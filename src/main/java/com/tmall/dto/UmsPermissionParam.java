package com.tmall.dto;

import io.swagger.annotations.ApiModelProperty;

public class UmsPermissionParam {
	
	@ApiModelProperty(value = "权限名称", required = true)
    private String name;
    @ApiModelProperty(value = "权限别名", required = true)
    private String alias;
    @ApiModelProperty(value = "排序号", required = true)
    private Integer sort;
    @ApiModelProperty(value = "父id", required = true)
    private Long pid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "UmsPermissionParam [name=" + name + ", alias=" + alias + ", sort=" + sort + ", pid=" + pid + "]";
	}
    
}
