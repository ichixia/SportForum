package com.ljc.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *最新信息实体类
 *由管理员上传
 *数据库对应news表 
 **/
public class News implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer nid;
	private String title;
	private String content;
	private Date date;
	private Integer aid;
	private String lable;
	private Integer status;
	private Admin admin;
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	

}
