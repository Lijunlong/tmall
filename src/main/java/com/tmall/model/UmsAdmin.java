package com.tmall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UmsAdmin implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "密码")
	private String password;

	@ApiModelProperty(value = "头像")
	private String icon;

	@ApiModelProperty(value = "昵称")
	private String nickName;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "备注信息")
	private String note;

	@ApiModelProperty(value = "电话号码")
	private String telphone;

	@ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
	private Integer enabled;

	@ApiModelProperty(value = "最后登录时间")
	private Date loginTime;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "创建人")
	private String creater;

	@ApiModelProperty(value = "修改人")
	private String updater;
	
	@ApiModelProperty(value = "岗位id(外键)")
	private Long umsJobId;
	
	@ApiModelProperty(value = "部门id(外键)")
	private Long umsDeptmentId;
	
	@ApiModelProperty(value = "部门实体")
	private UmsDepartment dept;
	
	@ApiModelProperty(value = "岗位实体")
	private UmsJob job;
	
	@ApiModelProperty(value = "角色实体列表")
	private List<UmsRole> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Long getUmsJobId() {
		return umsJobId;
	}

	public void setUmsJobId(Long umsJobId) {
		this.umsJobId = umsJobId;
	}

	public Long getUmsDeptmentId() {
		return umsDeptmentId;
	}

	public void setUmsDeptmentId(Long umsDeptmentId) {
		this.umsDeptmentId = umsDeptmentId;
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

}