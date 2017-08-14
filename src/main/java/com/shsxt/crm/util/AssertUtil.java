package com.shsxt.crm.util;

import org.apache.commons.lang3.StringUtils;

import com.shsxt.crm.constant.Constant;
import com.shsxt.crm.exception.ParamException;

public class AssertUtil {
	
	
	/**
	 * 对象不能为空
	 * @param value
	 * @param code
	 * @param message
	 */
	public static void notNull(Object value, String... message) {
		if (value == null) {
			throw new ParamException(loadMessage(message));
		}
	}
	
	/**
	 * 对象不能为空
	 * @param value
	 * @param code
	 * @param message
	 */
	public static void notNull(Object value, Integer code, String... message) {
		if (value == null) {
			throw new ParamException(code, loadMessage(message));
		}
	}
	
	/**
	 * 字符串不能为空
	 * @param value
	 * @param code
	 * @param message
	 */
	public static void isNotEmpty(String value, String... message) {
		if (StringUtils.isBlank(value)) {
			throw new ParamException(loadMessage(message));
		}
	}
	
	/**
	 * 字符串不能为空
	 * @param value
	 * @param code
	 * @param message
	 */
	public static void isNotEmpty(String value, Integer code, String... message) {
		if (StringUtils.isBlank(value)) {
			throw new ParamException(code, loadMessage(message));
		}
	}
	
	/**
	 * Integer不能为空
	 * @param value
	 * @param message
	 */
	public static void intIsNotEmpty(Integer value, String... message) {
		isTrue(value == null || value < 1, loadMessage(message));
	}
	
	public static void isTrue(boolean isTrue, String... message) {
		if (isTrue) {
			throw new ParamException(loadMessage(message));
		}
	}
	
	
	private static String loadMessage (String... message) {
		String msg = "";
		if (message != null && message.length > 0 && StringUtils.isNoneBlank(message[0])) {
			msg = message[0];
		} else {
			msg = Constant.ERROR_MESSAGE;
		}
		return msg;
	}
	
}
