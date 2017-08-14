package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.dto.SaleChanceDto;
import com.shsxt.crm.dto.SaleChanceQuery;
import com.shsxt.crm.model.SaleChance;
import com.shsxt.crm.util.AssertUtil;

@SuppressWarnings("all")
@Service
public class SaleChanceService {

	@Autowired
	private SaleChanceDao saleChanceDao;

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public Map<String, Object> selectForPage(SaleChanceQuery query) {

		// 构建一个分页对象
		Integer page = query.getPage();
		if (page == null) {
			page = 1;
		}
		Integer pageSize = query.getRows();
		if (pageSize == null) {
			pageSize = 10;
		}
		String sort = query.getSort();
		if (StringUtils.isBlank(sort)) {
			sort = "id.asc"; // 数据库字段.desc/asc
		}
		PageBounds pageBounds = new PageBounds(page, pageSize, Order.formString(sort));

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
	 * 添加记录
	 * @param saleChanceDto
	 * @param loginUserName
	 */
	public void add(SaleChanceDto saleChanceDto,String loginUserName) {
		//参数验证
		checkParams(saleChanceDto.getCustomerId(), saleChanceDto.getCustomerName(), saleChanceDto.getCgjl());
		//判断分配状态
		String assignMan = saleChanceDto.getAssignMan();
		int state = 0; // 未分配
		Date assignTime = null;
		if (StringUtils.isNoneBlank(assignMan)) {
			state = 1; // 已分配
			assignTime = new Date();
		}
		//设置实例对象
		SaleChance saleChance = new SaleChance();
		BeanUtils.copyProperties(saleChanceDto, saleChance);
		saleChance.setAssignMan(assignMan);
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
		//执行保存
		int count = saleChanceDao.insert(saleChance);
	}
	
	/**
	 * 更新记录
	 * @param saleChance
	 */
	public void update(SaleChance saleChance) {
		//参数验证
		Integer id = saleChance.getId();
		AssertUtil.intIsNotEmpty(id, "请选择记录进行更新");
		checkParams(saleChance.getCustomerId(), saleChance.getCustomerName(), saleChance.getCgjl());
		//状态验证
		checkState(saleChance);
		saleChance.setUpdateDate(new Date());
		//执行更新
		saleChanceDao.update(saleChance);
	}
	
	/**
	 * 删除记录
	 * 
	 * @param ids
	 */
	public void delete(String ids) {
		// 参数验证
		AssertUtil.isNotEmpty(ids, "请选择记录进行删除");
		// 执行sql
		saleChanceDao.delete(ids);
	}
	
	
	/**
	 * 参数验证
	 * @param customerId
	 * @param customerName
	 * @param cgjl
	 */
	private void checkParams(Integer customerId,String customerName,Integer cgjl) {
		AssertUtil.intIsNotEmpty(customerId, "请选择客户");
		AssertUtil.isNotEmpty(customerName, "请选择客户");
		AssertUtil.intIsNotEmpty(cgjl, "请输入成功几率");
	}
	
	/**
	 * 分配状态验证
	 * @param saleChance
	 */
	private void checkState(SaleChance saleChance) {
		SaleChance saleChanceFromDB = saleChanceDao.findById(saleChance.getId());
		AssertUtil.notNull(saleChanceFromDB, "该记录不存在，重新选择");
		
		Integer state =saleChanceFromDB.getState();
		Date assignTime = null;
		String assignMan = saleChanceFromDB.getAssignMan();
		
		if(saleChanceFromDB.getState()==0) {//未分配
			if(StringUtils.isNoneBlank(saleChance.getAssignMan())) {//已分配
				state = 1;
				assignTime = new Date();	
			}else {//已分配
				// 页面传入的指派人和数据库中的指派人不相等
				if(!saleChanceFromDB.getAssignMan().equals(saleChance.getAssignMan())) {
					if(StringUtils.isNoneBlank(saleChance.getAssignMan())) {//客户端未传值
						state = 0;
						assignTime = null;
					}else {//客户端传值
						state = 1;
						assignMan = saleChance.getAssignMan();
						assignTime = new Date();
					}
				}
			}

		}
		saleChance.setAssignMan(assignMan);
		saleChance.setAssignTime(assignTime);
		saleChance.setState(state);
	}

}
