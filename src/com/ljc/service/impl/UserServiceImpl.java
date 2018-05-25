package com.ljc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljc.dao.UserDao;
import com.ljc.entity.Article;
import com.ljc.entity.PageBean;
import com.ljc.entity.User;
import com.ljc.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int addUser(String username, String password, String headimg,String id_num,String status) {
		return userDao.addUser(username, password, headimg,id_num,status);
	}

	@Override
	public User findUser(String username, String password) {
		return userDao.findUser(username, password);
	}

	@Override
	public User getUserByID(Integer uid) {
		return userDao.getUserByID(uid);
	}

	@Override
	public int updateHeadImg(String headImgName, Integer uid) {
		return userDao.updateHeadImg(headImgName, uid);
	}

	@Override
	public int updateUserInfo(Integer uid, String password) {
		return userDao.updateUserInfo(uid, password);
	}

	@Override
	public PageBean getUserPageList(int currentPage, int pageSize) {
		int count = userDao.getUserList().size();
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);// 总页数
		List<User> userList = userDao.getUserPageList(
				(currentPage - 1) * pageSize, pageSize);
		PageBean pageBean = new PageBean(currentPage, pageSize, count,
				totalPage, userList);
		return pageBean;
	}
	
	@Override
	public List<User> getUserList(){
		return userDao.getUserList();
	}

	@Override
	public List<User> searchUserByName(String username) {
		return userDao.searchUserByName(username);
	}

	@Override
	public int deleteUserByID(Integer uid) {
		return userDao.deleteUserByID(uid);
	}

	@Override
	public int updateUserStatus(Integer uid, String status) {
		return userDao.updateUserStatus(uid, status);
	}

}
