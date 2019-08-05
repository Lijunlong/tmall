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
}
