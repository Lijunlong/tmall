package com.tmall.service;

import java.util.List;
import java.util.Map;

import com.tmall.quartz.model.QuartzJob;
import com.tmall.quartz.model.QuartzJobLog;

public interface QuartzJobService {

	List<QuartzJob> getQuartzJobList(Integer pageSize, Integer pageNum);
	
	int updateQuartzJob(QuartzJob quartzJob);

	Map executeJob(Long id);

	Map updateJobStatus(Long id);

	Map deleteJob(Long id);

	Long insertQuartzJob(QuartzJob quartzJob);
	
}
