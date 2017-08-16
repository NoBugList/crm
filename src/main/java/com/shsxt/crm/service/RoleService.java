package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.base.BaseQuery;
import com.shsxt.crm.dao.RoleDao;
import com.shsxt.crm.model.Role;
import com.shsxt.crm.util.AssertUtil;

import junit.framework.Assert;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;

	/**
	 * 页面展示
	 * @param query
	 * @return
	 */
	public Map<String, Object> selectForPage(BaseQuery query) {
		PageBounds pageBounds = query.buildPageBounds();
		PageList<Role> roles = roleDao.selectForPage(pageBounds);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("rows", roles);
		result.put("total", roles.getPaginator().getTotalCount());
		return result;
	}

	/**
	 * 添加
	 * @param roleName
	 * @param roleRemark
	 */
	public void add(String roleName,String roleRemark) {
		AssertUtil.isNotEmpty(roleName, "请输入角色名称");
		AssertUtil.isNotEmpty(roleRemark, "请输入角色描述");
		Role roleTem = roleDao.findByRoleName(roleName);
		AssertUtil.isTrue(roleTem!=null, "该角色已存在，请重新输入");
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleRemark(roleRemark);
		roleDao.insert(role);
	}

	
	/**
	 * 更新
	 * @param id
	 * @param roleName
	 * @param roleRemark
	 */
	public void update(Integer id,String roleName,String roleRemark) {
		AssertUtil.intIsNotEmpty(id, "请选择角色");
		AssertUtil.isNotEmpty(roleName, "请输入角色名称");
		AssertUtil.isNotEmpty(roleRemark, "请输入角色描述");
		Role role = findById(id);
		if(!role.getRoleName().equals(roleName)) {
			Role roleTem = roleDao.findByRoleName(roleName.trim());
			AssertUtil.isTrue(roleTem!=null, "该角色已存在，请重新输入");
		}
		role.setRoleName(roleName);
		role.setRoleRemark(roleRemark);
		roleDao.update(role);	
	}

	/**
	 * 删除
	 * @param ids
	 */
	public void delete(String ids) {
		AssertUtil.isNotEmpty(ids, "请选择记录进行删除");
		roleDao.delete(ids);
	}
	
	public Role findById(Integer roleId) {
		AssertUtil.intIsNotEmpty(roleId, "请选择角色");
		Role role = roleDao.findById(roleId);
		AssertUtil.notNull(role, "该角色不存在");
		return role;
	}
}
