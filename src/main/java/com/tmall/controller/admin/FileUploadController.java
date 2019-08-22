package com.tmall.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.tmall.aop.log.Log;
import com.tmall.common.api.CommonResult;
import com.tmall.controller.BaseController;
import com.tmall.util.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(tags = "FileUploadController", description = "文件上传管理")
@RequestMapping("/files")
public class FileUploadController extends BaseController {
	
	@Log("上传文件")
	@ApiOperation("上传文件")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult upload(@RequestBody MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		if (file == null) {
			return CommonResult.failed();
		}
		// 获取文件在服务器的储存位置
		final String upload = "upload";
		String path = request.getSession().getServletContext().getRealPath("/" + upload);
		File filePath = new File(path);
		if (!filePath.exists() && !filePath.isDirectory()) {
			logger.info("目录不存在，创建目录:" + filePath);
			filePath.mkdir();
		}

		// 获取原始文件名称(包含格式)
		String originalFileName = file.getOriginalFilename();
		logger.info("原始文件名称：" + originalFileName);

		// 获取文件类型，以最后一个`.`为标识
		String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		logger.info("文件类型：" + type);
		// 获取文件名称（不包含格式）
		String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

		// 设置文件新名称: 当前时间+文件名称（不包含格式）
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(d);
		String fileName = date + name + "." + type;
		logger.info("新文件名称：" + fileName);

		// 在指定路径下创建一个文件
		File targetFile = new File(path, fileName);

		// 将文件保存到服务器指定位置
		try {
			file.transferTo(targetFile);
			logger.info("上传成功");
			//返回结果
			JSONObject result = new JSONObject();
			//result.put("url", path + "/" + fileName);//保存图片真实路径
			String icon_url = "http://localhost:" + Constant.HOST +"/tmall/" + upload + "/" + fileName;
			result.put("url", icon_url);//保存图片真实路径
			// 将文件在服务器的存储路径返回
			return CommonResult.success(result);
		} catch (IOException e) {
			logger.info("上传失败");
			e.printStackTrace();
			return CommonResult.failed();
		}
	}

}
