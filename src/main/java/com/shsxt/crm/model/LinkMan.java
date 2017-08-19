package com.shsxt.crm.model;

import com.shsxt.crm.base.BaseModel;

/**
 * Created by Tony on 2016/8/24.
 */
@SuppressWarnings("serial")
public class LinkMan extends BaseModel {
    private Integer cusId; // 所属客户
    private String linkName; // 姓名
    private String sex; // 性别
    private String zhiwei; // 职位
    private String officePhone; // 办公电话
    private String phone; // 手机


    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getLinkName() {
        return linkName;
    }
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getZhiwei() {
        return zhiwei;
    }
    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }
    public String getOfficePhone() {
        return officePhone;
    }
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
