package com.tmall.service;

import com.tmall.model.UmsRolePermissionRelation;

public interface UmsRolePermissionRelationService {

	int deleteUmsRolePermissionRelationByRoleId(Long roleId);

	Long insertUmsRolePermissionRelation(UmsRolePermissionRelation umsRolePermissionRelation);

}
