package com.tmall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UmsRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "名称")
	private String name;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "启用状态：0->禁用；1->启用")
	private Integer status;

	@ApiModelProperty(value = "排序号")
	private Integer sort;
	
	@ApiModelProperty(value = "数据权限")
	private String dataScope;
	
	@ApiModelProperty(value = "级别")
	private Integer level;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "创建人")
	private String creater;

	@ApiModelProperty(value = "修改人")
	private String updater;
	
	@ApiModelProperty(value = "部门列表")
	private List<UmsDepartment> depts;
	
	@ApiModelProperty(value = "菜单列表")
	private List<UmsMenu> menus;
	
	@ApiModelProperty(value = "权限列表")
	private List<UmsPermission> permissions;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public List<UmsDepartment> getDepts() {
		return depts;
	}

	public void setDepts(List<UmsDepartment> depts) {
		this.depts = depts;
	}

	public List<UmsMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<UmsMenu> menus) {
		this.menus = menus;
	}

	public List<UmsPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UmsPermission> permissions) {
		this.permissions = permissions;
	}

}