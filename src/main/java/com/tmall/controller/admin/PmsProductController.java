package com.tmall.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.common.api.CommonPage;
import com.tmall.common.api.CommonResult;
import com.tmall.dto.PmsProductParam;
import com.tmall.model.PmsProduct;
import com.tmall.service.PmsProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 商品模块
 * Created by Mr.Li on 2019/08/05
 */
@Controller
@Api(tags = "PmsProductController", description = "商品管理")
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    private PmsProductService productService;
    
    @ApiOperation("查询商品")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.getPmsProductList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }
    
    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody PmsProductParam pmsProductParam) {
    	long count = productService.createPmsProduct(pmsProductParam);
    	if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
    
    @ApiOperation("更新商品")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductParam pmsProductParam) {
        int count = productService.updatePmsProduct(id, pmsProductParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
