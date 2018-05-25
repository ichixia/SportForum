package com.ljc.service;

import java.util.List;



import com.ljc.entity.PageBean;
import com.ljc.entity.User;

public interface UserService {

	/**
	 * 注册用户
	 * @param username
	 * @param password
	 * @param headimg 
	 * @return
	 */
	public int addUser(String username, String password, String headimg,String id_num,String status);

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUser(String username, String password);

	/**
	 * 根据id查询用户
	 * @param uid
	 * @return
	 */
	public User getUserByID(Integer uid);

	/**
	 * 更新头像
	 * @param headImgName
	 * @param uid 
	 * @return
	 */
	public int updateHeadImg(String headImgName, Integer uid);

	/**
	 * 根据uid更新对应用户信息
	 * @param uid
	 * @param password
	 * @return
	 */
	public int updateUserInfo(Integer uid, String password);
	
	public PageBean getUserPageList(int currentPage, int pageSize); 
	
	public List<User> getUserList();
	
	public List<User> searchUserByName(String username);
	
	public int deleteUserByID(Integer uid);
	
	public int updateUserStatus(Integer uid,String status);


}
