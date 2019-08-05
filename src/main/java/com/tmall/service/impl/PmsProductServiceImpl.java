package com.tmall.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.PmsProductMapper;
import com.tmall.dto.PmsProductParam;
import com.tmall.model.PmsProduct;
import com.tmall.service.PmsProductService;

@Service
public class PmsProductServiceImpl implements PmsProductService {
	@Autowired
	private PmsProductMapper pmsProductMapper;
	
	@Override
	public List<PmsProduct> getPmsProductList(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return pmsProductMapper.getPmsProductList();
	}

	@Override
	public long createPmsProduct(PmsProductParam pmsProductParam) {
		PmsProduct pmsProduct = new PmsProduct();
		BeanUtils.copyProperties(pmsProductParam, pmsProduct);
		return pmsProductMapper.insertPmsProduct(pmsProduct);
	}

	@Override
	public int updatePmsProduct(Long id, PmsProductParam pmsProductParam) {
		PmsProduct pmsProduct = new PmsProduct();
		BeanUtils.copyProperties(pmsProductParam, pmsProduct);
		return pmsProductMapper.updatePmsProduct(pmsProduct);
	}

}
