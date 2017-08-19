package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shsxt.crm.dao.DataDicDao;
import com.shsxt.crm.dto.DataDicQuery;
import com.shsxt.crm.model.DataDic;
import com.shsxt.crm.util.AssertUtil;

@Service
public class DataDicService {

	@Autowired
	private DataDicDao dataDicDao;

	public List<DataDic> findByName(String dataDicName) {
		AssertUtil.isNotEmpty(dataDicName, "请选择查询的名称");
		List<DataDic> dataDics = dataDicDao.findByName(dataDicName);
		return dataDics;
	}

	public Map<String, Object> selectForPage(DataDicQuery query) {
		PageList<DataDic> pageDatas = dataDicDao.selectForPage(query, query.buildPageBounds());
		Map<String, Object> result = new HashMap<>();
		result.put("rows", pageDatas);
		result.put("total", pageDatas.getPaginator().getTotalCount());
		return result;
	}

	public List<DataDic> findAll() {
		return dataDicDao.findAll();
	}

	public void addOrUpdate(DataDic dataDic) {
		 // 基本参数判断
        String dataDicName = dataDic.getDataDicName();
        AssertUtil.isTrue(StringUtils.isBlank(dataDicName), "请输入数据字典的名称");
        String dataDicValue = dataDic.getDataDicValue();
        AssertUtil.isTrue(StringUtils.isBlank(dataDicValue), "请输入数据字典的值");
        if(dataDic.getId() == null) {
            dataDicDao.insert(dataDic);
        } else {
            dataDicDao.update(dataDic);
        }
	}

	public void deleteBatch(String ids) {
		AssertUtil.isTrue(StringUtils.isBlank(ids), "请选择一条记录进行操作");
		dataDicDao.deleteBatch(ids);
	}

}
