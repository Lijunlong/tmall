package com.tmall.service;

import java.util.List;

import com.tmall.quartz.model.QuartzJobLog;

public interface QuartzJobLogService {

	List<QuartzJobLog> getQuartzJobLogList(Integer pageSize, Integer pageNum);

}
