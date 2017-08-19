package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.dao.CustomerDao;
import com.shsxt.crm.dto.CustomerQuery;
import com.shsxt.crm.model.Customer;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.vo.CustomerVO;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * 查询所有的客户
	 * @return
	 */
	public List<CustomerVO> findAll() {
		
		List<CustomerVO> customers = customerDao.findAll();
		
		return customers;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	public Map<String, Object> selectForPage(CustomerQuery query) {
		// 构建分页参数
		PageBounds pageBounds = query.buildPageBounds();
		// 调用sql
		PageList<Customer> customers = customerDao.selectForPage(query, pageBounds);
		Map<String, Object> result = new HashMap<>();
		result.put("rows", customers);
		result.put("total", customers.getPaginator().getTotalCount());
		return result;
	}
	
	/**
	 * 添加
	 * @param customer
	 */
	public void add(Customer customer) {
		// 基本参数验证
		// TODO
		// 客户名唯一验证
		Customer customerByName = customerDao.findByName(customer.getName().trim());
		AssertUtil.isTrue(customerByName != null, "该客户已存在");
		customer.setCreateDate(new Date());
		customer.setUpdateDate(new Date());
		customer.setIsValid(1);
		customer.setState(0);
		// 生成一个客户编号“KH” + 日期 + 三位随机数
		String khno = "KH" + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") 
			+ RandomUtils.nextInt(100, 999);
		customer.setKhno(khno);
		// 保存
		customerDao.insert(customer);
	}
	
	/**
	 * 修改
	 * @param customer
	 */
	public void update(Customer customer) {
		// 基本参数验证
		// TODO
		Integer id = customer.getId();
		AssertUtil.intIsNotEmpty(id, "请选择记录进行修改");
		// 先从数据库中获取
		Customer customerFromDB = customerDao.findById(id);
		AssertUtil.notNull(customerFromDB, "该记录不存在，请确认后修改");
		// 客户名唯一验证
		if (!customer.getName().equals(customerFromDB.getName())) {
			Customer customerByName = customerDao.findByName(customer.getName().trim());
			AssertUtil.isTrue(customerByName != null, "该客户已存在");
		}
		
		// 修改
		customer.setUpdateDate(new Date());
		customerDao.update(customer);
	}

	public void delete(String ids) {
		AssertUtil.isNotEmpty(ids, "请选择记录进行修改");
		customerDao.delete(ids);
	}

}
