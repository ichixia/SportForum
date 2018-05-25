package com.ljc.service;

import com.ljc.entity.Admin;

public interface AdminService {

	/**
	 * 注册管理员
	 * @param admin_name
	 * @param password
	 * @param headimg 
	 * @return
	 */
	public int addAdmin(String admin_name, String password, String headimg,String aid_num,String status);

	/**
	 * 管理员登录
	 * @param admin_name
	 * @param password
	 * @return
	 */
	public Admin findAdmin(String admin_name, String password);

	/**
	 * 根据id查询管理员
	 * @param aid
	 * @return
	 */
	public Admin getAdminByID(Integer aid);

	/**
	 * 更新头像
	 * @param headImgName
	 * @param uid 
	 * @return
	 */
	public int updateHeadImg(String headImgName, Integer aid);

	/**
	 * 根据uid更新对应用户信息
	 * @param uid
	 * @param password
	 * @return
	 */
	public int updateAdminInfo(Integer aid, String password);


}
