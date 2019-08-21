package com.tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsRoleDepartmentRelationMapper;
import com.tmall.model.UmsRoleDepartmentRelation;
import com.tmall.service.UmsRoleDepartmentRelationService;

@Service
public class UmsRoleDepartmentRelationServiceImpl implements UmsRoleDepartmentRelationService {
	
	@Autowired
	private UmsRoleDepartmentRelationMapper umsRoleDepartmentRelationMapper;

	@Override
	public Long insertUmsRoleDepartmentRelation(UmsRoleDepartmentRelation umsRoleDepartmentRelation) {
		return umsRoleDepartmentRelationMapper.insertUmsRoleDepartmentRelation(umsRoleDepartmentRelation);
	}

	@Override
	public int deleteUmsRoleDepartmentRelationByRoleId(Long roleId) {
		return umsRoleDepartmentRelationMapper.deleteUmsRoleDepartmentRelationByRoleId(roleId);
	}
	
}
