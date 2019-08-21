package com.tmall.service;

import com.tmall.model.UmsRoleDepartmentRelation;

public interface UmsRoleDepartmentRelationService {
	
	Long insertUmsRoleDepartmentRelation(UmsRoleDepartmentRelation umsRoleDepartmentRelation);

	int deleteUmsRoleDepartmentRelationByRoleId(Long roleId);
	
}
