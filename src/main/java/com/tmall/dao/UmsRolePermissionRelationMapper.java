package com.tmall.dao;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsRolePermissionRelation;

public interface UmsRolePermissionRelationMapper {

	int deleteUmsRolePermissionRelationByRoleId(@Param("roleId")Long roleId);
	
	Long insertUmsRolePermissionRelation(UmsRolePermissionRelation umsRolePermissionRelation);
}
