package com.shsxt.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.UserRoleDao;
import com.shsxt.crm.model.UserRole;
import com.shsxt.crm.util.AssertUtil;



@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;

	public List<UserRole> findUserRoles(Integer userId) {
		AssertUtil.intIsNotEmpty(userId, "请选择用户");
		List<UserRole> userRoles = userRoleDao.findUserRoles(userId);
		return userRoles;
	}

}
