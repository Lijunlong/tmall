package com.tmall.dto;

import io.swagger.annotations.ApiModelProperty;

public class UmsMenuParam {
	
	@ApiModelProperty(value = "图标", required = true)
    private String icon;
    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;
    @ApiModelProperty(value = "排序字段", required = true)
    private Integer sort;
    @ApiModelProperty(value = "是否外链，1正常；0外链", required = true)
    private Integer iFrame;
    @ApiModelProperty(value = "链接地址", required = true)
    private String path;
    @ApiModelProperty(value = "组件路径", required = true)
    private String component;
    @ApiModelProperty(value = "父id", required = true)
    private Long pid;
    @ApiModelProperty(value = "状态，1正常；0停用", required = true)
    private Integer enabled;
    @ApiModelProperty(value = "是否隐藏，1显示，0隐藏", required = true)
    private Integer hidden;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
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
	public Integer getiFrame() {
		return iFrame;
	}
	public void setiFrame(Integer iFrame) {
		this.iFrame = iFrame;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getHidden() {
		return hidden;
	}
	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}
	@Override
	public String toString() {
		return "UmsMenuParam [icon=" + icon + ", name=" + name + ", sort=" + sort + ", iFrame=" + iFrame + ", path="
				+ path + ", component=" + component + ", pid=" + pid + ", enabled=" + enabled + ", hidden=" + hidden
				+ "]";
	}
	
}
