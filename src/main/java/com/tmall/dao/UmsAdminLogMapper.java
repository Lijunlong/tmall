package com.tmall.dao;

import java.util.List;

import com.tmall.model.UmsAdminLog;

public interface UmsAdminLogMapper {

	Long insertUmsAdminLog(UmsAdminLog umsAdminLog);

	List<UmsAdminLog> selectUmsAdminLogList(UmsAdminLog umsAdminLog);

	List<UmsAdminLog> selectUmsAdminLogListLikeUmsAdminLog(UmsAdminLog umsAdminLog);
	
	int updateUmsAdminLogAddressById(UmsAdminLog umsAdminLog);

}
