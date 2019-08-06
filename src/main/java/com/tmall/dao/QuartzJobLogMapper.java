package com.tmall.dao;

import com.tmall.quartz.model.QuartzJobLog;

public interface QuartzJobLogMapper {

	long insertQuartzJobLog(QuartzJobLog log);

}
