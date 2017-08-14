package com.shsxt.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.ResultInfo;
import com.shsxt.crm.model.CusDevPlan;
import com.shsxt.crm.model.SaleChance;
import com.shsxt.crm.service.CusDevPlanService;
import com.shsxt.crm.service.SaleChanceService;

@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {
	
	@Autowired
	private SaleChanceService saleChanceService;
	@Autowired
	private CusDevPlanService cusDevPlanService;
	
	
	
	@RequestMapping("index")
	public String index(Integer saleChanceId, Integer type, Model model) {
		SaleChance saleChance = saleChanceService.findById(saleChanceId);
		model.addAttribute("saleChance", saleChance);
		model.addAttribute("show", type); // type==0就要显示开发按钮 如果==0只是查看
		return "cus_dev_plan";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(Integer saleChanceId) {
		Map<String, Object> result = cusDevPlanService.findBySaleChanceId(saleChanceId);
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public ResultInfo add(CusDevPlan cusDevPlan) {
		cusDevPlanService.add(cusDevPlan);
		return success("添加成功");
	}
	
	@RequestMapping("update")
	@ResponseBody
	public ResultInfo update(CusDevPlan cusDevPlan) {
		cusDevPlanService.update(cusDevPlan);
		return success("修改成功");
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public ResultInfo delete(Integer id) {
		cusDevPlanService.delete(id);
		return success("删除成功");
	}
	
}
