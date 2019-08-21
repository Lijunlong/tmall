package com.tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsRolePermissionRelationMapper;
import com.tmall.model.UmsRolePermissionRelation;
import com.tmall.service.UmsRolePermissionRelationService;

@Service
public class UmsRolePermissionRelationServiceImpl implements UmsRolePermissionRelationService {
	
	@Autowired
	private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;
	
	@Override
	public int deleteUmsRolePermissionRelationByRoleId(Long roleId) {
		return umsRolePermissionRelationMapper.deleteUmsRolePermissionRelationByRoleId(roleId);
	}

	@Override
	public Long insertUmsRolePermissionRelation(UmsRolePermissionRelation umsRolePermissionRelation) {
		return umsRolePermissionRelationMapper.insertUmsRolePermissionRelation(umsRolePermissionRelation);
	}

}
