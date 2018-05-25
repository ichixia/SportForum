package com.ljc.entity;

import java.io.Serializable;

/*
 * 管理员实体类
 * */
public class Admin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int aid;
	private String admin_name;
	private String password;
	private String aid_num;//身份证号码
	private String status;//管理员状态
	private String headimg;//头像
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAid_num() {
		return aid_num;
	}
	public void setAid_num(String aid_num) {
		this.aid_num = aid_num;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
}
