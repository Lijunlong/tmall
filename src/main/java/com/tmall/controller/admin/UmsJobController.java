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
import com.tmall.controller.BaseController;
import com.tmall.dto.UmsJobParam;
import com.tmall.model.UmsJob;
import com.tmall.service.UmsJobService;
import com.tmall.util.CommonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 岗位管理
 * Created by Mr.Li on 2019/08/08
 */
@Controller
@Api(tags = "UmsJobController", description = "岗位管理")
@RequestMapping("/jobs")
public class UmsJobController extends BaseController {
	
	@Autowired
	private UmsJobService umsJobService;
	
	@ApiOperation("通过部门id查询岗位列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getJobs(Long deptId,String name,Integer enabled, 
					    		@RequestParam(value = "pageSize", defaultValue = "999") Integer pageSize,
					            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsJob> umsJobs = umsJobService.getJobs(deptId,name,enabled,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(CommonUtil.removeKeyWithNullValue(umsJobs)));
    }
	
	@ApiOperation("修改岗位")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                         	   @RequestBody UmsJobParam umsJobParam) {
        int count = umsJobService.update(id, umsJobParam);
        return CommonResult.success("修改岗位成功");
    }
	
	@ApiOperation("添加岗位")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insert(@RequestBody UmsJobParam umsJobParam) {
        Long id = umsJobService.insert(umsJobParam);
        return CommonResult.success("添加岗位成功");
    }
	
	@ApiOperation("删除岗位")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = umsJobService.delete(id);
        return CommonResult.success("删除岗位成功");
    }
	
}
