package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.constant.DevResult;
import com.shsxt.crm.dao.CusDevPlanDao;
import com.shsxt.crm.model.CusDevPlan;
import com.shsxt.crm.util.AssertUtil;

@Service
public class CusDevPlanService {
	
	@Autowired
	private CusDevPlanDao cusDevPlanDao;
	
	@Autowired
	private SaleChanceService saleChanceService;
	
	/**
	 * 获取营销机会下的客户开发计划项
	 * @param saleChanceId
	 * @return
	 */
	public Map<String, Object> findBySaleChanceId(Integer saleChanceId) {
		AssertUtil.intIsNotEmpty(saleChanceId, "请选择营销机会进行操作");
		List<CusDevPlan> cusDevPlans = cusDevPlanDao.findBySaleChanceId(saleChanceId);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", cusDevPlans);
		return result; 
	}
	
	/**
	 * 添加
	 * @param cusDevPlan
	 */
	public void add(CusDevPlan cusDevPlan) {
		// 基本参数验证
		Integer saleChanceId = cusDevPlan.getSaleChanceId();
		AssertUtil.intIsNotEmpty(saleChanceId, "请选择营销机会进行操作");
		Date planDate = cusDevPlan.getPlanDate();
		AssertUtil.notNull(planDate, "请选择计划日期");
		String planItem = cusDevPlan.getPlanItem();
		AssertUtil.isNotEmpty(planItem, "请输入计划内容");
		String exeAffect = cusDevPlan.getExeAffect();
		AssertUtil.isNotEmpty(exeAffect, "请输入执行效果");
		// 插入数据库
		cusDevPlanDao.insert(cusDevPlan);
		
		// 修改营销机会管理的开发状态
		saleChanceService.updateDevResult(saleChanceId, DevResult.DEVING.getDevResult());
	}
	
	/**
	 * 修改
	 * @param cusDevPlan
	 */
	public void update(CusDevPlan cusDevPlan) {
		
		// 基本参数验证
		Integer id = cusDevPlan.getId();
		AssertUtil.intIsNotEmpty(id, "请选择记录进行操作");
		Integer saleChanceId = cusDevPlan.getSaleChanceId();
		AssertUtil.intIsNotEmpty(saleChanceId, "请选择营销机会进行操作");
		Date planDate = cusDevPlan.getPlanDate();
		AssertUtil.notNull(planDate, "请选择计划日期");
		String planItem = cusDevPlan.getPlanItem();
		AssertUtil.isNotEmpty(planItem, "请输入计划内容");
		String exeAffect = cusDevPlan.getExeAffect();
		AssertUtil.isNotEmpty(exeAffect, "请输入执行效果");
		
		// 更新
		cusDevPlanDao.update(cusDevPlan);
		
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id) {
		AssertUtil.intIsNotEmpty(id, "请选择记录进行删除");
		cusDevPlanDao.delete(id);
	}
	
}
