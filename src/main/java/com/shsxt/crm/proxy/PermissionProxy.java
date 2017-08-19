package com.shsxt.crm.proxy;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shsxt.crm.annotation.RequirePermissions;
import com.shsxt.crm.constant.Constant;
import com.shsxt.crm.exception.AuthException;
import com.shsxt.crm.exception.LoginException;
import com.shsxt.crm.service.PermissionService;
import com.shsxt.crm.util.LoginUserUtil;

@Component
@Aspect
public class PermissionProxy {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private PermissionService permissionService;
	
	@Pointcut("execution(* com.*.*.controller.*.*(..))")
	public void pointcut() {
		
	}
	
	@Before("pointcut()")
	public void preMethod(JoinPoint joinPoint) throws Throwable {
		
		// 判断如果是登录页面就不需要走验证
		String uri = request.getRequestURI();
		if ("/index".equals(uri) || "/user/login".equals(uri)) {
			return;
		}
		
		// 获取用户权限
		List<String> userPermissions = buildPermission();
		
		// 拿到方法签名
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); 
		// 拿到执行的方法
		Method method = methodSignature.getMethod();
		// 获取一个注解
		RequirePermissions requirePermissions = method.getAnnotation(RequirePermissions.class);
		if (requirePermissions == null) {
			return;
		}
		
		// 权限比对
		if (!userPermissions.contains(requirePermissions.permission())) {
			throw new AuthException(-1, "权限认证失败");
		}
	}
	
	/**
	 * 设置权限
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	private List<String> buildPermission() {
		// 验证用户是否登录
		Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
		if (userId == null) { // 未登录
			throw new LoginException(201, "请登录");
		}
		List<String> userPermissions = (List<String>) session.getAttribute(Constant.USER_PERMISSION_SESSION_KEY);
		if (userPermissions != null) {
			return userPermissions;
		}
		// 从数据库中读取用户权限
		userPermissions = permissionService.findUserPermissions(userId);
		if (userPermissions == null || userPermissions.size() < 1) {
			throw new AuthException(-1, "权限认证失败");
		}
		session.setAttribute(Constant.USER_PERMISSION_SESSION_KEY, userPermissions);
		return userPermissions;
	}
}
