package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.dto.PmsProductCategoryWithChildrenItem;
import com.tmall.model.PmsProductCategory;

public interface PmsProductCategoryMapper {
	
	/**
	 * 查找商品分类表
	 * @param parentId
	 * @return
	 */
	List<PmsProductCategory> getPmsProductCategoryList(@Param("parentId")Long parentId);

	int updateShowStatus(@Param("id")Long id, @Param("showStatus")Integer showStatus);

	long insertPmsProductCategory(PmsProductCategory pmsProductCategory);

	List<PmsProductCategory> getPmsProductCategory(PmsProductCategory pmsProductCategory);

	int updatePmsProductCategory(PmsProductCategory pmsProductCategory);

	List<PmsProductCategoryWithChildrenItem> listWithChildren();

}
