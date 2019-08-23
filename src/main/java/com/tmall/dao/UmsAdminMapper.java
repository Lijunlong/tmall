package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsAdmin;

public interface UmsAdminMapper {

	List<UmsAdmin> selectUmsAdmin(UmsAdmin umsAdmin);

	Long insertUmsAdmin(UmsAdmin umsAdmin);

	int updateUmsAdmin(UmsAdmin umsAdmin);

	int deleteUmsAdminById(Long umsAdminId);
	
	/**
	 * 模糊查询
	 * @param deptIds 
	 * @param umsAdminSearchParam
	 * @return
	 */
	List<UmsAdmin> selectUmsAdminLikeUmsAdmin(@Param("umsAdmin")UmsAdmin umsAdmin, @Param("deptIds")List<Long> deptIds);

	int updateUmsAdminIconByUsername(UmsAdmin umsAdmin);

}
