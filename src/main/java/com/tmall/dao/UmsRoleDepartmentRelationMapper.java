package com.tmall.dao;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsRoleDepartmentRelation;

public interface UmsRoleDepartmentRelationMapper {
	
	Long insertUmsRoleDepartmentRelation(UmsRoleDepartmentRelation umsRoleDepartmentRelation);

	int deleteUmsRoleDepartmentRelationByRoleId(@Param("roleId")Long roleId);
	
}
