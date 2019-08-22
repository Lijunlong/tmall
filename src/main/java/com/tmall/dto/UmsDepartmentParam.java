package com.tmall.dto;

import io.swagger.annotations.ApiModelProperty;

public class UmsDepartmentParam {
	
	@ApiModelProperty(value = "部门名称", required = true)
    private String name;
    @ApiModelProperty(value = "状态", required = true)
    private Integer enabled;
    @ApiModelProperty(value = "上级部门", required = true)
    private Long pid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "UmsDepartmentParam [name=" + name + ", enabled=" + enabled + ", pid=" + pid + "]";
	}
    
}
