package com.shsxt.crm.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.dto.ContactQuery;
import com.shsxt.crm.model.LinkMan;

public interface LinkManDao {
	
	@Select("select id, cus_id, link_name, sex, zhiwei, office_phone, phone,"
			+ " create_date, update_date from t_customer_linkman "
			+ " where cus_id = #{customerId} and is_valid = 1")
	PageList<LinkMan> selectForPage(ContactQuery query, PageBounds pageBounds);
	
	@Insert("insert into t_customer_linkman (cus_id, link_name, sex, zhiwei, office_phone, phone, "
			+ " create_date, update_date, is_valid) values("
			+ "#{cusId}, #{linkName}, #{sex}, #{zhiwei}, #{officePhone}, #{phone}, now(), now(), 1)")
	void insert(LinkMan linkman);
	
	@Update("update t_customer_linkman set link_name = #{linkName}, sex = #{sex}, "
			+ "zhiwei = #{zhiwei}, office_phone = #{officePhone}, phone = #{phone}, "
			+ "update_date = now() where id = #{id}")
	void update(LinkMan linkman);
	
	@Update("update t_customer_linkman set is_valid = 0 where id = #{id}")
	void delete(@Param(value="id")Integer id);
	
	@Select("select id, cus_id, link_name, sex, zhiwei, office_phone, phone,"
			+ " create_date, update_date from t_customer_linkman "
			+ " where link_name = #{linkName} and sex = #{sex} and is_valid = 1")
	LinkMan findByName(@Param(value="linkName")String linkName, @Param(value="sex")String sex);
	
	@Select("select id, cus_id, link_name, sex, zhiwei, office_phone, phone,"
			+ " create_date, update_date from t_customer_linkman "
			+ " where id = #{id} and is_valid = 1")
	LinkMan findById(@Param(value="id")Integer id);

}
