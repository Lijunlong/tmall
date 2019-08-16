package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsPermission;

public interface UmsPermissionMapper {

	List<UmsPermission> getUmsPermissionListByAdminId(@Param("adminId")Long adminId);

	List<UmsPermission> selectUmsPermissionListByRoleId(@Param("roleId")Long roleId);

	List<UmsPermission> selectUmsPermissionList(UmsPermission umsPermission);

	List<UmsPermission> selectUmsPermissionListByPid(@Param("pid")Long pid);

	int updateUmsPermission(UmsPermission umsPermisson);

	Long insertUmsPermission(UmsPermission umsPermisson);

	int deleteUmsPermissionById(@Param("id")Long id);

	List<UmsPermission> selectUmsPermissionLikeUmsPermission(UmsPermission umsPermission);

}
