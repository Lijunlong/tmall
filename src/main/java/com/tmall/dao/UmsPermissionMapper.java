package com.tmall.dao;

import java.util.List;

import com.tmall.model.UmsPermission;

public interface UmsPermissionMapper {

	List<UmsPermission> getUmsPermissionList(Long user_id);

}
