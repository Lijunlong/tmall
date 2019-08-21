package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsRole;

public interface UmsRoleMapper {

	List<UmsRole> selectRoleListByAdminId(@Param("adminId")long userId);

	List<UmsRole> selectUmsRoleListLikeUmsRole(UmsRole umsRole);

	UmsRole selectUmsRoleById(@Param("id")Long id);

	int deleteUmsRoleById(@Param("id")Long id);

	int updateUmsRoleById(UmsRole umsRole);

	Long insertUmsRole(UmsRole umsRole);

}
