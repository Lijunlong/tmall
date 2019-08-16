package com.tmall.service;

import java.util.List;

import com.tmall.dto.UmsAdminParam;
import com.tmall.model.UmsAdmin;

public interface UmsAdminService {
	
	/**
	 * 查询用户
	 * @param deptId 部门id
	 * @param username 用户名
	 * @param email 邮箱
	 * @param enabled 状态，1启用，0禁用
	 * @param pageNum 
	 * @param pageSize 
	 * @return 用户列表
	 */
	List<UmsAdmin> getUserList(Long deptId, String username, String email,Integer enabled, Integer pageSize, Integer pageNum);
	
	/**
	 * 添加用户
	 * @param umsAdminParam
	 * @return
	 */
	int create(UmsAdminParam umsAdminParam);
	/**
	 * 修改用户
	 * @param umsAdminId
	 * @param umsAdminParam
	 * @return
	 */
	int update(Long umsAdminId, UmsAdminParam umsAdminParam);
	
	/**
	 * 删除用户
	 * @param umsAdminId
	 * @return
	 */
	int delete(Long umsAdminId);
}
