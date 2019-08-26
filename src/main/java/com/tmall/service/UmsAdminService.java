package com.tmall.service;

import java.util.List;
import java.util.Map;

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
	 * @param umsAdminParam 前端用户参数集
	 * @param creator 创建人
	 * @return
	 */
	int create(UmsAdminParam umsAdminParam, String creator);
	/**
	 * 修改用户
	 * @param umsAdminId 用户id
	 * @param umsAdminParam 前端用户参数集
	 * @param updater 修改人
	 * @return
	 */
	int update(Long umsAdminId, UmsAdminParam umsAdminParam, String updater);
	
	/**
	 * 删除用户
	 * @param umsAdminId
	 * @return
	 */
	int delete(Long umsAdminId);
	
	/**
	 * 通过用户名修改用户头像地址
	 * @param umsAdmin
	 * @return
	 */
	int updateUmsAdminIconByUsername(UmsAdmin umsAdmin);
	
	/**
	 * 修改邮箱
	 * @param code 邮箱验证码
	 * @param umsAdminParam 前端邮箱验证参数（邮箱地址、密码）
	 * @return
	 */
	Map<String, String> updateEmail(String code, UmsAdminParam umsAdminParam);
	
	
}
