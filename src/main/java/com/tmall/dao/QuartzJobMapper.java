package com.tmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tmall.quartz.model.QuartzJob;
import com.tmall.quartz.model.QuartzJobLog;

public interface QuartzJobMapper {

	List<QuartzJob> findByIsPauseIsFalse();

	QuartzJob selectQuartzJobById(@Param("id")Long id);
	
	List<QuartzJob> selectQuartzJob();
	
	int updateQuartzJob(QuartzJob quartzJob);
	
	int deleteQuartzJobById(@Param("id")Long id);

	Long insertQuartzJob(QuartzJob quartzJob);

}
