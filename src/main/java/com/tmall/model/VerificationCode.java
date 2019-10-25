package com.tmall.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 验证码表
 * Created by Mr.Li on 2019/08/23
 */
public class VerificationCode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键")
	private Long id;
	@ApiModelProperty(value = "验证码")
	private String code;
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	@ApiModelProperty(value = "状态：1有效、0过期")
	private Integer status;
	@ApiModelProperty(value = "验证码类型：email或者短信")
	private String type;
	@ApiModelProperty(value = "接收邮箱或者手机号码")
	private String value;
	@ApiModelProperty(value = "业务名称：如重置邮箱、重置密码等")
	private String scenes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getScenes() {
		return scenes;
	}
	public void setScenes(String scenes) {
		this.scenes = scenes;
	}
	
}
