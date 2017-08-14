package com.shsxt.crm.base;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@SuppressWarnings("serial")
public class BaseQuery implements Serializable {
	
	private static final Integer PAGE = 1;
	private static final Integer ROWS = 10;
	
	private Integer rows; // 多少条
	private Integer page; // 当前页
	private String sort; // 排序
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	/**
	 * 构建分页对象
	 * @return
	 */
	public PageBounds buildPageBounds() {
		// 构建一个分页对象
		if (page == null) {
			page = PAGE;
		}
		if (rows == null) {
			rows = ROWS;
		}
		if (StringUtils.isBlank(sort)) {
			sort = "id.desc"; // 数据库字段.desc/asc
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sort));
		return pageBounds;
	}
	
}
