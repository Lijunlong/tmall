package com.tmall.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 岗位表
 * Created by Mr.Li on 2019/08/08
 */
public class UmsJob implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Long id;

	@ApiModelProperty(value = "岗位名称")
    private String name;
	
	@ApiModelProperty(value = "状态")
	private Integer enabled;
	
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	@ApiModelProperty(value = "排序号")
	private Integer sort;
	
	@ApiModelProperty(value = "部门id（外键）")
	private Long umsDepartmentId;
	
	@ApiModelProperty(value = "部门表")
	private UmsDepartment dept;
	
	@ApiModelProperty(value = "前端展示需要，岗位列表所属部门")
	private String deptSuperiorName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getUmsDepartmentId() {
		return umsDepartmentId;
	}

	public void setUmsDepartmentId(Long umsDepartmentId) {
		this.umsDepartmentId = umsDepartmentId;
	}

	public UmsDepartment getDept() {
		return dept;
	}

	public void setDept(UmsDepartment dept) {
		this.dept = dept;
	}

	public String getDeptSuperiorName() {
		return deptSuperiorName;
	}

	public void setDeptSuperiorName(String deptSuperiorName) {
		this.deptSuperiorName = deptSuperiorName;
	}

}