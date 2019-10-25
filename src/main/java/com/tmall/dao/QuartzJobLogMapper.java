package com.tmall.dao;

import java.util.List;

import com.tmall.quartz.model.QuartzJobLog;

public interface QuartzJobLogMapper {

	List<QuartzJobLog> getQuartzJobLogList();

	long insertQuartzJobLog(QuartzJobLog log);

}
