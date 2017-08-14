package com.shsxt.crm.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SaleChanceDto implements Serializable {
	
	private String chanceSource;
	private Integer customerId;
	private String customerName;
	private Integer cgjl;
	private String overview;
	private String linkMan;
	private String linkPhone;
	private String description;
	private String createMan;
	private String assignMan;
	public String getChanceSource() {
		return chanceSource;
	}
	public void setChanceSource(String chanceSource) {
		this.chanceSource = chanceSource;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCgjl() {
		return cgjl;
	}
	public void setCgjl(Integer cgjl) {
		this.cgjl = cgjl;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public String getAssignMan() {
		return assignMan;
	}
	public void setAssignMan(String assignMan) {
		this.assignMan = assignMan;
	}
}
