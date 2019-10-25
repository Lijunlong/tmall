package com.tmall.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.common.api.CommonResult;
import com.tmall.service.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统工具邮箱管理
 * Created by Mr.Li on 2019/08/06
 */
@Controller
@Api(tags = "SysMailController", description = "系统工具邮箱管理")
@RequestMapping("/system/mail")
public class SysMailController {
	
	@Autowired
	private MailService mailService;
	
	@ApiOperation("分页查询定时任务")
    @RequestMapping(value = "/send_mail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult sendMail() {
		mailService.sendMail();
        return CommonResult.success(null);
    }
	
	
}
