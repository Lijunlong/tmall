package com.tmall.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.aop.log.Log;
import com.tmall.common.api.CommonPage;
import com.tmall.common.api.CommonResult;
import com.tmall.quartz.model.QuartzJob;
import com.tmall.quartz.model.QuartzJobLog;
import com.tmall.service.QuartzJobLogService;
import com.tmall.service.QuartzJobService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统工具定时任务管理
 * Created by Mr.Li on 2019/08/06
 */
@Controller
@Api(tags = "SysTimingController", description = "系统工具定时任务管理")
@RequestMapping("/system/timing")
public class SysTimingController {
    
	@Autowired
	private QuartzJobService quartzJobService;
	
	@Autowired
	private QuartzJobLogService quartzJobLogService;
	
    @ApiOperation("分页查询定时任务")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<QuartzJob>> getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<QuartzJob> quartzJobList = quartzJobService.getQuartzJobList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(quartzJobList));
    }
    
    @Log("新增定时任务")
    @ApiOperation("新增定时任务")
    @RequestMapping(value = "/job", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody QuartzJob quartzJob){
    	Long id = quartzJobService.insertQuartzJob(quartzJob);
    	return CommonResult.success(id);
    }
    
    @Log("修改定时任务")
    @ApiOperation("修改定时任务")
    @RequestMapping(value = "/job/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,@RequestBody QuartzJob quartzJob){
    	quartzJob.setId(id);
    	int num = quartzJobService.updateQuartzJob(quartzJob);
    	if (num > 0) {
    		return CommonResult.success(id);
		}else {
			return CommonResult.failed();
		}
    }
    
    @Log("执行定时任务")
    @ApiOperation("执行定时任务")
    @RequestMapping(value = "/job/execute_job/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult executeJob(@PathVariable Long id){
    	Map map = quartzJobService.executeJob(id);
    	if (StringUtils.isEmpty(map.get("error"))) {
    		return CommonResult.success("执行成功");
		}else {
			return CommonResult.failed(map.get("error").toString());
		}
    }
    
    @Log("修改定时任务状态")
    @ApiOperation("修改定时任务状态")
    @RequestMapping(value = "/job/update_job_status/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult updateJobStatus(@PathVariable Long id){
    	Map map = quartzJobService.updateJobStatus(id);
    	if (StringUtils.isEmpty(map.get("error"))) {
    		return CommonResult.success("执行成功");
		}else {
			return CommonResult.failed(map.get("error").toString());
		}
    }
    
    @Log("删除定时任务")
    @ApiOperation("删除定时任务")
    @RequestMapping(value = "/job/delete_job/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult deleteJob(@PathVariable Long id){
    	Map map = quartzJobService.deleteJob(id);
    	if (StringUtils.isEmpty(map.get("error"))) {
    		return CommonResult.success("删除定时任务成功");
		}else {
			return CommonResult.failed(map.get("error").toString());
		}
    }
    
    @ApiOperation("分页查询定时任务日志")
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<QuartzJobLog>> getLogList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<QuartzJobLog> quartzJobLogList = quartzJobLogService.getQuartzJobLogList(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(quartzJobLogList));
    }
}
