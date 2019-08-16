package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsMenu;

public interface UmsMenuMapper {

	List<UmsMenu> selectUmsMenuListByRoleId(@Param("roleId")Long roleId);

	List<UmsMenu> selectUmsMenuLikeUmsMenu(UmsMenu umsMenu);

	List<UmsMenu> selectUmsMenuListByPid(@Param("pid")Long pid);

	int updateUmsMenu(UmsMenu umsMenu);
	
	Long insertUmsMenu(UmsMenu umsMenu);

	int deleteUmsMenuById(@Param("id")Long id);

}
