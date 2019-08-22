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
import com.tmall.dto.UmsPermissionParam;
import com.tmall.model.UmsPermission;
import com.tmall.service.UmsPermissionService;
import com.tmall.util.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限管理
 * Created by Mr.Li on 2019/08/14
 */
@Controller
@Api(tags = "UmsPermissionController", description = "权限管理")
@RequestMapping("/permissions")
public class UmsPermissionController extends BaseController {

	@Autowired
	private UmsPermissionService umsPermissionService;
	
	@ApiOperation("查询权限")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getPermissions(String name) {
		List<UmsPermission> permissionTrees = umsPermissionService.getPermissionTree(name);
        return CommonResult.success(permissionTrees);
    }
	
	@ApiOperation("返回全部的权限，新增角色时下拉选择")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult buildTree() {
		List<Map<String,Object>> permissionTrees = umsPermissionService.buildTree();
        return CommonResult.success(permissionTrees);
    }
	
	@Log("修改权限")
	@ApiOperation("修改权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                         	   @RequestBody UmsPermissionParam umsPermissionParam) {
        int count = umsPermissionService.update(id, umsPermissionParam,SecurityUtils.getUsername());
        return CommonResult.success("修改权限成功");
    }
	
	@Log("添加权限")
	@ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsPermissionParam umsPermissionParam) {
        Long id = umsPermissionService.create(umsPermissionParam,SecurityUtils.getUsername());
        return CommonResult.success("添加权限成功");
    }
	
	@Log("删除权限")
	@ApiOperation("删除权限")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = umsPermissionService.delete(id);
        return CommonResult.success("删除权限成功");
    }
}
