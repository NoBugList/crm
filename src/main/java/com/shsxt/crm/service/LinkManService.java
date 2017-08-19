package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.shsxt.crm.dao.LinkManDao;
import com.shsxt.crm.dto.ContactQuery;
import com.shsxt.crm.model.LinkMan;
import com.shsxt.crm.util.AssertUtil;

@Service
public class LinkManService {
	
	@Autowired
	private LinkManDao linkManDao;

	public Map<String, Object> selectForPage(ContactQuery query) {
		PageList<LinkMan> contacts = linkManDao.selectForPage(query, query.buildPageBounds());
		Paginator paginator = contacts.getPaginator(); // 分页对象
		Map<String, Object> result = new HashMap<>();
		result.put("rows", contacts);
		result.put("total", paginator.getTotalCount());
		return result;
	}

	public void save(LinkMan linkman) {
		// 基本参数验证 TODO
		AssertUtil.intIsNotEmpty(linkman.getCusId(), "请选择客户");
		// 联系人唯一验证
		LinkMan linkManFromDb = linkManDao.findByName(linkman.getLinkName(), linkman.getSex());
		AssertUtil.isTrue(linkManFromDb != null, "该客户联系人已存在");
		linkManDao.insert(linkman);
	}

	public void update(LinkMan linkman) {
		
		// 基本参数验证 TODO
		AssertUtil.intIsNotEmpty(linkman.getId(), "请选择一条记录进行更新");
		AssertUtil.intIsNotEmpty(linkman.getCusId(), "请选择客户");
		LinkMan linkManById = linkManDao.findById(linkman.getId());
		AssertUtil.notNull(linkManById, "该记录不存在");
		if (!linkManById.getLinkName().equals(linkman.getLinkName())) {
			// 联系人唯一验证
			LinkMan linkManFromDb = linkManDao.findByName(linkman.getLinkName(), linkman.getSex());
			AssertUtil.isTrue(linkManFromDb != null, "该客户联系人已存在");
		}
		linkManDao.update(linkman);
	}

	public void delete(Integer id) {
		AssertUtil.intIsNotEmpty(id, "请选择删除的记录");
		linkManDao.delete(id);
	}

}
