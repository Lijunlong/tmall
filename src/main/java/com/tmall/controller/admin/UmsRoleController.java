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
import com.tmall.dto.UmsRoleParam;
import com.tmall.model.UmsRole;
import com.tmall.service.UmsRoleService;
import com.tmall.util.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色管理
 * Created by Mr.Li on 2019/08/08
 */
@Controller
@Api(tags = "UmsRoleController", description = "角色管理")
@RequestMapping("/roles")
public class UmsRoleController extends BaseController {
	
	@Autowired
	private UmsRoleService umsRoleService;
	
	@ApiOperation("获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getRoles(String name,
    							 @RequestParam(value = "pageSize", defaultValue = "999") Integer pageSize,
            					 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsRole> roleList = umsRoleService.getRoles(name,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }
	
	@ApiOperation("获取单个角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSingleRole(@PathVariable Long id) {
		UmsRole umsRole = umsRoleService.getSingleRole(id);
        return CommonResult.success(umsRole);
    }
	
	@ApiOperation("修改角色菜单")
    @RequestMapping(value = "/update/menu/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRoleMenu(@PathVariable Long id,@RequestBody UmsRoleParam umsRoleParam) {
        int count = umsRoleService.updateRoleMenu(id,umsRoleParam);
        return CommonResult.success("修改角色菜单成功");
    }
	
	@ApiOperation("修改角色权限")
    @RequestMapping(value = "/update/permission/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRolePermission(@PathVariable Long id,@RequestBody UmsRoleParam umsRoleParam) {
        int count = umsRoleService.updateRolePermission(id,umsRoleParam);
        return CommonResult.success("修改角色权限成功");
    }
	
	@ApiOperation("删除角色")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult deleteRole(@PathVariable Long id) {
        int count = umsRoleService.deleteRole(id);
        return CommonResult.success("删除角色成功");
    }
	
	@ApiOperation("添加角色")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertRole(@RequestBody UmsRoleParam umsRoleParam) {
        int count = umsRoleService.insertRole(umsRoleParam,SecurityUtils.getUsername());
        return CommonResult.success("添加角色成功");
    }
	
	@ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@PathVariable Long id,@RequestBody UmsRoleParam umsRoleParam) {
        int count = umsRoleService.updateRole(id,umsRoleParam,SecurityUtils.getUsername());
        return CommonResult.success("修改角色成功");
    }
	
}
