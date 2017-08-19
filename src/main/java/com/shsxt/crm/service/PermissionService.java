package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.constant.ModuleGrade;
import com.shsxt.crm.dao.ModuleDao;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.model.Module;
import com.shsxt.crm.model.Permission;
import com.shsxt.crm.model.Role;
import com.shsxt.crm.util.AssertUtil;

@Service
public class PermissionService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private ModuleDao moduleDao;
	
	/**
	 * 获取登录用户的权限
	 * @param userId
	 * @return
	 */
	public List<String> findUserPermissions(Integer userId) {
		AssertUtil.intIsNotEmpty(userId, "请登录");
		List<String> permissions = permissionDao.findUserPermissions(userId);
		return permissions;
	}

	/**
	 * 授权或者取消权限
	 * 
	 * @param roleId
	 *            角色ID
	 * @param moduleId
	 *            模块ID
	 * @param checked
	 *            是否授权
	 */
	public void addDodoRelate(Integer roleId, Integer moduleId, boolean checked) {
		// 验证(参数非空，角色非空，模块非空)
		check(roleId, moduleId);
		Module module = moduleService.findById(moduleId);
		// checked
		if (checked) { // 授权
			// 先把该模块赋值给此角色
			List<Permission> permissions = new ArrayList<>();
			Permission permission = bindModule(roleId, module);
			permissions.add(permission);
			// 把父模块赋值给此角色
			// 1. 先查询父模块是否绑定此角色: 父模块可能有多个
			addParentModule(module, roleId, permissions);
			// 把子模块赋值给此角色
			addChildrenModule(moduleId, roleId, permissions);
			// 执行插入操作
			permissionDao.insertBatch(permissions);
		} else {// 取消权限
			// 解绑本模块
			permissionDao.delete(moduleId, roleId);
			// 解绑本模块的子级模块
			// 获取子模块然后在删除
			String treePath = buildTreePath(moduleId);
			permissionDao.deleteChildrenModule(treePath, roleId);
			// 解绑本模块的父级模块
			deleteParentModule(module, roleId);
		}

	}

	/**
	 * (参数非空，角色非空，模块非空)验证
	 * 
	 * @param roleId
	 * @param moduleId
	 */
	private void check(Integer roleId, Integer moduleId) {
		// 基本参数验证
		AssertUtil.intIsNotEmpty(roleId, "请选择角色");
		AssertUtil.intIsNotEmpty(moduleId, "请选择模块");
		// 角色验证
		Role role = roleService.findById(roleId);
		AssertUtil.notNull(role, "该角色不存在");
		// 模块验证
		Module module = moduleService.findById(moduleId);
		AssertUtil.notNull(module, "该模块不存在");
	}

	/**
	 * 将模块绑定到角色上
	 * 
	 * @param roleId
	 * @param moduleId
	 */
	private Permission bindModule(Integer roleId, Module module) {
		Permission permission = new Permission();
		permission.setAclValue(module.getOptValue());
		permission.setModuleId(module.getId());
		permission.setRoleId(roleId);
		return permission;
	}

	/**
	 * 构建treePath
	 * 
	 * @param moduleId
	 * @return
	 */
	private String buildTreePath(Integer moduleId) {
		String treePath = "";
		if (ModuleGrade.root.getGrade() == moduleService.findById(moduleId).getGrade()) {
			treePath = "," + moduleId + ",";
		} else {
			treePath = moduleService.findById(moduleId).getTreePath() + moduleId + ",";
		}
		return treePath;

	}
	
	/**
	 * 添加父模块
	 * @param module
	 * @param roleId
	 * @param permissions
	 */
	private void addParentModule(Module module,Integer roleId,List<Permission> permissions) {
		if (ModuleGrade.first.getGrade() == module.getGrade()) {
			Permission parentPermission = permissionDao.findByRolePermission(module.getParentId(), roleId);
			if (parentPermission == null) { // 父模块没有绑定子模块
				Module parentModule = moduleService.findById(module.getParentId());
				parentPermission = bindModule(roleId, parentModule);
				permissions.add(parentPermission);
			} else if (ModuleGrade.second.getGrade() == module.getGrade()) { // 第二级有两父模块
				String[] parentIds = module.getTreePath().substring(1, module.getTreePath().lastIndexOf(",") + 1)
						.split(","); // ,1,2,
				for (String parentIdStr : parentIds) {
					parentPermission = permissionDao.findByRolePermission(Integer.parseInt(parentIdStr), roleId);
					if (parentPermission != null) {
						continue;
					}
					Module parentModule = moduleService.findById(Integer.parseInt(parentIdStr));
					parentPermission = bindModule(roleId, parentModule);
					permissions.add(parentPermission);
				}
			}
		}
	}
	
	/**
	 * 添加子模块
	 * @param moduleId
	 * @param roleId
	 * @param permissions
	 */
	private void addChildrenModule(Integer moduleId,Integer roleId,List<Permission> permissions) {
		String treePath = buildTreePath(moduleId);
		List<Module> childrenModules = moduleDao.findChildrenModules(treePath);
		for (Module childModule : childrenModules) {
			Permission childPermission = bindModule(roleId, childModule);
			permissions.add(childPermission);
		}
	}
	
	/**
	 * 撤销父模块
	 * @param module
	 * @param roleId
	 */
	private void deleteParentModule(Module module,Integer roleId) {
		if (module.getGrade() > ModuleGrade.root.getGrade()) { // 排除根级模块
			String[] parentIds = module.getTreePath().substring(1, module.getTreePath().lastIndexOf(","))
					.split(","); // ,1,2,
			for (int i = parentIds.length - 1; i > -1; i--) {
				Integer parentId = Integer.parseInt(parentIds[i]);
				// 判定parentId下面是否有其他子级与此角色进行过绑定 如果有就不删除，如没有就删除
				// 获取父级模块
				Module parentModule = moduleDao.findById(parentId);
				String ptreePath = buildTreePath(parentModule.getId());
				Integer count = permissionDao.findChildrens(ptreePath, roleId);
				if (count == null || count == 0) {
					permissionDao.delete(parentId, roleId);
				}
			}
		}
	}

}
