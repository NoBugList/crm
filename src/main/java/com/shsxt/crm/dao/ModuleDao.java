package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.model.Module;

public interface ModuleDao {
	
	String  Module_Columns = "select id, module_name, module_style, url, parent_id, grade, orders, tree_path," + 
			"create_date, update_date, is_valid, opt_value from t_module " ;
	
	@Select(Module_Columns+" where is_valid = 1")
	PageList<Module> selectForPage(PageBounds pageBounds);
	
	@Select(Module_Columns+"where is_valid = 1 and id=#{id}")
	Module findById(@Param(value="id")Integer id);
	
	@Insert("insert into t_module " + 
			"(module_name, module_style, url, parent_id, grade, orders, tree_path," + 
			"create_date, update_date, is_valid, opt_value)" + 
			"values " + 
			"(#{moduleName}, #{moduleStyle}, #{url}, #{parentId}, #{grade}, #{orders},"+ 
			"#{treePath},now(), now(), 1, #{optValue})" )
	void insert(Module module);

	@Select(Module_Columns+"where is_valid = 1 and opt_value=#{optValue}")
	Module findByOptValue(String optValue);

	@Update("update t_module set  module_name = #{moduleName},module_style = #{moduleStyle},"
			+ " url = #{url},parent_id = #{parentId},grade = #{grade}, orders = #{orders},"
			+ " tree_path = #{treePath},opt_value = #{optValue},UPDATE_DATE = now()"
			+ " where id = #{id}")
	void update(Module moduleFromDB);

	@Update("update t_module set is_valid = 0, update_date = now() where id IN (${ids})")
	void delete(@Param(value="ids")String ids);

	@Select("select id ,module_name from t_module where is_valid=1 and grade = #{grade}")
	List<Module> findByGrade(@Param(value="grade")Integer grade);
	
	





}
