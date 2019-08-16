package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsDepartment;

public interface UmsDepartmentMapper {

	List<UmsDepartment> selectUmsDepartment(UmsDepartment umsDepartment);

	UmsDepartment selectUmsDepartmentById(@Param("id")Long id);

	List<UmsDepartment> selectUmsDepartmenListByRoleId(@Param("roleId")Long roleId);

	List<UmsDepartment> selectUmsDepartmentLikeUmsDepartment(@Param("name")String name, @Param("enabled")Integer enabled);

	List<UmsDepartment> selectUmsDepartmentListByPid(@Param("pid")Long pid);

	int updateUmsDepartment(UmsDepartment umsDepartment);
	
	Long insertUmsDepartment(UmsDepartment umsDepartment);

	int deleteUmsDepartmentById(@Param("id")Long id);
	
}
