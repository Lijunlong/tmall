package com.tmall.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.common.api.CommonPage;
import com.tmall.common.api.CommonResult;
import com.tmall.controller.BaseController;
import com.tmall.dto.MenuVo;
import com.tmall.model.UmsAdmin;
import com.tmall.model.UmsMenu;
import com.tmall.model.UmsRole;
import com.tmall.service.UmsMenuService;
import com.tmall.service.UmsRoleService;
import com.tmall.util.CommonUtil;

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
    public CommonResult getRoles(@RequestParam(value = "pageSize", defaultValue = "999") Integer pageSize,
            					 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsRole> roleList = umsRoleService.getRoles();
        return CommonResult.success(CommonPage.restPage(roleList));
    }
}
