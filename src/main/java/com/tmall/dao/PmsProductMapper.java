package com.tmall.dao;

import java.util.List;

import com.tmall.model.PmsProduct;

public interface PmsProductMapper {

	List<PmsProduct> getPmsProductList();

	long insertPmsProduct(PmsProduct pmsProduct);
	
	int updatePmsProduct(PmsProduct pmsProduct);
}
