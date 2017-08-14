package com.shsxt.crm.model;

import java.util.Date;

import com.shsxt.crm.base.BaseModel;

@SuppressWarnings("serial")
public class SaleChance extends BaseModel{
	private Integer id;
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
	private Date assignTime;
	private Integer state;
	private Integer devResult;
	public Integer getId() {
		return id;
	}
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
	public Date getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getDevResult() {
		return devResult;
	}
	public void setDevResult(Integer devResult) {
		this.devResult = devResult;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	



}
