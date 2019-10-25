package com.tmall.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tmall.dao.QuartzJobMapper;
import com.tmall.quartz.model.QuartzJob;
import com.tmall.quartz.model.QuartzJobLog;
import com.tmall.quartz.util.QuartzManage;
import com.tmall.service.QuartzJobService;

@Service(value = "quartzJobService")
public class QuartzJobServiceImpl implements QuartzJobService {
	
	@Autowired
	private QuartzJobMapper quartzJobMapper;
	
	@Autowired
	private QuartzManage quartzManage;
	
	@Override
	public List<QuartzJob> getQuartzJobList(Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		return quartzJobMapper.selectQuartzJob();
	}
	
	@Override
	public int updateQuartzJob(QuartzJob quartzJob) {
		quartzJob.setUpdateTime(new Date());
		return quartzJobMapper.updateQuartzJob(quartzJob);
	}

	@Override
	public Map executeJob(Long id) {
		HashMap<String, String> map = new HashMap<String, String>();
		QuartzJob quartzJob = quartzJobMapper.selectQuartzJobById(id);
		if (quartzJob == null) {
			map.put("error", "任务执行失败，通过id："+id+"，查找QuartzJob为空");
		}
		//执行定时任务
		quartzManage.runAJobNow(quartzJob);
		return map;
	}

	@Override
	public Map updateJobStatus(Long id) {
		HashMap<String, String> map = new HashMap<String, String>();
		QuartzJob quartzJob = quartzJobMapper.selectQuartzJobById(id);
		if (quartzJob == null) {
			map.put("error", "任务执行失败，通过id："+id+"，查找QuartzJob为空");
		}
		if (quartzJob.getIsPause() == 1) {
			//恢复一个job
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(0);
        } else {
        	//暂停一个job
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(1);
        }
		quartzJob.setUpdateTime(new Date());
		quartzJobMapper.updateQuartzJob(quartzJob);
		return map;
	}

	@Override
	public Map deleteJob(Long id) {
		HashMap<String, String> map = new HashMap<String, String>();
		QuartzJob quartzJob = quartzJobMapper.selectQuartzJobById(id);
		if (quartzJob == null) {
			map.put("error", "任务执行失败，通过id："+id+"，查找QuartzJob为空");
		}
		//删除quartzJob
		quartzManage.deleteJob(quartzJob);
		quartzJobMapper.deleteQuartzJobById(id);
		return map;
	}

	@Override
	public Long insertQuartzJob(QuartzJob quartzJob) {
		quartzJob.setUpdateTime(new Date());
		quartzJobMapper.insertQuartzJob(quartzJob);
		return quartzJob.getId();
	}

}
