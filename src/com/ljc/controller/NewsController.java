package com.ljc.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljc.config.Config;
import com.ljc.entity.Admin;
//import com.ljc.entity.Comment;
//import com.ljc.entity.Floor;
import com.ljc.entity.News;
import com.ljc.entity.User;
import com.ljc.service.AdminService;
import com.ljc.service.ArticleService;
import com.ljc.service.CommentService;
import com.ljc.service.NewsService;
import com.ljc.service.UserService;
import com.ljc.util.LogUtils;
import com.ljc.util.StringUtils;

/**
 * @author LJC 
 * 管理员公告列表Controller
 */
@Controller
@RequestMapping("news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AdminService adminService;
	
	
	/**
	 * 获取公告分页数据
	 * @return
	 */
	@RequestMapping("/list/{currentPage}")
	public ModelAndView getNewsPageList(HttpSession session,
			@PathVariable("currentPage") int currentPage){
		LogUtils.info("new/list/"+currentPage);
		ModelAndView mav = new ModelAndView();
		int pageSize = Config.DEFAULT_PAGESIZE;// 每页记录数
		mav.addObject("newsPageBean", newsService.getNewsPageList(currentPage,pageSize));

		List<Integer> statusList = new ArrayList<>();
		statusList.add(Config.STATUS_TOP);	//置顶帖
		statusList.add(Config.STATUS_TOP_HOT);	//置顶且加精帖
		mav.addObject("topNews", newsService.getNewsListByStatus(statusList));
		
		//刷新session
		if(session.getAttribute("user") != null){
			User user = (User) session.getAttribute("user");
			session.setAttribute("user", userService.getUserByID(user.getUid()));
		}
		if(session.getAttribute("admin") != null){
			Admin admin = (Admin) session.getAttribute("admin");
			session.setAttribute("admin", adminService.getAdminByID(admin.getAid()));
			mav.setViewName("news/newsList");
			return mav;
		}
		mav.setViewName("news/newsList");
		LogUtils.info("new/list/"+currentPage);
		return mav;
	}
	
	/**
	 * 根据id获取公告数据
	 * @param nid
	 * @return
	 */
	@RequestMapping("/details/{nid}")
	public ModelAndView getArticleByID(@PathVariable("nid") Integer nid){
		ModelAndView mav = new ModelAndView();
		//帖子数据
		News news = newsService.getNewsByID(nid);
		//评论数据
		//List<Comment> commentList = commentService.findComment(aid, null);
		//楼中楼评论数据
		//List<Floor> floorCommentList = commentService.findFloorComment(aid, null);
		
		//数据存放至Model
		mav.addObject(news);
		
		//mav.addObject(commentList);
		//mav.addObject("Floor", floorCommentList);
		//设置视图
		mav.setViewName("news/newsContent");
		return mav;
	}

	/**
	 * 公告界面还未实现
	 * 添加楼中楼评论
	 * @param aid
	 * @param cid
	 * @param uid
	 * @param content
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/floor/add", method = RequestMethod.POST)
//	public Map<String,String> findFloorComment(HttpSession session,
//			@RequestParam("aid") Integer aid,
//			@RequestParam("cid") Integer cid,
//			@RequestParam("uid") Integer uid,
//			@RequestParam("content") String content){
//		Map<String,String> map = new HashMap<String,String>();
//		//身份检测
//		User user = (User) session.getAttribute("user");	//当前登录用户
//		if(user.getUid() != uid){
//			map.put("data", "回复失败！");
//			return map;
//		}
//		//楼中楼评论数据
//		int result = commentService.addFloorComment(aid,cid,uid,content);
//		if(result>0){
//			LogUtils.info("楼中楼评论成功!内容=>"+content);
////			map.put("data", "回复成功！");
//		}else{
//			LogUtils.info("楼中楼评论失败!");
////			map.put("data", "回复失败！");
//		}
//		return map;
//	}

	/**
	 * 根据id删除公告数据
	 * @param nid
	 * @return
	 */
	@RequestMapping("/delete/{nid}")
	@ResponseBody
	public Map<String, String> deleteArticleByID(HttpSession session,@PathVariable("nid") Integer nid){
		Map<String, String> map = new HashMap<>();
		//身份检测
		Admin admin = (Admin) session.getAttribute("admin");	//当前登录用户
//		Integer uid = articleService.getArticleByID(aid).getUid();	//帖子作者
		Integer aid = newsService.getNewsByID(nid).getAdmin().getAid();	//帖子作者
		if(admin.getAid() != aid){
			map.put("data", "只能删除自己的公告！");
			return map;
		}
		int result = newsService.deleteNewsByID(nid);
		if(result>0){
			LogUtils.info("成功删除id为{}的公告！",nid);
			map.put("data", "删除成功！");
		}else{
			LogUtils.info("删除失败id为{}的帖子！",nid);
			map.put("data", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 发表新公告
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addArticle(HttpSession session,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "content") String content,
			@RequestParam(value = "aid") Integer aid,
			@RequestParam(value = "lable") String lable){
		Map<String, String> map = new HashMap<>();
		//身份检测
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null){
			map.put("data", "请登录管理员账号！");
			return map;
		}
		if(StringUtils.isEmpty(title) || StringUtils.isBlank(title)){
			map.put("data", "标题不能为空！");
			return map;
		}
		int result = newsService.addNews(title,content,new Timestamp(new Date().getTime()),aid,lable);
		if(result>0){
			LogUtils.info("新增公告成功,标题:{},内容长度:{}",title,content.length());
			map.put("data", "新增公告成功！");
		}else{
			LogUtils.info("新增公告失败！");
		}
		return map;
	}
	
	/**
	 * 关键字查询公告标题
	 * @param key
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView SearchArticle(@RequestParam("key")String key){
		ModelAndView mav = new ModelAndView();
		List<News> list = newsService.searchNewsByKey(key);
		LogUtils.info("查询关键字:{},共查询到{}条记录",key,list.size());
		mav.addObject("key",key);
		mav.addObject("resultList",list);
		mav.setViewName("search");
		return mav;
	}
	
}
