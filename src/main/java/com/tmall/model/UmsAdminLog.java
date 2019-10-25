package com.tmall.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class UmsAdminLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "异常详情")
	private String exceptionDetail;
	
	@ApiModelProperty(value = "日志类型")
	private String logType;
	
	@ApiModelProperty(value = "方法")
	private String method;
	
	@ApiModelProperty(value = "参数")
	private String params;
	
	@ApiModelProperty(value = "请求ip")
	private String requestIp;
	
	@ApiModelProperty(value = "耗时")
	private Long time;
	
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	
	@ApiModelProperty(value = "创建人")
	private String creater;
	
	@ApiModelProperty(value = "ip地址详细信息")
	private String address;

	public UmsAdminLog() {
		super();
	}

	public UmsAdminLog(String logType, Long time) {
		super();
		this.logType = logType;
		this.time = time;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
