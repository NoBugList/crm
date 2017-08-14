package com.shsxt.crm.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserVO implements Serializable {
	private Integer id;
	private String trueName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
}
