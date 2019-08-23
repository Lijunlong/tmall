package com.tmall.dto;

import io.swagger.annotations.ApiModelProperty;

public class UmsAdminUpdatePasswordParam {
	
	@ApiModelProperty(value = "旧密码", required = true)
	private String oldPass;
	@ApiModelProperty(value = "新密码", required = true)
	private String newPass;
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
}
