package com.tmall.dto;

import com.tmall.model.UmsRole;

public class UmsRoleParam extends UmsRole {

	@Override
	public String toString() {
		return "UmsRoleParam [getId()=" + getId() + ", getName()=" + getName() + ", getDescription()="
				+ getDescription() + ", getStatus()=" + getStatus() + ", getSort()=" + getSort() + ", getDataScope()="
				+ getDataScope() + ", getLevel()=" + getLevel() + ", getCreateTime()=" + getCreateTime()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getCreater()=" + getCreater() + ", getUpdater()="
				+ getUpdater() + ", getDepts()=" + getDepts() + ", getMenus()=" + getMenus() + ", getPermissions()="
				+ getPermissions() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
