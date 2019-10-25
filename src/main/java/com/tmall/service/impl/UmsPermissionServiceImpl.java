package com.tmall.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tmall.dao.UmsPermissionMapper;
import com.tmall.dto.UmsPermissionParam;
import com.tmall.model.UmsPermission;
import com.tmall.service.UmsPermissionService;

@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {

    @Autowired
    private UmsPermissionMapper umsPermissionMapper;

	@Override
	public List<UmsPermission> getUmsPermissionListByAdminId(Long userId) {
		return umsPermissionMapper.getUmsPermissionListByAdminId(userId);
	}

	@Override
	public List<UmsPermission> selectUmsPermissionListByRoleId(Long roleId) {
		return umsPermissionMapper.selectUmsPermissionListByRoleId(roleId);
	}

	@Override
	public List<UmsPermission> getPermissionTree(String name) {
		//获取权限列表
		UmsPermission umsPermission = new UmsPermission();
		umsPermission.setName(name);
		List<UmsPermission> permissionList = umsPermissionMapper.selectUmsPermissionLikeUmsPermission(umsPermission);
		//构建权限树
		List<UmsPermission> permissionTrees = this.buildPermissionTrees(permissionList);
		//权限树根据排序号排序，升序排序
		permissionSort(permissionTrees);
		if (permissionTrees.size() == 0 && !StringUtils.isEmpty(name)) {
			return permissionList;
		}else {
			return permissionTrees;
		}
	}

	/**
	 * 构建权限树
	 * @param permissionList 权限列表
	 * @return 权限树
	 */
	private List<UmsPermission> buildPermissionTrees(List<UmsPermission> permissionList) {
		List<UmsPermission> umsPermissionTree = new ArrayList<UmsPermission>();
		
		if (permissionList != null && permissionList.size() > 0) {
			for (UmsPermission umsPermission : permissionList) {
				if ("0".equals(umsPermission.getPid().toString())) {
					umsPermissionTree.add(umsPermission);
				}
				for (UmsPermission it : permissionList) {
					if (it.getPid().equals(umsPermission.getId())) {
						if (umsPermission.getChildren() == null) {
							umsPermission.setChildren(new ArrayList<UmsPermission>());
						}
						umsPermission.getChildren().add(it);
					}
				}
			}
		}
		return umsPermissionTree;
	}

	@Override
	public List<Map<String, Object>> buildTree() {
		//获取根权限
		List<UmsPermission> umsPermissionList = umsPermissionMapper.selectUmsPermissionListByPid(0L);
		//构建前端新增角色时下拉选择的权限树
		return this.buildTree(umsPermissionList);
	}
	
	/**
	 * 返回全部的权限，新增角色时下拉选择
	 * @param umsPermissionList 权限列表
	 * @return 新增角色时下拉选择的权限树
	 */
	private List<Map<String, Object>> buildTree(List<UmsPermission> umsPermissionList) {
		List<Map<String, Object>> tree = new ArrayList<Map<String,Object>>();
		for (UmsPermission umsPermission : umsPermissionList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", umsPermission.getId());
			map.put("label", umsPermission.getAlias());
			List<UmsPermission> children = umsPermissionMapper.selectUmsPermissionListByPid(umsPermission.getId());
			if (children != null && children.size() > 0) {
				map.put("children", buildTree(children));
			}
			tree.add(map);
		}
		return tree;
	}

	@Override
	public int update(Long id, UmsPermissionParam umsPermissionParam, String updater) {
		UmsPermission umsPermisson = this.createUmsPermissionByUmsPermissionParam(umsPermissionParam);
		umsPermisson.setId(id);
		umsPermisson.setUpdateTime(new Date());
		umsPermisson.setUpdater(updater);
		umsPermissionMapper.updateUmsPermission(umsPermisson);
		return 1;
	}
	
	@Override
	public Long create(UmsPermissionParam umsPermissionParam, String creater) {
		UmsPermission umsPermisson = this.createUmsPermissionByUmsPermissionParam(umsPermissionParam);
		umsPermisson.setCreateTime(new Date());
		umsPermisson.setCreater(creater);
		umsPermissionMapper.insertUmsPermission(umsPermisson);
		return umsPermisson.getId();
	}
	
	/**
	 * 转换器：把前端用户的数据转成后端用户表的数据
	 * @param umsPermissionParam 前端用户的数据
	 * @return 后端用户的数据
	 */
	private UmsPermission createUmsPermissionByUmsPermissionParam(UmsPermissionParam umsPermissionParam) {
		UmsPermission umsPermission = new UmsPermission();
		BeanUtils.copyProperties(umsPermissionParam, umsPermission);
		return umsPermission;
	}

	@Override
	public int delete(Long permissionId) {
		//删除权限和下面的子集
		List<UmsPermission> children = umsPermissionMapper.selectUmsPermissionListByPid(permissionId);
		for (UmsPermission umsPermission : children) {
			delete(umsPermission.getId());
		}
		umsPermissionMapper.deleteUmsPermissionById(permissionId);
		return 1;
	}

	@Override
	public String[] getUmsPermissionNameByAdminId(Long adminId) {
		List<UmsPermission> umsPermissionList = umsPermissionMapper.getUmsPermissionListByAdminId(adminId);
		if (umsPermissionList != null && umsPermissionList.size() > 0) {
			String[] umsPermissionName = new String[umsPermissionList.size()];
			for (int i = 0; i < umsPermissionList.size(); i++) {
				UmsPermission umsPermission = umsPermissionList.get(i);
				umsPermissionName[i] = umsPermission.getName();
			}
			return umsPermissionName;
		}else {
			return new String[] {};
		}
	}
	
	private void permissionSort(List<UmsPermission> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				UmsPermission umsPermission = list.get(i);
				Collections.sort(list, new Comparator<UmsPermission>() {
					@Override
					public int compare(UmsPermission o1, UmsPermission o2) {
						return o1.getSort().compareTo(o2.getSort());
					}
				});
				permissionSort(umsPermission.getChildren());
			}
		}
	}
    
}
