package com.tmall.service;

import java.util.List;

import com.tmall.dto.UmsRoleParam;
import com.tmall.model.UmsRole;

public interface UmsRoleService {
	
	
	List<UmsRole> selectRoleListByAdminId(long userId);
	
	List<UmsRole> getRoles(String name, Integer pageSize, Integer pageNum);
	
	/**
	 * 修改角色菜单
	 * @param id 角色id
	 * @param umsRoleParam 角色前端参数集
	 * @return
	 */
	int updateRoleMenu(Long id, UmsRoleParam umsRoleParam);
	
	/**
	 * 修改角色权限
	 * @param id 角色id
	 * @param umsRoleParam 角色前端参数集
	 * @return
	 */
	int updateRolePermission(Long id, UmsRoleParam umsRoleParam);
	
	/**
	 * 获取单个角色
	 * @param id 角色id
	 * @return
	 */
	UmsRole getSingleRole(Long id);
	
	/**
	 * 删除角色
	 * @param id 角色id
	 * @return
	 */
	int deleteRole(Long id);
	
	/**
	 * 修改角色
	 * @param id 角色id
	 * @param umsRoleParam 角色前端参数集
	 * @return
	 */
	int updateRole(Long id, UmsRoleParam umsRoleParam);
	
	/**
	 * 添加角色
	 * @param umsRoleParam 角色前端参数集
	 * @return
	 */
	int insertRole(UmsRoleParam umsRoleParam);

}
