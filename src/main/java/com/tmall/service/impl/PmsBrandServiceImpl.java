package com.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.PmsBrandMapper;
import com.tmall.model.PmsBrand;
import com.tmall.service.PmsBrandService;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
	@Autowired
    private PmsBrandMapper pmsBrandMapper;
	
	@Override
	public List<PmsBrand> listAllBrand() {
		return pmsBrandMapper.selectBrand(new PmsBrand());
	}

}
