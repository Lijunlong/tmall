package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.quartz.model.QuartzJob;

public interface QuartzJobMapper {

	List<QuartzJob> findByIsPauseIsFalse();

	int updateQuartzJob(QuartzJob quartzJob);
	
	int deleteQuartzJobById(@Param("id")Long id);
}
