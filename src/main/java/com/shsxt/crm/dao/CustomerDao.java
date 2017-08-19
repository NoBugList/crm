package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.dto.CustomerQuery;
import com.shsxt.crm.model.Customer;
import com.shsxt.crm.vo.CustomerVO;

public interface CustomerDao {
	
	@Select("SELECT id, `name` FROM t_customer where is_valid = 1")
	List<CustomerVO> findAll();
	
	PageList<Customer> selectForPage(CustomerQuery query, PageBounds pageBounds);
	
	@Select("select id, name from t_customer where is_valid = 1 and name = #{name}")
	Customer findByName(@Param(value="name") String name);
	
	void insert(Customer customer);
	
	@Select("select id, name, khno from t_customer where is_valid = 1 and id = #{id}")
	Customer findById(@Param(value="id")Integer id);
	
	void update(Customer customer);
	
	@Update("update t_customer set is_valid = 0, update_date = now() where id in (${ids})")
	void delete(@Param(value="ids")String ids);
	
	
	List<Customer> findLossCustomer();
	
	List<Customer> findLossCustomerNoOrderLongTime();
	
	@Update("update t_customer set state = 2, update_date = now() where id in (${ids})")
	void updateStates(@Param(value="ids")String ids);
	
	@Update("update t_customer set state = 1, update_date = now() where khno = #{cusNo}")
	void updateLossState(@Param(value="cusNo")String cusNo);
	
//	PageList<CustomerGx> khgxReport(ReportGxQuery query, PageBounds pageBounds);
//
//	List<CustomerGc> khgcReport();

}
