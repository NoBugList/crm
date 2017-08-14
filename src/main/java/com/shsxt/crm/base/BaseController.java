package com.shsxt.crm.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shsxt.crm.constant.Constant;
import com.shsxt.crm.exception.ParamException;

public class BaseController {
	//sxtezre
	@ModelAttribute
	protected void preMethod(HttpServletRequest request, Model model) {
		String ctx = request.getContextPath();
		model.addAttribute("ctx", ctx);
	}
	
	protected ResultInfo failure(Integer errorCode, String errorMessage) {
		ResultInfo resultInfo = new ResultInfo(errorCode,errorMessage,errorMessage);
		return resultInfo;
	}
	
	protected ResultInfo failure(String errorMessage) {
		ResultInfo resultInfo = failure(Constant.ERROR_CODE, errorMessage);
		return resultInfo;
	}
	
	protected ResultInfo failure(ParamException e) {
		ResultInfo resultInfo = failure(e.getErrorCode(),e.getMessage());
		return resultInfo;
	}
	
	protected ResultInfo success(Object result) {
		ResultInfo resultInfo = new ResultInfo(Constant.SUCCESS_CODE,Constant.SUCCESS_MESSAGE,result);
		return resultInfo;
	}

}
