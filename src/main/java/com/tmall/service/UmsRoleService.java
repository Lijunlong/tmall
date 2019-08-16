package com.tmall.service;

import java.util.List;

import com.tmall.model.UmsRole;

public interface UmsRoleService {
	
	
	List<UmsRole> selectRoleListByAdminId(long userId);
	
	List<UmsRole> getRoles();

}
