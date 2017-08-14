package com.shsxt.crm.dao.provider;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shsxt.crm.dto.SaleChanceQuery;

public class SaleChanceProvider {
	
	public static final String COLUMNS = "t.id, t.customer_id, t.customer_name, t.overview, t.link_man, t.link_phone, "
			+ " t.create_man, t.create_date, t.assign_man,t.assign_time,t.state, t.cgjl, t.description, t.chance_source";
	private static Logger logger = LoggerFactory.getLogger(SaleChanceProvider.class);
	
	public String selectForPage(final SaleChanceQuery query) {
		
		String sql = new SQL() {{
			SELECT(COLUMNS);
			FROM("t_sale_chance t");
			WHERE("is_valid = 1");
			if (StringUtils.isNoneBlank(query.getCustomerName())) {
				AND().WHERE("customer_name like '%"+ query.getCustomerName() +"%'");
			}
			if (StringUtils.isNoneBlank(query.getOverview())) {
				AND().WHERE("overview like '%"+ query.getOverview() +"%'");
			}
			if (StringUtils.isNoneBlank(query.getCreateMan())) {
				AND().WHERE("create_man like '%"+ query.getCreateMan() +"%'");
			}
			if (query.getState() != null) {
				AND().WHERE("state = #{state}");
			}
			
		}}.toString();
		
		logger.debug("打印sql"+sql);
		
		return sql;
		
	}
	
	
	public String findById(@Param(value="id") Integer id) {
		
		String sql = new SQL() {{
			SELECT(COLUMNS);
			FROM("t_sale_chance t");
			WHERE("is_valid = 1 and id = #{id}");
		}}.toString();
		
		logger.debug("打印sql"+sql);
		
		return sql;
		
	}

	
}
