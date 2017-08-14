package com.shsxt.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.CustomerDao;
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

}
