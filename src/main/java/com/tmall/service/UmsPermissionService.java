package com.tmall.service;

import java.util.List;
import java.util.Map;

import com.tmall.dto.UmsPermissionParam;
import com.tmall.model.UmsPermission;

public interface UmsPermissionService {

	List<UmsPermission> getUmsPermissionListByAdminId(Long userId);

	List<UmsPermission> selectUmsPermissionListByRoleId(Long roleId);
	
	List<UmsPermission> getPermissionTree(String name);
	
	/**
	 * 返回全部的权限，新增角色时下拉选择
	 * @return 返回全部的权限，新增角色时下拉选择树
	 */
	List<Map<String, Object>> buildTree();
	
	/**
	 * 修改权限
	 * @param id
	 * @param umsPermissonParam
	 * @return
	 */
	int update(Long id, UmsPermissionParam umsPermissionParam);
	
	/**
	 * 添加权限
	 * @param umsPermissionParam
	 * @return
	 */
	Long create(UmsPermissionParam umsPermissionParam);
	
	/**
	 * 删除权限
	 * @param permissionId 权限id
	 * @return
	 */
	int delete(Long permissionId);
	
	/**
	 * 通过用户id获取权限名称集合
	 * @param adminId 用户id
	 * @return 权限名称集合
	 */
	String[] getUmsPermissionNameByAdminId(Long adminId);

}
