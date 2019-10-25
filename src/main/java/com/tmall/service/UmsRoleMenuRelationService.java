package com.tmall.service;

import com.tmall.model.UmsRoleMenuRelation;

public interface UmsRoleMenuRelationService {
	
	Long insertUmsRoleMenuRelation(UmsRoleMenuRelation umsRoleMenuRelation);

	int deleteUmsRoleMenuRelationByRoleId(Long roleId);

}
