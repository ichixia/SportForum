package com.ljc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljc.entity.User;

public interface UserDao {

	public int addUser(@Param("username") String username,
			@Param("password") String password, @Param("headimg") String headimg,
			@Param("id_num") String id_num,@Param("status") String status);

	public User findUser(@Param("username") String username,
			@Param("password") String password);

	public User getUserByID(@Param("uid") Integer uid);

	public int updateHeadImg(@Param("headimg") String headImgName,
			@Param("uid") Integer uid);

	public int updateUserInfo(@Param("uid") Integer uid,
			@Param("password") String password);
	
	public List<User> getUserPageList(@Param("currentPage") int currentPage,
			@Param("pageSize") int pageSize); 
	
	public List<User> searchUserByName(@Param("username")String username);
	
	public int deleteUserByID(@Param("uid") Integer uid);
	
	public int updateUserStatus(@Param("uid") Integer uid,@Param("status") String status);
	
	public List<User> getUserList();

}
