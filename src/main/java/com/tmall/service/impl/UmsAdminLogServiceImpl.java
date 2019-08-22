package com.tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsAdminLogMapper;
import com.tmall.service.UmsAdminLogService;

@Service
public class UmsAdminLogServiceImpl implements UmsAdminLogService {
	
	@Autowired
	private UmsAdminLogMapper umsAdminLogMapper;
	
}
