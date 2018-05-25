package com.ljc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ljc.config.Config;
import com.ljc.entity.Admin;
import com.ljc.entity.Article;
import com.ljc.entity.Comment;
import com.ljc.entity.Floor;
import com.ljc.entity.News;
import com.ljc.entity.User;
import com.ljc.service.AdminService;
import com.ljc.service.ArticleService;
import com.ljc.service.CommentService;
import com.ljc.service.NewsService;
import com.ljc.service.UserService;
import com.ljc.util.IdcardValidatorUtil;
import com.ljc.util.LogUtils;
import com.ljc.util.QiNiuUtils;
import com.ljc.util.StringUtils;
import com.qiniu.storage.model.DefaultPutRet;

/**
 * @author LJC 
 * 管理员登录注册Controller
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private IdcardValidatorUtil idcardValidatorUtil;
	

	/**
	 * 注册管理员
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> regist(
			@RequestParam("admin_name") String admin_name,
			@RequestParam("password") String password,
			@RequestParam("aid_num") String aid_num,
			@RequestParam("authorization_code") String authorization_code) {
		
		Map<String, String> map = new HashMap<String, String>();
		//保证用户名唯一
		if(adminService.findAdmin(admin_name,null) != null){
			map.put("data", "用户名已存在！");
			return map;
		}
		if(!idcardValidatorUtil.isIdcard(aid_num)){
			map.put("data", "请输入正确的身份证号码！");
			return map;
		}
		if(!Config.ADMIN_Authorization_Code.equals(authorization_code)){
			map.put("data", "请输入正确的管理员授权码！");
			return map;
		}
		//默认头像
		String headimg = Config.DEFAULT_HEADIMG_ADDRESS;
		String status ="1";
		int result = adminService.addAdmin(admin_name, StringUtils.MD5(password), headimg,aid_num,status);
		if (result > 0) {
			LogUtils.info("注册成功！管理员名:{},密码：{},身份证号：{}", admin_name, password,aid_num);
			map.put("data", "注册成功,请重新登录！");
		} else {
			LogUtils.info("注册失败!");
			map.put("data", "注册失败！");
		}
		return map;
	}

	/**
	 * 管理员登录
	 * @param session
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpSession session,
			@RequestParam("admin_name") String admin_name,
			@RequestParam("password") String password) {
		Admin admin = adminService.findAdmin(admin_name, StringUtils.MD5(password));
		Map<String, String> map = new HashMap<String, String>();
		if (admin!=null) {
			LogUtils.info("登录成功！管理员名:{},密码：{}", admin_name, password);
			map.put("data", "登录成功！");
			session.setAttribute("admin", admin);
		} else {
			LogUtils.info("登录失败!");
			map.put("data", "登录失败！");
		}
		return map;
	}
	
	/**
	 * 根据id获得管理員信息及发表过的公告
	 * @return
	 */
	@RequestMapping("/manager/{aid}")
	public ModelAndView getUserManager(@PathVariable("aid") Integer aid){
		ModelAndView mav = new ModelAndView();
		//用户信息
		Admin admin = adminService.getAdminByID(aid);
		//用户帖子数据
		List<News> newsList = newsService.getNewsByAID(aid);
		mav.addObject("a",admin);
		mav.addObject("adminNews",newsList);
		mav.setViewName("admin/adminManager");
		return mav;
	}
	
	/**
	 * 根据id获取帖子数据
	 * @param aid
	 * @return
	 */
	@RequestMapping("/details/{aid}")
	public ModelAndView getArticleByID(@PathVariable("aid") Integer aid){
		ModelAndView mav = new ModelAndView();
		//帖子数据
		Article article = articleService.getArticleByID(aid);
		//评论数据
		List<Comment> commentList = commentService.findComment(aid, null);
		//楼中楼评论数据
		List<Floor> floorCommentList = commentService.findFloorComment(aid, null);
		//数据存放至Model
		mav.addObject(article);
		mav.addObject(commentList);
		mav.addObject("Floor", floorCommentList);
		//设置视图
		mav.setViewName("admin/article/articleContent");
		return mav;
	}
	
	/**
	 * 根据aid获得管理员信息
	 * @return
	 */
	@RequestMapping("/info/{aid}")
	public ModelAndView getUserInfo(@PathVariable("aid") Integer aid){
		ModelAndView mav = new ModelAndView();
		//用户信息
		Admin admin = adminService.getAdminByID(aid);
		mav.addObject("aInfo",admin);
		mav.setViewName("admin/adminInfo");
		return mav;
	}
	
	/**
	 * 更新头像
	 * @param request
	 * @param file
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/headimg/{aid}", method = RequestMethod.POST)
	public String updateHeadImg(HttpServletRequest request,
			@RequestParam("file") MultipartFile file,
			@PathVariable("aid") Integer aid){
		//防止空白头像的情况
		if(file.isEmpty()) return null;
		//进行上传操作
		DefaultPutRet putRet = QiNiuUtils.upLoad(file, Config.QINIU_BUCKET_HEADIMG);
		//头像地址并更根据uid插入对应数据库
		int result = adminService.updateHeadImg(Config.QINIU_IMG_URL + putRet.key,aid);
		if(result > 0){
			LogUtils.info("成功更新aid为{}的用户头像,文件名{}",aid,putRet.key);
		} else {
			LogUtils.info("更新头像失败！");
		}
		//刷新session
		request.getSession().setAttribute("admin",adminService.getAdminByID(aid));
		return "redirect:/admin/manager/"+aid;
	}
	
	/**
	 * 根据aid更新管理员密码
	 * @param session
	 * @param aid
	 * @param admin_name
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/update/{aid}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> updateUserInfo(HttpSession session,
			@PathVariable("aid") Integer aid,
			@RequestParam("password") String password) {
		Map<String,String> map = new HashMap<>();
		//身份检测
		Admin admin = (Admin) session.getAttribute("admin");	//当前登录用户
		if(admin.getAid() != aid){
			map.put("data", "只能修改自己的信息！");
			return map;
		}
		//更新用户信息
		int result = adminService.updateAdminInfo(aid,StringUtils.MD5(password));
		if(result>0){
			LogUtils.info("成功更新id为{}的管理员信息！新密码:{}",aid,password);
			map.put("data", "信息修改成功！");
		}else{
			map.put("data", "信息修改失败！");
		}
		return map;
	}
	
	/**
	 * 管理员根据id删除帖子数据
	 * @param aid
	 * @return
	 */
	@RequestMapping("/delete/{aid}")
	@ResponseBody
	public Map<String, String> deleteArticleByID(HttpSession session,@PathVariable("aid") Integer aid){
		Map<String, String> map = new HashMap<>();
		//身份检测
		Admin admin = (Admin) session.getAttribute("admin");	//当前登录管理员
		if(admin == null || 
				(adminService.getAdminByID(admin.getAid())) == null){
			map.put("data", "请先登录管理员账户！");
			return map;
		}
		int result = articleService.deleteArticleByID(aid);
		if(result>0){
			LogUtils.info("成功删除id为{}的帖子！",aid);
			map.put("data", "删除成功！");
		}else{
			LogUtils.info("删除失败id为{}的帖子！",aid);
			map.put("data", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 安全退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpSession session) {
		//销毁session
		session.invalidate();
		return "redirect:/index.jsp";
	}

}
