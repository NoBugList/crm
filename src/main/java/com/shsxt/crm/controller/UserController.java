package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.dto.UserQuery;
import com.shsxt.crm.model.User;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.util.LoginUserUtil;
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
	
	@RequestMapping("index")
	public String index() {
		return "user";
	}
	
	@RequestMapping("find_all")
	@ResponseBody
	public Map<String, Object>findAll(UserQuery query) {
		Map<String, Object> result = userService.findAll(query);
		return result;
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object>selectForPage(UserQuery query) {
		Map<String, Object> result = userService.selectForPage(query);
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo add(User user) {
		userService.add(user);
		return success("添加成功");
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(User user) {
		userService.update(user);
		return success("修改成功");
	}

	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(String ids) {
		userService.deleteBatch(ids);
		return success("删除成功");
	}
	
	@RequestMapping("update_password")
	public @ResponseBody Object updatePassword(String oldPassword,
			String newPassword, String confirmPassword, HttpServletRequest request) {
		int userId = LoginUserUtil.releaseUserIdFromCookie(request);
		userService.updatePassword(userId, oldPassword, newPassword, confirmPassword);
		return success("更新成功, 系统将自动退出, 请重新登陆");
	}

}
