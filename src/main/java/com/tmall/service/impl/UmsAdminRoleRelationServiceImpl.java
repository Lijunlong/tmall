package com.tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsAdminRoleRelationMapper;
import com.tmall.model.UmsAdminRoleRelation;
import com.tmall.service.UmsAdminRoleRelationService;

@Service
public class UmsAdminRoleRelationServiceImpl implements UmsAdminRoleRelationService {
	
	@Autowired
	private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

	@Override
	public Long insertUmsAdminRoleRelation(UmsAdminRoleRelation umsAdminRoleRelation) {
		umsAdminRoleRelationMapper.insertUmsAdminRoleRelation(umsAdminRoleRelation);
		return umsAdminRoleRelation.getId();
	}

	@Override
	public int deleteUmsAdminRoleRelationByAdminId(Long adminId) {
		return umsAdminRoleRelationMapper.deleteUmsAdminRoleRelationByAdminId(adminId);
	}

}
