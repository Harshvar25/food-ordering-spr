package com.cognizant.webapp09.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.webapp09.admin.dao.AdminDao;
import com.cognizant.webapp09.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	@Autowired
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public boolean signInAdmin(Admin admin) {
		return adminDao.checkCredentials(admin);
	}
}