package com.tmall.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.tmall.dto.MenuVo;
import com.tmall.dto.UmsMenuParam;
import com.tmall.model.UmsAdmin;
import com.tmall.model.UmsMenu;
import com.tmall.service.UmsMenuService;
import com.tmall.util.CommonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单管理
 * Created by Mr.Li on 2019/08/08
 */
@Controller
@Api(tags = "UmsMenuController", description = "菜单管理")
@RequestMapping("/menus")
public class UmsMenuController extends BaseController {
	
	@Autowired
	private UmsMenuService umsMenuService;
	
	@ApiOperation("构建前端路由所需要的菜单")
    @RequestMapping(value = "/build", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult buildMenus(HttpServletRequest request) {
		UmsAdmin umsAdmin = getUmsAdmin(request);
		List<UmsMenu> menuTree = umsMenuService.buildMenus(umsAdmin.getId());
		List<MenuVo> menuVoList = umsMenuService.buildMenus(menuTree);
        return CommonResult.success(CommonUtil.removeKeyWithNullValue(menuVoList));
    }
	
	@ApiOperation("查询菜单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getMenuTree(String name) {
		List<UmsMenu> umsMenuTree = umsMenuService.getMenuTree(name);
        return CommonResult.success(umsMenuTree);
    }
	
	@ApiOperation("返回全部的菜单，新增菜单时下拉选择")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult buildTree() {
		List<Map<String,Object>> permissionTrees = umsMenuService.buildTree();
        return CommonResult.success(permissionTrees);
    }
	
	@Log("修改菜单")
	@ApiOperation("修改菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                         	   @RequestBody UmsMenuParam umsMenuParam) {
        int count = umsMenuService.update(id, umsMenuParam);
        return CommonResult.success("修改菜单成功");
    }
	
	@Log("添加菜单")
	@ApiOperation("添加菜单")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insert(@RequestBody UmsMenuParam umsMenuParam) {
        Long id = umsMenuService.insert(umsMenuParam);
        return CommonResult.success("添加菜单成功");
    }
	
	@Log("删除菜单")
	@ApiOperation("删除菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = umsMenuService.delete(id);
        return CommonResult.success("删除菜单成功");
    }
	
}
