package com.tmall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
/**
 * 产品分类
 * Created by Mr.Li on 2019/07/26
 */
public class PmsProductCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "上机分类的编号：0表示一级分类")
    private Long parentId;

    @ApiModelProperty(value = "产品分类名称")
    private String name;

    @ApiModelProperty(value = "分类级别：0->1级；1->2级")
    private Integer level;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    private Integer showStatus;
    
    @ApiModelProperty(value = "排序号")
    private Integer sort;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "关键字")
    private String keywords;

    @ApiModelProperty(value = "描述")
    private String description;
    
    @ApiModelProperty(value = "图标保存路径")
    private String savePathIcon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSavePathIcon() {
		return savePathIcon;
	}

	public void setSavePathIcon(String savePathIcon) {
		this.savePathIcon = savePathIcon;
	}
    
}