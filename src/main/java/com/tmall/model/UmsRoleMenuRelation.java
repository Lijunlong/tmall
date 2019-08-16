package com.tmall.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UmsRoleMenuRelation implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Long id;

	@ApiModelProperty(value = "角色id（外键）")
    private Long roleId;

	@ApiModelProperty(value = "菜单id（外键）")
	private Long menuId;

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

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
