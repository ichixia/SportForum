package com.ljc.entity;

import java.io.Serializable;

/**
 * @author LJC 
 * 用户实体
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer uid;
	private String username;
	private String password;
	private String headimg; // 用户头像
	private String id_num;//身份证号
	private String status;//用户状态

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getId_num() {
		return id_num;
	}

	public void setId_num(String id_num) {
		this.id_num = id_num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
