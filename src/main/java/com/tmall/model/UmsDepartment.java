package com.tmall.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 部门表
 * Created by Mr.Li on 2019/08/08
 */
public class UmsDepartment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Long id;

	@ApiModelProperty(value = "部门名称")
    private String name;
	
	@ApiModelProperty(value = "父id")
	private Long pid;
	
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	@ApiModelProperty(value = "状态，1正常；0停用")
	private Integer enabled;
	
	@ApiModelProperty(value = "排序号")
	private Integer sort;
	
	@ApiModelProperty(value = "label")
	private String label;
	
	private List<UmsDepartment> children;

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

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<UmsDepartment> getChildren() {
		return children;
	}

	public void setChildren(List<UmsDepartment> children) {
		this.children = children;
	}
	
}