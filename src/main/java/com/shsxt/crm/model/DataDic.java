package com.shsxt.crm.model;

import com.shsxt.crm.base.BaseModel;

@SuppressWarnings("serial")
// 数据字典
public class DataDic extends BaseModel {
	
	private String dataDicName;
	private String dataDicValue;
	public String getDataDicName() {
		return dataDicName;
	}
	public void setDataDicName(String dataDicName) {
		this.dataDicName = dataDicName;
	}
	public String getDataDicValue() {
		return dataDicValue;
	}
	public void setDataDicValue(String dataDicValue) {
		this.dataDicValue = dataDicValue;
	}
	
}
