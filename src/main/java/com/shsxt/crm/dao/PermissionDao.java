package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.model.Permission;

public interface PermissionDao {
	
	@Select("select id, role_id, module_id from t_permission "
			+ " where is_valid = 1 and module_id = #{moduleId} and role_id = #{roleId}")
	Permission findByRolePermission(@Param(value="moduleId")Integer moduleId, @Param(value="roleId")Integer roleId);

	void insertBatch(@Param(value="permissions")List<Permission> permissions);
	
	@Delete("delete from t_permission where module_id = #{moduleId} and role_id = #{roleId}")
	void delete(@Param(value="moduleId")Integer moduleId, @Param(value="roleId")Integer roleId);
	
	@Select("SELECT count(1) from t_permission where role_id = #{roleId} "
			+ " and module_id in (SELECT id FROM t_module where tree_path like '${treePath}%')")
	Integer findChildrens(@Param(value="treePath")String treePath, @Param(value="roleId")Integer roleId);
	
	@Delete("DELETE from t_permission where role_id = #{roleId} and module_id in "
			+ " (SELECT id FROM t_module where tree_path like '${treePath}%')")
	void deleteChildrenModule(@Param(value="treePath")String treePath, @Param(value="roleId")Integer roleId);

	@Select("SELECT DISTINCT t3.acl_value FROM t_user t1 " +
			" LEFT JOIN t_user_role t2 ON t1.id = t2.user_id " +
			" LEFT JOIN t_permission t3 ON t2.role_id = t3.role_id" +
			" WHERE t1.id = #{userId}")
	List<String> findUserPermissions(@Param(value="userId")Integer userId);
	

}
