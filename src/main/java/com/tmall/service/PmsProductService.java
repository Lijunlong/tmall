package com.tmall.service;

import java.util.List;

import com.tmall.dto.PmsProductParam;
import com.tmall.model.PmsProduct;

/**
 * 商品管理
 * Created by Mr.Li on 2019/08/05
 */
public interface PmsProductService {

	List<PmsProduct> getPmsProductList(Integer pageSize, Integer pageNum);

	long createPmsProduct(PmsProductParam pmsProductParam);

	int updatePmsProduct(Long id, PmsProductParam pmsProductParam);

}
