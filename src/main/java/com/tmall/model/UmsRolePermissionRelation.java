package com.tmall.model;

import java.io.Serializable;

public class UmsRolePermissionRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long roleId;

	private Long permissionId;

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

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

}