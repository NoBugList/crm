package com.shsxt.crm.model;

import com.shsxt.crm.base.BaseModel;

@SuppressWarnings("serial")
public class Role extends BaseModel {
	
	private String roleName;
	private String roleRemark;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleRemark() {
		return roleRemark;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	
	
}
