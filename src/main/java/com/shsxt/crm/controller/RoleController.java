package com.shsxt.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseQuery;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.model.Role;
import com.shsxt.crm.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("index")
	public String index() {
		return "role";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> selectForPage(BaseQuery query){
		Map<String,Object> result = new HashMap<String, Object>();
		result = roleService.selectForPage(query);
		return result;
	}
	
	@RequestMapping("addRole")
	@ResponseBody
	public ResultInfo add(String roleName,String roleRemark) {
		roleService.add(roleName,roleRemark);
		return success("添加成功");	
	}
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(Integer id,String roleName,String roleRemark) {
		roleService.update(id,roleName,roleRemark);
		return success("更新成功");	
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(String ids) {
		roleService.delete(ids);
		return success("删除成功");	
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("find_all")
	@ResponseBody
	public List<Role> findAllList() {
		Map<String, Object> result = roleService.findAll();
		return (List<Role>) result.get("rows");
	}

}
