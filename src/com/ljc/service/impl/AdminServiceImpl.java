package com.ljc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljc.dao.AdminDao;
import com.ljc.entity.Admin;
import com.ljc.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public int addAdmin(String admin_name, String password, String headimg,
			String aid_num, String status) {
		return adminDao.addAdmin(admin_name, password, headimg, aid_num, status);
	}

	@Override
	public Admin findAdmin(String admin_name, String password) {
		return adminDao.findAdmin(admin_name, password);
	}

	@Override
	public Admin getAdminByID(Integer aid) {
		return adminDao.getAdminByID(aid);
	}

	@Override
	public int updateHeadImg(String headImgName, Integer aid) {
		return adminDao.updateHeadImg(headImgName, aid);
	}

	@Override
	public int updateAdminInfo(Integer aid, String password) {
		return adminDao.updateAdminInfo(aid, password);
	}

}
