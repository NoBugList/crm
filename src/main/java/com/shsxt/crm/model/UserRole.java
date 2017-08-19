package com.shsxt.crm.model;

import com.shsxt.crm.base.BaseModel;

@SuppressWarnings("serial")
public class UserRole extends BaseModel {
	
	private Integer roleId;
	private Integer userId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	 

}
