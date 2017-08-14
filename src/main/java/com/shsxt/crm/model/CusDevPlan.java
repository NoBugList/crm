
package com.shsxt.crm.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shsxt.crm.base.BaseModel;

@SuppressWarnings("serial")
public class CusDevPlan extends BaseModel {
	private Integer saleChanceId; // 营销机会ID
	private String planItem; // 内容
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date planDate; // 计划时间
	private String exeAffect; // 执行效果
	public Integer getSaleChanceId() {
		return saleChanceId;
	}
	public void setSaleChanceId(Integer saleChanceId) {
		this.saleChanceId = saleChanceId;
	}
	public String getPlanItem() {
		return planItem;
	}
	public void setPlanItem(String planItem) {
		this.planItem = planItem;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public String getExeAffect() {
		return exeAffect;
	}
	public void setExeAffect(String exeAffect) {
		this.exeAffect = exeAffect;
	}
	
	
}
