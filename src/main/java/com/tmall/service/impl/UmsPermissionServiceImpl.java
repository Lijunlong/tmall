package com.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsPermissionMapper;
import com.tmall.model.UmsPermission;
import com.tmall.service.UmsPermissionService;

@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {

    @Autowired
    private UmsPermissionMapper umsPermissionMapper;

	@Override
	public List<UmsPermission> getUmsPermissionList(Long user_id) {
		return umsPermissionMapper.getUmsPermissionList(user_id);
	}
    
}
