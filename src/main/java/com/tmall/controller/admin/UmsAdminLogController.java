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
import com.tmall.model.UmsAdminLog;
import com.tmall.service.UmsAdminLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 操作日志管理
 * Created by Mr.Li on 2019/08/22
 */
@Controller
@Api(tags = "UmsAdminLogController", description = "操作日志管理")
@RequestMapping("/logs")
public class UmsAdminLogController extends BaseController {
	
	@Autowired
	private UmsAdminLogService umsAdminLogService;
	
	@ApiOperation("查询操作日志")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getLogs(String creater,String ip,String description,
    							@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
								@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsAdminLog> logList = umsAdminLogService.getLogs(creater,ip,description,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(logList));
    }
	
	@ApiOperation("查询异常日志")
    @RequestMapping(value = "/error/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getErrorLogs(String creater,String ip,String description,
    								 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
									 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsAdminLog> errorLogList = umsAdminLogService.getErrorLogs(creater,ip,description,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(errorLogList));
    }
	
	@ApiOperation("查询当前用户操作日志")
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getCurrentUserLogs(@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
										   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<UmsAdminLog> logList = umsAdminLogService.getCurrentUserLogs(pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(logList));
    }
	
}
