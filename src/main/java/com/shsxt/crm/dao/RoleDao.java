package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.model.Role;
import com.sun.org.glassfish.gmbal.ParameterNames;

public interface RoleDao {

	@Select("select id, role_name, role_remark, create_date, "
			+ "update_date from t_role where is_valid = 1")
	public PageList<Role> selectForPage(PageBounds pageBounds); 
	
	@Select("select id, role_name, role_remark, create_date, "
			+ "update_date from t_role where is_valid = 1 and role_name = #{roleName}")
	public Role findByRoleName(@Param("roleName")String roleName);

	@Insert("insert into t_role (role_name,role_remark,create_date,update_date,is_valid)"
			+ " values (#{roleName},#{roleRemark},now(),now(),1)")
	public void insert(Role role); 

	@Select("select id, role_name, role_remark, create_date, "
			+ "update_date from t_role where is_valid = 1 and id = #{id}")
	public Role findById(@Param(value="id") Integer id) ;

	@Update("update t_role set role_name=#{roleName},role_remark=#{roleRemark},"
			+"update_date=now(),is_valid = 1 where id = #{id}")
	public void update(Role role);

	@Update("update t_role set is_valid = 0 ,update_date=now() where id in (${ids})")
	public void delete(@Param(value="ids")String ids);

	@Select("select id, role_name, role_remark, create_date, "
			+ "update_date from t_role where is_valid = 1")
	public List<Role> findAll();

}
