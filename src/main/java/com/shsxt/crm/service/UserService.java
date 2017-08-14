package com.shsxt.crm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.exception.ParamException;
import com.shsxt.crm.model.User;
import com.shsxt.crm.util.MD5Util;
import com.shsxt.crm.util.UserIDBase64;
import com.shsxt.crm.vo.UserLoginIdentity;
import com.shsxt.crm.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * 登录
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public UserLoginIdentity login(String userName,String userPwd){
		
		//参数验证
		if (StringUtils.isBlank(userName)) {
			throw new ParamException("请输入用户名");
		}
		if (StringUtils.isBlank(userPwd)) {
			throw new ParamException("请输入密码");
		}
		//用户数据查询
		User user = userDao.findByUserName(userName);
		//用户判断
		if (null==user) {
			throw new ParamException("用户名或密码错误");
		}
		//密码判断
		String md5Pwd = MD5Util.md5Method(userPwd);
		if (!md5Pwd.equals(user.getPassword())) {
			throw new ParamException("用户名或密码错误");
		}
		//构建登录实体类
		UserLoginIdentity userLoginIdentity = new UserLoginIdentity();
		userLoginIdentity.setUserIdString(UserIDBase64.encoderUserID(user.getId()));
		userLoginIdentity.setRealName(user.getTrueName());
		userLoginIdentity.setUserName(userName);
		//返回
		return userLoginIdentity;

	}
	
	
	public List<UserVO> findCutomerManager() {
		List<UserVO> customerManagers = userDao.findCutomerManager();
		return customerManagers;
	}
}
