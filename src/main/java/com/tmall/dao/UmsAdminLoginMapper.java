package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsAdmin;

public interface UmsAdminLoginMapper {
	
    List<UmsAdmin> selectUserByUsername(@Param("username")String username);
    
	int updateLoginTimeByUsername(UmsAdmin user);

	int updateUmsAdminPasswordById(@Param("id")Long id,@Param("password")String password);

}