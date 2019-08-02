package com.tmall.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.PmsProductCategoryMapper;
import com.tmall.dto.PmsProductCategoryParam;
import com.tmall.model.PmsProductCategory;
import com.tmall.service.PmsProductCategoryService;

@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;

	@Override
	public List<PmsProductCategory> getPmsProductCategoryList(Long parentId, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
        return productCategoryMapper.getPmsProductCategoryList(parentId);
	}

	@Override
	public long createPmsProductCategory(PmsProductCategoryParam pmsProductCategoryParam) {
		PmsProductCategory pmsProductCategory = new PmsProductCategory();
        BeanUtils.copyProperties(pmsProductCategoryParam, pmsProductCategory);
		long id = this.insertPmsProductCategory(pmsProductCategory);
		return id;
	}
	
	@Override
	public long insertPmsProductCategory(PmsProductCategory pmsProductCategory) {
		return productCategoryMapper.insertPmsProductCategory(pmsProductCategory);
	}

	@Override
	public int updateShowStatus(Long id, Integer showStatus) {
		return productCategoryMapper.updateShowStatus(id,showStatus);
	}

	@Override
	public List<PmsProductCategory> getPmsProductCategory(PmsProductCategory pmsProductCategory) {
		return productCategoryMapper.getPmsProductCategory(pmsProductCategory);
	}

	@Override
	public int update(Long id, PmsProductCategoryParam pmsProductCategoryParam) {
		PmsProductCategory pmsProductCategory = new PmsProductCategory();
		pmsProductCategory.setId(id);
        BeanUtils.copyProperties(pmsProductCategoryParam, pmsProductCategory);
		return productCategoryMapper.updatePmsProductCategory(pmsProductCategory);
	}

}
