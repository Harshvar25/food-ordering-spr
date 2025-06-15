package com.cognizant.webapp09.admin.dao;

import com.cognizant.webapp09.entity.Admin;

public interface AdminDao {
	public boolean checkCredentials(Admin admin);
}