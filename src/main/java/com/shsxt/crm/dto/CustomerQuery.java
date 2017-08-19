package com.shsxt.crm.dto;

import com.shsxt.crm.base.BaseQuery;

@SuppressWarnings("serial")
public class CustomerQuery extends BaseQuery {
	
	private String customerNo; // 编号
	private String customerName;  // 名称
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
