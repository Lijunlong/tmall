package com.tmall.quartz.model;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * quartz参数
 * Created by Mr.Li on 2019/08/06
 */
public class QuartzJob implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String JOB_KEY = "JOB_KEY";

	@ApiModelProperty(value = "主键")
	private Long id;

    @ApiModelProperty(value = "定时器名称")
    private String jobName;

    @ApiModelProperty(value = "Bean名称")
    private String beanName;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "参数")
    private String params;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "状态：1暂停、0启用")
    private Integer isPause;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建日期")
    private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Integer getIsPause() {
		return isPause;
	}

	public void setIsPause(Integer isPause) {
		this.isPause = isPause;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
