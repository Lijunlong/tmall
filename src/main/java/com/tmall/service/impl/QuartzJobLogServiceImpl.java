package com.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.QuartzJobLogMapper;
import com.tmall.quartz.model.QuartzJobLog;
import com.tmall.service.QuartzJobLogService;

@Service
public class QuartzJobLogServiceImpl implements QuartzJobLogService {
	
	@Autowired
	private QuartzJobLogMapper quartzJobLogMapper;
	
	@Override
	public List<QuartzJobLog> getQuartzJobLogList(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		List<QuartzJobLog> quartzJobLogList = quartzJobLogMapper.getQuartzJobLogList();
		return quartzJobLogList;
	}

}
