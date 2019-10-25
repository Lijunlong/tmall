package com.tmall.service;

import com.tmall.model.UmsAdminRoleRelation;

public interface UmsAdminRoleRelationService {

	Long insertUmsAdminRoleRelation(UmsAdminRoleRelation umsAdminRoleRelation);

	int deleteUmsAdminRoleRelationByAdminId(Long adminId);

}
