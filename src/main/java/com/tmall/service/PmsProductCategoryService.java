package com.tmall.service;

import java.util.List;

import com.tmall.dto.PmsProductCategoryParam;
import com.tmall.dto.PmsProductCategoryWithChildrenItem;
import com.tmall.model.PmsProductCategory;

/**
 * 商品分类模块
 * Created by Mr.Li on 2019/07/29
 */
public interface PmsProductCategoryService {
	
	List<PmsProductCategory> getPmsProductCategoryList(Long parentId, Integer pageSize, Integer pageNum);

	int updateShowStatus(Long id, Integer showStatus);

	long createPmsProductCategory(PmsProductCategoryParam pmsProductCategoryParam);
	
	long insertPmsProductCategory(PmsProductCategory pmsProductCategory);
	
	List<PmsProductCategory> getPmsProductCategory(PmsProductCategory pmsProductCategory);

	int update(Long id, PmsProductCategoryParam pmsProductCategoryParam);

	List<PmsProductCategoryWithChildrenItem> listWithChildren();
	
}
