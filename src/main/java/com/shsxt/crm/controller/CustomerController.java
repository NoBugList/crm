package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.annotation.RequirePermissions;
import com.shsxt.crm.annotation.SystemLog;
import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.dto.CustomerQuery;
import com.shsxt.crm.model.Customer;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.CustomerVO;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("find_all")
	@ResponseBody
	public List<CustomerVO> findAll() {
		List<CustomerVO> customers = customerService.findAll();
		return customers;
	}
	
	@RequestMapping("index")
	@RequirePermissions(permission = "20")
	@SystemLog("客户信息管理")
	public String index () {
		return "customer";
	}
	
	@RequestMapping("list")
	@RequirePermissions(permission = "2010")
	@SystemLog("客户信息管理-分页查询")
	@ResponseBody
	public Map<String, Object> selectForPage (CustomerQuery query) {
		Map<String, Object> result = customerService.selectForPage(query);
		return result;
	}
	
	@RequestMapping("add")
	@RequirePermissions(permission = "201001")
	@SystemLog("客户信息管理-添加")
	@ResponseBody
	public ResultInfo add (Customer customer) {
		customerService.add(customer);
		return success("添加成功");
	}
	
	@RequestMapping("update")
	@RequirePermissions(permission = "201002")
	@SystemLog("客户信息管理-修改")
	@ResponseBody
	public ResultInfo update (Customer customer) {
		customerService.update(customer);
		return success("修改成功");
	}

	@RequestMapping("delete")
	@RequirePermissions(permission = "201003")
	@SystemLog("客户信息管理-删除")
	@ResponseBody
	public ResultInfo delete (String ids) {
		customerService.delete(ids);
		return success("删除成功");
	}
	
}
