package com.tmall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.github.pagehelper.PageHelper;
import com.tmall.dao.UmsRoleMapper;
import com.tmall.dto.UmsRoleParam;
import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsMenu;
import com.tmall.model.UmsPermission;
import com.tmall.model.UmsRole;
import com.tmall.model.UmsRoleDepartmentRelation;
import com.tmall.model.UmsRoleMenuRelation;
import com.tmall.model.UmsRolePermissionRelation;
import com.tmall.service.UmsDepartmentService;
import com.tmall.service.UmsMenuService;
import com.tmall.service.UmsPermissionService;
import com.tmall.service.UmsRoleDepartmentRelationService;
import com.tmall.service.UmsRoleMenuRelationService;
import com.tmall.service.UmsRolePermissionRelationService;
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
	@Autowired
	private UmsRoleMenuRelationService umsRoleMenuRelationService;
	@Autowired
	private UmsRolePermissionRelationService umsRolePermissionRelationService;
	@Autowired
	private UmsRoleDepartmentRelationService umsRoleDepartmentRelationService;
	
	@Override
	public List<UmsRole> selectRoleListByAdminId(long userId) {
		return umsRoleMapper.selectRoleListByAdminId(userId);
	}

	@Override
	public List<UmsRole> getRoles(String roleName, Integer pageSize, Integer pageNum) {
		UmsRole umsRoleSearchParam = new UmsRole();
		umsRoleSearchParam.setName(roleName);
		//获取角色列表
		PageHelper.startPage(pageNum, pageSize);
		List<UmsRole> umsRoleList = umsRoleMapper.selectUmsRoleListLikeUmsRole(umsRoleSearchParam);
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

	@Override
	public UmsRole getSingleRole(Long roleId) {
		//获取当前的角色信息
		UmsRole umsRole = umsRoleMapper.selectUmsRoleById(roleId);
		//获取当前角色下的部门
		List<UmsDepartment> umsDepartmentList = umsDepartmentService.selectUmsDepartmenListByRoleId(roleId);
		//获取当前角色下的菜单
		List<UmsMenu> umsMenuList = umsMenuService.selectUmsMenuListByRoleId(roleId);
		//获取当前角色下的权限
		List<UmsPermission> umsPermissionList = umsPermissionService.selectUmsPermissionListByRoleId(roleId);
		umsRole.setDepts(umsDepartmentList);
		umsRole.setMenus(umsMenuList);
		umsRole.setPermissions(umsPermissionList);
		return umsRole;
	}
	
	@Override
	public int updateRoleMenu(Long roleId, UmsRoleParam umsRoleParam) {
		//删除旧的角色和菜单的关系表
		umsRoleMenuRelationService.deleteUmsRoleMenuRelationByRoleId(roleId);
		List<UmsMenu> menuList = umsRoleParam.getMenus();
		for (UmsMenu umsMenu : menuList) {
			Long menuId = umsMenu.getId();
			//插入新的角色与菜单的关系表
			UmsRoleMenuRelation umsRoleMenuRelation = new UmsRoleMenuRelation();
			umsRoleMenuRelation.setRoleId(roleId);
			umsRoleMenuRelation.setMenuId(menuId);
			umsRoleMenuRelationService.insertUmsRoleMenuRelation(umsRoleMenuRelation);
		}
		return 1;
	}

	@Override
	public int updateRolePermission(Long roleId, UmsRoleParam umsRoleParam) {
		//删除旧的角色和权限的关系表
		umsRolePermissionRelationService.deleteUmsRolePermissionRelationByRoleId(roleId);
		List<UmsPermission> permissionList = umsRoleParam.getPermissions();
		for (UmsPermission umsPermission : permissionList) {
			Long permissionId = umsPermission.getId();
			//插入新的角色与权限的关系表
			UmsRolePermissionRelation umsRolePermissionRelation = new UmsRolePermissionRelation();
			umsRolePermissionRelation.setRoleId(roleId);
			umsRolePermissionRelation.setPermissionId(permissionId);
			umsRolePermissionRelationService.insertUmsRolePermissionRelation(umsRolePermissionRelation);
		}
		return 1;
	}

	@Override
	public int deleteRole(Long roleId) {
		//删除角色与部门的关系表
		umsRoleDepartmentRelationService.deleteUmsRoleDepartmentRelationByRoleId(roleId);
		//删除角色与菜单的关系表
		umsRoleMenuRelationService.deleteUmsRoleMenuRelationByRoleId(roleId);
		//删除角色与权限的关系表
		umsRolePermissionRelationService.deleteUmsRolePermissionRelationByRoleId(roleId);
		//删除角色表
		umsRoleMapper.deleteUmsRoleById(roleId);
		return 1;
	}

	@Override
	public int updateRole(Long roleId, UmsRoleParam umsRoleParam, String updater) {
		List<UmsDepartment> deptList = umsRoleParam.getDepts();
		//删除之前的角色与部门的关系表
		umsRoleDepartmentRelationService.deleteUmsRoleDepartmentRelationByRoleId(roleId);
		for (UmsDepartment umsDepartment : deptList) {
			Long umsDepartmentId = umsDepartment.getId();//部门id
			//添加新的角色与部门的关系表
			UmsRoleDepartmentRelation umsRoleDepartmentRelation = new UmsRoleDepartmentRelation();
			umsRoleDepartmentRelation.setRoleId(roleId);
			umsRoleDepartmentRelation.setDepartmentId(umsDepartmentId);
			umsRoleDepartmentRelationService.insertUmsRoleDepartmentRelation(umsRoleDepartmentRelation);
		}
		//更新角色
		UmsRole umsRole = this.createUmsRoleByUmsRoleParam(umsRoleParam);
		umsRole.setId(roleId);
		umsRole.setUpdateTime(new Date());
		umsRole.setUpdater(updater);
		umsRoleMapper.updateUmsRoleById(umsRole);
		return 1;
	}
	
	@Override
	public int insertRole(UmsRoleParam umsRoleParam, String creater) {
		UmsRole umsRole = this.createUmsRoleByUmsRoleParam(umsRoleParam);
		umsRole.setCreateTime(new Date());
		umsRole.setCreater(creater);
		//添加角色表
		umsRoleMapper.insertUmsRole(umsRole);
		Long roleId = umsRole.getId();//新增返回的角色id
		List<UmsDepartment> deptList = umsRoleParam.getDepts();
		for (UmsDepartment umsDepartment : deptList) {
			Long umsDepartmentId = umsDepartment.getId();//部门id
			UmsRoleDepartmentRelation umsRoleDepartmentRelation = new UmsRoleDepartmentRelation();
			umsRoleDepartmentRelation.setRoleId(roleId);
			umsRoleDepartmentRelation.setDepartmentId(umsDepartmentId);
			//添加角色与部门的关系表
			umsRoleDepartmentRelationService.insertUmsRoleDepartmentRelation(umsRoleDepartmentRelation);
		}
		return 1;
	}
	
	/**
	 * 转换器：把前端角色的数据转成后端角色表的数据
	 * @param umsMenuParam 前端角色的数据
	 * @return 后端角色的数据
	 */
	private UmsRole createUmsRoleByUmsRoleParam(UmsRoleParam umsRoleParam) {
		UmsRole umsRole = new UmsRole();
		BeanUtils.copyProperties(umsRoleParam, umsRole);
		return umsRole;
	}

}
