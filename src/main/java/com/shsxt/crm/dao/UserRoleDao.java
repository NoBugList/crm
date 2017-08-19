package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.model.UserRole;

public interface UserRoleDao {

	void insertBatch(@Param(value="userRoles")List<UserRole> userRoles);
	
	@Delete("delete from t_user_role where user_id = #{userId}")
	void deleteUserRoles(@Param(value="userId")Integer userId);
	
	@Select("select id, user_id, role_id from t_user_role where user_id = #{userId} and is_valid = 1")
	List<UserRole> findUserRoles(@Param(value="userId")Integer userId);

}
