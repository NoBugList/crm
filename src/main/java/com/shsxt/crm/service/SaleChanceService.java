package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.dto.SaleChanceDto;
import com.shsxt.crm.dto.SaleChanceQuery;
import com.shsxt.crm.model.SaleChance;
import com.shsxt.crm.util.AssertUtil;

@Service
public class SaleChanceService {
	
	@Autowired
	private SaleChanceDao saleChanceDao;
	
	private static Logger logger = LoggerFactory.getLogger(SaleChanceService.class);

	public Map<String, Object> selectForPage(SaleChanceQuery query) {
		
		// 构建分页对象
		PageBounds pageBounds = query.buildPageBounds();
		
		// 查询
		List<SaleChance> saleChances = saleChanceDao.selectForPage(query, pageBounds);
		PageList<SaleChance> result = (PageList<SaleChance>) saleChances;
		
		// 返回分页结果
		Paginator paginator = result.getPaginator();
		Map<String, Object> map = new HashMap<>();
		map.put("paginator", paginator);
		map.put("rows", result);
		map.put("total", paginator.getTotalCount());
		
		return map;
	}
	
	/**
	 * 添加
	 * @param saleChanceDto
	 */
	public void add(SaleChanceDto saleChanceDto, String loginUserName) {
		// 参数验证
		checkParams(saleChanceDto.getCustomerId(), saleChanceDto.getCustomerName(), 
				saleChanceDto.getCgjl());
		
		// 判断分配状态  根据是否有指定人判断
		String assignMan = saleChanceDto.getAssignMan();
		int state = 0; // 未分配
		Date assignTime = null;
		if (StringUtils.isNoneBlank(assignMan)) {
			state = 1; // 已分配
			assignTime = new Date();
		}
		
		SaleChance saleChance = new SaleChance();
		BeanUtils.copyProperties(saleChanceDto, saleChance); // 属性拷贝
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
		saleChance.setCreateMan(loginUserName);
//		saleChance.setCreateDate(new Date());
//		saleChance.setUpdateDate(new Date());
		// 保存
		int count = saleChanceDao.insert(saleChance);
		logger.debug("插入的记录数为：{}, 主键为：", count, saleChance.getId());
	}
	
	/**
	 * 更新
	 * @param saleChance
	 */
	public void update(SaleChance saleChance) {
		
		// 基本参数验证
		Integer id = saleChance.getId();
		AssertUtil.intIsNotEmpty(id, "请选择记录进行更新");
		checkParams(saleChance.getCustomerId(), saleChance.getCustomerName(), 
				saleChance.getCgjl());
		
		// 判断分配状态  根据是否有指定人判断
		// 选查询该记录是否分配过 先查询 在判定
		
		checkState(saleChance);
		saleChance.setUpdateDate(new Date());

		
		// 更新
		saleChanceDao.update(saleChance);
	}
	
	
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(String ids) {
		// 参数验证
		AssertUtil.isNotEmpty(ids, "请选择记录进行删除");
		// 执行sql
		saleChanceDao.delete(ids);
	}
	
	/**
	 * 根据主键ID获取营销机会信息
	 * @param saleChanceId
	 * @return
	 */
	public SaleChance findById(Integer saleChanceId) {
		AssertUtil.intIsNotEmpty(saleChanceId, "请选择营销机会进行操作");
		SaleChance saleChance = saleChanceDao.findById(saleChanceId);
		return saleChance;
	}
	
	/**
	 * 更新开发状态
	 * @param saleChanceId
	 * @param devResult
	 */
	public void updateDevResult(Integer saleChanceId, int devResult) {
		AssertUtil.intIsNotEmpty(saleChanceId, "请选择营销机会进行操作");
		saleChanceDao.updateDevResult(saleChanceId, devResult);
	}
	
	/**
	 * 基本参数验证
	 * @param customerId
	 * @param cutomerName
	 * @param cgjl
	 */
	private void checkParams(Integer customerId, 
			String cutomerName, Integer cgjl) {

		AssertUtil.intIsNotEmpty(customerId, "请选择客户");
		AssertUtil.isNotEmpty(cutomerName, "请选择客户");
		AssertUtil.intIsNotEmpty(cgjl, "请输入成功几率");
		
	}
	
	
	/**
	 * 验证分配状态
	 * @param saleChance
	 */
	private void checkState(SaleChance saleChance) {
		SaleChance saleChanceFromDB = saleChanceDao.findById(saleChance.getId());
		AssertUtil.notNull(saleChanceFromDB, "该记录不存在，请重新选择");
		
		int state = saleChanceFromDB.getState();
		Date assignTime = null;
		String assignMan = saleChanceFromDB.getAssignMan();
		if (saleChanceFromDB.getState() == 0) { // 未分配
			if (StringUtils.isNoneBlank(saleChance.getAssignMan())) {
				state = 1; // 已分配
				assignTime = new Date();
			}
			
		} else { // 已分配
			if (!saleChanceFromDB.getAssignMan().equals(saleChance.getAssignMan())) { // 页面传入的指派人和数据库中的指派人不相等
				if (StringUtils.isBlank(saleChance.getAssignMan())) { // 客户端没有传值
					state = 0; // 处于未分配的状态
					assignTime = null;
				} else { // 客户端传值
					assignMan = saleChance.getAssignMan();
					assignTime = new Date();
				}
			} else {
				assignTime = saleChanceFromDB.getAssignTime();
			}
		}
		saleChance.setAssignMan(assignMan);
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
	}
}
