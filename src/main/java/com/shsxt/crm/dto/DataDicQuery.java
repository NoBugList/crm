package com.shsxt.crm.dto;

import com.shsxt.crm.base.BaseQuery;

/**
 * Created by Tony on 2016/8/24.
 */
public class DataDicQuery extends BaseQuery {
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
