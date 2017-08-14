package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.shsxt.crm.dao.provider.SaleChanceProvider;
import com.shsxt.crm.dto.SaleChanceQuery;
import com.shsxt.crm.model.SaleChance;

public interface SaleChanceDao {
	
//	@Select("select * from t_sale_chance where is_valid = 1")
	@SelectProvider(type = SaleChanceProvider.class, method="selectForPage")
	List<SaleChance> selectForPage(SaleChanceQuery query, PageBounds pageBounds);
	
	@Insert("insert into t_sale_chance (chance_source,customer_id,customer_name,cgjl," + 
			"overview,link_man,link_phone,description,create_man,assign_man,assign_time," + 
			"state,dev_result,is_valid,create_date,update_date) values(" + 
			"#{chanceSource},#{customerId},#{customerName},#{cgjl}," + 
			"#{overview},#{linkMan},#{linkPhone},#{description}," + 
			"#{createMan},#{assignMan},#{assignTime},#{state},0,1,now(),now())")
	@Options(useGeneratedKeys = true, keyProperty="id")
	int insert(SaleChance saleChance);
	
	@SelectProvider(type = SaleChanceProvider.class, method="findById")
	SaleChance findById(Integer id);
	
	@Update("update t_sale_chance set chance_source = #{chanceSource}, customer_id = #{customerId}, "
			+ "customer_name = #{customerName}, cgjl = #{cgjl}, overview = #{overview},"
			+ "link_man = #{linkMan}, link_phone = #{linkPhone}, description = #{description}, "
			+ "create_man = #{createMan}, assign_man = #{assignMan}, assign_time = #{assignTime},"
			+ " state=#{state},update_date = #{updateDate} where id = #{id}")
	int update(SaleChance saleChance);
	
	@Update("update t_sale_chance set is_valid = 0 where id in (${ids})")
	void delete(@Param(value="ids") String ids);
	
	@Update("update t_sale_chance set dev_result = #{devResult} where id  = #{id}")
	void updateDevResult(@Param(value="id")Integer saleChanceId, @Param(value="devResult")int devResult);
	
}
