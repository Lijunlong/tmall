package com.tmall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.tmall.model.UmsDepartment;
import com.tmall.model.UmsJob;
import com.tmall.model.UmsRole;

/**
 * 用户管理参数
 */
public class UmsAdminParam {
	@ApiModelProperty(value = "昵称", required = true)
    private String nickName;
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "状态，1启用，0禁用", required = true)
    private Integer enabled;
    @ApiModelProperty(value = "电话号码", required = true)
    private String telphone;
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;
    @ApiModelProperty(value = "部门表", required = true)
    private UmsDepartment dept;
    @ApiModelProperty(value = "岗位表", required = true)
    private UmsJob job;
    @ApiModelProperty(value = "角色列表", required = true)
    private List<UmsRole> roles;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UmsDepartment getDept() {
		return dept;
	}
	public void setDept(UmsDepartment dept) {
		this.dept = dept;
	}
	public UmsJob getJob() {
		return job;
	}
	public void setJob(UmsJob job) {
		this.job = job;
	}
	public List<UmsRole> getRoles() {
		return roles;
	}
	public void setRoles(List<UmsRole> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UmsAdminParam [nickName=" + nickName + ", username=" + username + ", enabled=" + enabled + ", telphone="
				+ telphone + ", email=" + email + ", dept=" + dept + ", job=" + job + ", roles=" + roles + "]";
	}
	
}
