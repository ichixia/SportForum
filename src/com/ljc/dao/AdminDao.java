package com.ljc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljc.entity.Admin;
import com.ljc.entity.User;

public interface AdminDao {

	public int addAdmin(@Param("admin_name") String admin_name,
			@Param("password") String password, @Param("headimg") String headimg,
			@Param("aid_num") String aid_num,@Param("status") String status);

	public Admin findAdmin(@Param("admin_name") String admin_name,
			@Param("password") String password);

	public Admin getAdminByID(@Param("aid") Integer aid);

	public int updateHeadImg(@Param("headimg") String headImgName,
			@Param("aid") Integer aid);

	public int updateAdminInfo(@Param("aid") Integer aid,
			@Param("password") String password);
	
	

}
