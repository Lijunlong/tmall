package com.tmall.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UmsRoleDepartmentRelation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Long id;

	@ApiModelProperty(value = "角色id（外键）")
    private Long roleId;

	@ApiModelProperty(value = "部门id（外键）")
	private Long departmentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
}
