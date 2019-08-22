package com.tmall.dto;

import java.util.List;

import com.tmall.model.PmsProductCategory;

/**
 * Created by Mr.Li on 2019/08/05
 */
@SuppressWarnings("serial")
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    private List<PmsProductCategory> children;

    public List<PmsProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<PmsProductCategory> children) {
        this.children = children;
    }

	@Override
	public String toString() {
		return "PmsProductCategoryWithChildrenItem [children=" + children + ", getId()=" + getId() + ", getParentId()="
				+ getParentId() + ", getName()=" + getName() + ", getLevel()=" + getLevel() + ", getShowStatus()="
				+ getShowStatus() + ", getSort()=" + getSort() + ", getIcon()=" + getIcon() + ", getKeywords()="
				+ getKeywords() + ", getDescription()=" + getDescription() + ", getSavePathIcon()=" + getSavePathIcon()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


}
