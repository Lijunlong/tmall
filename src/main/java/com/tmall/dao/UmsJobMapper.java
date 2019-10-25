package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.model.UmsJob;

public interface UmsJobMapper {

	UmsJob selectUmsJobById(@Param("id")Long umsJobId);

	List<UmsJob> selectUmsJob(UmsJob umsJob);

	int deleteUmsJobById(@Param("id")Long id);

	Long insertUmsJob(UmsJob umsJob);

	int updateUmsJob(UmsJob umsJob);

	List<UmsJob> selectUmsJobLikeUmsJob(UmsJob umsJob);

}
