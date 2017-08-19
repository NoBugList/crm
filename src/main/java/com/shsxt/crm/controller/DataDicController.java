package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.model.DataDic;
import com.shsxt.crm.service.DataDicService;

@Controller
@RequestMapping("datadic")
public class DataDicController extends BaseController {
	
	
	@Autowired
	private DataDicService dataDicService;
	
	@RequestMapping("index")
	public String index() {
		return "data_dic";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list() {
		return null;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo add(DataDic dataDic) {
		dataDicService.addOrUpdate(dataDic);
		return success("添加成功");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(DataDic dataDic) {
		dataDicService.addOrUpdate(dataDic);
		return success("修改成功");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(String ids) {
		dataDicService.deleteBatch(ids);
		return success("删除成功");
	}
	
	@RequestMapping("dataDicComboList")
	@ResponseBody
	public List<DataDic> dataDicComboList(String dataDicName) {
		List<DataDic> dataDics = dataDicService.findByName(dataDicName);
		return dataDics;
	}
	
}
