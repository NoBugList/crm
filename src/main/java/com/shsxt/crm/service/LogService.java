package com.shsxt.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.LogDao;
import com.shsxt.crm.model.Log;

@Service
public class LogService {
	
	@Autowired
	private LogDao logDao;
	
	public void addLog(Log log) {
		logDao.addLog(log);
	}
}
