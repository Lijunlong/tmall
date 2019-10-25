package com.tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsRoleMenuRelationMapper;
import com.tmall.model.UmsRoleMenuRelation;
import com.tmall.service.UmsRoleMenuRelationService;

@Service
public class UmsRoleMenuRelationServiceImpl implements UmsRoleMenuRelationService {
	
	@Autowired
	private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;

	@Override
	public Long insertUmsRoleMenuRelation(UmsRoleMenuRelation umsRoleMenuRelation) {
		return umsRoleMenuRelationMapper.insertUmsRoleMenuRelation(umsRoleMenuRelation);
	}

	@Override
	public int deleteUmsRoleMenuRelationByRoleId(Long roleId) {
		return umsRoleMenuRelationMapper.deleteUmsRoleMenuRelationByRoleId(roleId);
	}
	
}
