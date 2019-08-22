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
import com.tmall.dto.UmsAdminParam;
import com.tmall.model.UmsAdmin;
import com.tmall.service.UmsAdminService;
import com.tmall.util.SecurityUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理
 * Created by Mr.Li on 2019/08/12
 */
@Controller
@Api(tags = "UmsAdminController", description = "用户管理")
@RequestMapping("/users")
public class UmsAdminController extends BaseController {
	
	@Autowired
	private UmsAdminService umsAdminService;
	
	@ApiOperation("查询用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getUserList(Long deptId,String username,String email, Integer enabled,
    								@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            						@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsAdmin> adminList = umsAdminService.getUserList(deptId,username,email,enabled,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }
	
	@ApiOperation("添加用户")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsAdminParam umsAdminParam) {
        int count = umsAdminService.create(umsAdminParam, SecurityUtils.getUsername());
        return CommonResult.success("添加用户成功");
    }
	
	@ApiOperation("修改用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                         	   @RequestBody UmsAdminParam umsAdminParam) {
        int count = umsAdminService.update(id, umsAdminParam, SecurityUtils.getUsername());
        return CommonResult.success("修改用户成功");
    }
	
	@ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = umsAdminService.delete(id);
        return CommonResult.success("删除用户成功");
    }
	
}
