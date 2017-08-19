package com.shsxt.crm.dto;

import com.shsxt.crm.base.BaseQuery;

@SuppressWarnings("serial")
public class ContactQuery extends BaseQuery {
	
	private Integer customerId; // 编号

	public Integer getCustomerId() {
		return customerId;	
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}
