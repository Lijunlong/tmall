package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsRole;

public interface UmsRoleMapper {

	List<UmsRole> selectRoleListByAdminId(@Param("adminId")long userId);

	List<UmsRole> selectUmsRoleList(UmsRole umsRole);
	
}
