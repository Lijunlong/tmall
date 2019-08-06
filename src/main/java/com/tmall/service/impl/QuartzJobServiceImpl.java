package com.tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.dao.QuartzJobMapper;
import com.tmall.quartz.model.QuartzJob;
import com.tmall.service.QuartzJobService;

@Service(value = "quartzJobService")
public class QuartzJobServiceImpl implements QuartzJobService {
	
	@Autowired
	private QuartzJobMapper quartzJobMapper;
	
	@Override
	public int updateQuartzJob(QuartzJob quartzJob) {
		return quartzJobMapper.updateQuartzJob(quartzJob);
	}

}
