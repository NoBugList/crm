package com.shsxt.crm.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SaleChanceQuery implements Serializable {
	
	private String customerName;
	private String overview; 
	private String createMan;
	private Integer state; // 0=未分配 1=已分配
	private Integer rows; // 多少条
	private Integer page; // 当前页
	private String sort; // 排序
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
}
