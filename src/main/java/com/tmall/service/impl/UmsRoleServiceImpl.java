package com.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.UmsRoleMapper;
import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsMenu;
import com.tmall.model.UmsPermission;
import com.tmall.model.UmsRole;
import com.tmall.service.UmsDepartmentService;
import com.tmall.service.UmsMenuService;
import com.tmall.service.UmsPermissionService;
import com.tmall.service.UmsRoleService;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {
	
	@Autowired
	private UmsRoleMapper umsRoleMapper;
	@Autowired
	private UmsDepartmentService umsDepartmentService;
	@Autowired
	private UmsMenuService umsMenuService;
	@Autowired
	private UmsPermissionService umsPermissionService;
	
	@Override
	public List<UmsRole> selectRoleListByAdminId(long userId) {
		return umsRoleMapper.selectRoleListByAdminId(userId);
	}

	@Override
	public List<UmsRole> getRoles() {
		//获取所有角色
		List<UmsRole> umsRoleList = umsRoleMapper.selectUmsRoleList(new UmsRole());
		for (UmsRole umsRole : umsRoleList) {
			Long roleId = umsRole.getId();
			//获取当前角色下的部门
			List<UmsDepartment> umsDepartmentList = umsDepartmentService.selectUmsDepartmenListByRoleId(roleId);
			//获取当前角色下的菜单
			List<UmsMenu> umsMenuList = umsMenuService.selectUmsMenuListByRoleId(roleId);
			//获取当前角色下的权限
			List<UmsPermission> umsPermissionList = umsPermissionService.selectUmsPermissionListByRoleId(roleId);
			umsRole.setDepts(umsDepartmentList);
			umsRole.setMenus(umsMenuList);
			umsRole.setPermissions(umsPermissionList);
		}
		return umsRoleList;
	}
	

}
