package com.tmall.dao;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsAdminRoleRelation;

public interface UmsAdminRoleRelationMapper {

	Long insertUmsAdminRoleRelation(UmsAdminRoleRelation umsAdminRoleRelation);
	
	/**
	 * 通过用户id删除用户与角色的关系表
	 * @param adminId
	 * @return
	 */
	int deleteUmsAdminRoleRelationByAdminId(@Param("adminId")Long adminId);

}
