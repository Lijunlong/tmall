package com.tmall.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.tmall.dto.UmsDepartmentParam;
import com.tmall.model.UmsDepartment;

/**
 * 部门Service
 * Created by Mr.Li on 2019/08/12
 */
public interface UmsDepartmentService {
	
	List<UmsDepartment> getDeptTrees(String name, Integer enabled);
	
	/**
	 * 通过部门id查询部门
	 * @return
	 */
	UmsDepartment selectUmsDepartmentById(Long id);

	List<UmsDepartment> selectUmsDepartmenListByRoleId(Long roleId);
	
	/**
	 * 返回全部的部门，新增部门时下拉选择
	 * @return
	 */
	List<Map<String, Object>> buildTree();

	List<UmsDepartment> selectUmsDepartmentListByPid(Long pid);
	
	/**
	 * 修改部门
	 * @param id 部门id
	 * @param umsDepartmentParam 前端部门参数
	 * @return
	 */
	int update(Long id, UmsDepartmentParam umsDepartmentParam);
	
	/**
	 * 添加部门
	 * @param umsDepartmentParam 前端部门参数
	 * @return
	 */
	Long insert(UmsDepartmentParam umsDepartmentParam);
	
	/**
	 * 删除部门
	 * @param id 部门id
	 * @return
	 */
	int delete(Long id);

}
