package com.shsxt.crm.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.exception.ParamException;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController{
		
	@ExceptionHandler(value=ParamException.class)
	@ResponseBody
	public ResultInfo handerException(ParamException paramExction) {
		return failure(paramExction);
	}
	
}
