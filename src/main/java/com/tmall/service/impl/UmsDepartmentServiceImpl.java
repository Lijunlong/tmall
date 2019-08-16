package com.tmall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tmall.dao.UmsDepartmentMapper;
import com.tmall.dto.UmsDepartmentParam;
import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsMenu;
import com.tmall.service.UmsDepartmentService;
import com.tmall.util.CommonUtil;

@Service
public class UmsDepartmentServiceImpl implements UmsDepartmentService {
	
	@Autowired
	private UmsDepartmentMapper umsDepartmentMapper;
	
	@Override
	public List<UmsDepartment> getDeptTrees(String name,Integer enabled) {
		//获取部门列表
		List<UmsDepartment> departmentList = umsDepartmentMapper.selectUmsDepartmentLikeUmsDepartment(name,enabled);
		if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(enabled)) {
			return departmentList;
		}else {
			//构建部门树
			List<UmsDepartment> trees = this.buildDeptsTree(departmentList);
			//过滤value为空的键
			return trees;
		}
	}
	
	/**
	 * 构建部门树
	 * @param departmentList 部门列表
	 * @return 树
	 */
	private List<UmsDepartment> buildDeptsTree(List<UmsDepartment> departmentList) {
		List<UmsDepartment> deptsTree = new ArrayList<UmsDepartment>();
		if (departmentList != null && departmentList.size() > 0) {
			for (UmsDepartment umsDept : departmentList) {
				if ("0".equals(umsDept.getPid().toString())) {
					deptsTree.add(umsDept);
				}
				for (UmsDepartment it : departmentList) {
					if (it.getPid().equals(umsDept.getId())) {
						if (umsDept.getChildren() == null) {
							umsDept.setChildren(new ArrayList<UmsDepartment>());
						}
						umsDept.getChildren().add(it);
					}
				}
			}
		}
		return deptsTree;
	}

	@Override
	public UmsDepartment selectUmsDepartmentById(Long id) {
		return umsDepartmentMapper.selectUmsDepartmentById(id);
	}

	@Override
	public List<UmsDepartment> selectUmsDepartmenListByRoleId(Long roleId) {
		return umsDepartmentMapper.selectUmsDepartmenListByRoleId(roleId);
	}

	@Override
	public List<Map<String, Object>> buildTree() {
		//获取根部门
		List<UmsDepartment> umsDepartmentList = umsDepartmentMapper.selectUmsDepartmentListByPid(0L);
		//构建前端新增部门时下拉选择的部门树
		return this.buildTree(umsDepartmentList);
	}
	
	/**
	 * 构建前端新增部门时下拉选择的部门树
	 * @param umsDepartmentList 部门列表
	 * @return 部门树
	 */
	private List<Map<String, Object>> buildTree(List<UmsDepartment> umsDepartmentList) {
		List<Map<String, Object>> tree = new ArrayList<Map<String,Object>>();
		for (UmsDepartment umsDepartment : umsDepartmentList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", umsDepartment.getId());
			map.put("label", umsDepartment.getName());
			map.put("name", umsDepartment.getName());
			List<UmsDepartment> children = umsDepartmentMapper.selectUmsDepartmentListByPid(umsDepartment.getId());
			if (children != null && children.size() > 0) {
				map.put("children", buildTree(children));
			}
			tree.add(map);
		}
		return tree;
	}

	@Override
	public List<UmsDepartment> selectUmsDepartmentListByPid(Long pid) {
		return umsDepartmentMapper.selectUmsDepartmentListByPid(pid);
	}

	@Override
	public int update(Long id, UmsDepartmentParam umsDepartmentParam) {
		UmsDepartment umsDepartment = this.createUmsDepartmentByUmsDepartmentParam(umsDepartmentParam);
		umsDepartment.setId(id);
		umsDepartmentMapper.updateUmsDepartment(umsDepartment);
		return 1;
	}
	
	@Override
	public Long insert(UmsDepartmentParam umsDepartmentParam) {
		UmsDepartment umsDepartment = this.createUmsDepartmentByUmsDepartmentParam(umsDepartmentParam);
		umsDepartment.setCreateTime(new Date());
		umsDepartmentMapper.insertUmsDepartment(umsDepartment);
		return umsDepartment.getId();
	}
	
	/**
	 * 转换器：把前端部门的数据转成后端部门表的数据
	 * @param umsMenuParam 前端部门的数据
	 * @return 后端部门的数据
	 */
	private UmsDepartment createUmsDepartmentByUmsDepartmentParam(UmsDepartmentParam umsDepartmentParam) {
		UmsDepartment umsDepartment = new UmsDepartment();
		BeanUtils.copyProperties(umsDepartmentParam, umsDepartment);
		if (umsDepartmentParam.getName() != null) {
			umsDepartment.setLabel(umsDepartmentParam.getName());
		}
		return umsDepartment;
	}

	@Override
	public int delete(Long id) {
		List<UmsDepartment> children = umsDepartmentMapper.selectUmsDepartmentListByPid(id);
		for (UmsDepartment umsDepartment : children) {
			delete(umsDepartment.getId());
		}
		umsDepartmentMapper.deleteUmsDepartmentById(id);
		return 1;
	}

}
