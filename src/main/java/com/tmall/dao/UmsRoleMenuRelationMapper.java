package com.tmall.dao;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsRoleMenuRelation;

public interface UmsRoleMenuRelationMapper {

	Long insertUmsRoleMenuRelation(UmsRoleMenuRelation umsRoleMenuRelation);

	int deleteUmsRoleMenuRelationByRoleId(@Param("roleId")Long roleId);

}
