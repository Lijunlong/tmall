package com.tmall.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.aop.log.Log;
import com.tmall.common.api.CommonResult;
import com.tmall.controller.BaseController;
import com.tmall.dto.UmsDepartmentParam;
import com.tmall.model.UmsDepartment;
import com.tmall.service.UmsDepartmentService;
import com.tmall.util.CommonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 部门管理
 * Created by Mr.Li on 2019/08/12
 */
@Controller
@Api(tags = "UmsDepartmentController", description = "部门管理")
@RequestMapping("/department")
public class UmsDepartmentController extends BaseController {
	
	@Autowired
	private UmsDepartmentService umsDepartmentService;
	
	@ApiOperation("查询部门")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getDepts(String name,Integer enabled) {
		List<UmsDepartment> departmentTrees = umsDepartmentService.getDeptTrees(name,enabled);
        return CommonResult.success(CommonUtil.removeKeyWithNullValue(departmentTrees));
    }
	
	@ApiOperation("返回全部的部门，新增部门时下拉选择")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult buildTree() {
		List<Map<String,Object>> departmentTrees = umsDepartmentService.buildTree();
        return CommonResult.success(departmentTrees);
    }
	
	@Log("修改部门")
	@ApiOperation("修改部门")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                         	   @RequestBody UmsDepartmentParam umsDepartmentParam) {
        int count = umsDepartmentService.update(id, umsDepartmentParam);
        return CommonResult.success("修改部门成功");
    }
	
	@Log("添加部门")
	@ApiOperation("添加部门")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insert(@RequestBody UmsDepartmentParam umsDepartmentParam) {
        Long id = umsDepartmentService.insert(umsDepartmentParam);
        return CommonResult.success("添加部门成功");
    }
	
	@Log("删除部门")
	@ApiOperation("删除部门")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = umsDepartmentService.delete(id);
        return CommonResult.success("删除部门成功");
    }
}
