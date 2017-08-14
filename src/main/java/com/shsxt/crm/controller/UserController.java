package com.shsxt.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.vo.UserLoginIdentity;
import com.shsxt.crm.vo.UserVO;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	@ResponseBody
	public ResultInfo login(String userName,String userPwd){
		
		UserLoginIdentity userLoginIdentity = userService.login(userName, userPwd);
		
		ResultInfo resultInfo = success(userLoginIdentity);
		
		return resultInfo;
	}
	
	@RequestMapping("find_customer_manager")
	@ResponseBody
	public List<UserVO> findCutomerManager() {
		List<UserVO> customerManagers = userService.findCutomerManager();
		return customerManagers;
	}

}
