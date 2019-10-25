package com.tmall.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.common.api.CommonResult;
import com.tmall.controller.BaseController;
import com.tmall.model.VerificationCode;
import com.tmall.service.VerificationCodeService;
import com.tmall.util.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 验证码管理 Created by Mr.Li on 2019/08/23
 */
@Controller
@Api(tags = "UmsVerificationCodeController", description = "验证码管理")
@RequestMapping("/code")
public class VerificationCodeController extends BaseController {

	@Autowired
	private VerificationCodeService umsVerificationCodeService;

	@ApiOperation("发送邮箱验证码")
	@RequestMapping(value = "/resetEmail", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult resetEmail(@RequestBody VerificationCode verificationCode) {
		verificationCode.setScenes(Constant.RESET_MAIL);
		int count = umsVerificationCodeService.resetEmail(verificationCode);
		return CommonResult.success("已发送邮箱验证码");
	}
	
}
