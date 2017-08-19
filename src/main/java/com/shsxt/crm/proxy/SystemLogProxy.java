package com.shsxt.crm.proxy;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.annotation.SystemLog;
import com.shsxt.crm.model.Log;
import com.shsxt.crm.service.LogService;
import com.shsxt.crm.util.LoginUserUtil;

@Aspect
@Component
public class SystemLogProxy {
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private HttpServletRequest request;
	
	private static Logger logger = LoggerFactory.getLogger(SystemLogProxy.class);
	
	/**
	 * 拦截带有@SystemLog注解的方法
	 */
	@Pointcut("@annotation(com.shsxt.crm.annotation.SystemLog)")
	public void pointcut() {
		
	}
	
	@Around("pointcut()&&@annotation(systemLog)")
	public Object aroundMethod(ProceedingJoinPoint pjp, SystemLog systemLog) 
			throws Throwable {
		
		Log log = new Log();
		log.setCreateDate(new Date());
		String createMan = LoginUserUtil.releaseUserNameFromCookie(request);
		log.setCreateMan(createMan);
		log.setDescription(systemLog.value());
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		log.setMethod(method.toString());
		Map<String, String[]> params = request.getParameterMap();
		log.setParams(JSON.toJSONString(params));
		String requestIp = request.getRemoteAddr();
		log.setRequestIp(requestIp);
		long beforTime = new Date().getTime(); // 方法执行前
		// 执行方法
		Object obj = pjp.proceed();
		long endTime = new Date().getTime();
		logger.info("执行方法：{}, 执行时间：{}", method.getName(), endTime - beforTime);
		
		log.setExecuteTime(endTime - beforTime);
		if (obj != null) {
			log.setResult(JSON.toJSONString(obj));
		}
		
		log.setType(0);
		
		// 插入数据库
		logService.addLog(log);
		
		return obj;
		
	}
	
}
