package com.tmall.dao;

import java.util.List;
import com.tmall.model.UmsAdmin;

public interface UmsAdminLoginMapper {
	
    int insert(UmsAdmin umsAdmin);

    List<UmsAdmin> selectUserByUsername(String username);
    
	int updateLoginTimeByUsername(UmsAdmin user);

}