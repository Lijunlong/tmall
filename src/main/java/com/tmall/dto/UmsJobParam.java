package com.tmall.dto;

import io.swagger.annotations.ApiModelProperty;

public class UmsJobParam {
	
    @ApiModelProperty(value = "岗位名称", required = true)
    private String name;
    @ApiModelProperty(value = "排序字段", required = true)
    private Integer sort;
    @ApiModelProperty(value = "状态，1正常；0停用", required = true)
    private Integer enabled;
    @ApiModelProperty(value = "部门id（外键）")
    private Long umsDepartmentId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Long getUmsDepartmentId() {
		return umsDepartmentId;
	}
	public void setUmsDepartmentId(Long umsDepartmentId) {
		this.umsDepartmentId = umsDepartmentId;
	}
	@Override
	public String toString() {
		return "UmsJobParam [name=" + name + ", sort=" + sort + ", enabled=" + enabled + ", umsDepartmentId="
				+ umsDepartmentId + "]";
	}
    
}
