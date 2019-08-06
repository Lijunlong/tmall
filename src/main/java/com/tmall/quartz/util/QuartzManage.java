package com.tmall.quartz.util;

import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import com.tmall.exception.BadRequestException;
import com.tmall.quartz.model.QuartzJob;

@Component
public class QuartzManage {
	
	private Logger logger = LogManager.getLogger(QuartzManage.class);
	
    private static final String JOB_NAME = "TASK_";

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public void addJob(QuartzJob quartzJob){
        try {
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class).
                    withIdentity(JOB_NAME + quartzJob.getId()).build();

            //通过触发器名和cron 表达式创建 Trigger
            Trigger cronTrigger = newTrigger()
                    .withIdentity(JOB_NAME + quartzJob.getId())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))
                    .build();

            cronTrigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);

            //重置启动时间
            ((CronTriggerImpl)cronTrigger).setStartTime(new Date());

            //执行定时任务
            scheduler.scheduleJob(jobDetail,cronTrigger);

            // 暂停任务
            if (quartzJob.getIsPause() == 1) {
                pauseJob(quartzJob);
            }
        } catch (Exception e){
        	logger.error("创建定时任务失败", e);
            throw new BadRequestException("创建定时任务失败");
        }
    }

    /**
     * 	更新job cron表达式
     * @param quartzJob
     * @throws SchedulerException
     */
    public void updateJobCron(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null){
                addJob(quartzJob);
                trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            }
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            //重置启动时间
            ((CronTriggerImpl)trigger).setStartTime(new Date());
            trigger.getJobDataMap().put(QuartzJob.JOB_KEY,quartzJob);

            scheduler.rescheduleJob(triggerKey, trigger);
            // 暂停任务
            if (quartzJob.getIsPause() == 1) {
                pauseJob(quartzJob);
            }
        } catch (Exception e){
            logger.error("更新定时任务失败", e);
            throw new BadRequestException("更新定时任务失败");
        }

    }

    /**
     * 	删除一个job
     * @param quartzJob
     * @throws SchedulerException
     */
    public void deleteJob(QuartzJob quartzJob){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (Exception e){
            logger.error("删除定时任务失败", e);
            throw new BadRequestException("删除定时任务失败");
        }
    }

    /**
     * 恢复一个job
     * @param quartzJob
     * @throws SchedulerException
     */
    public void resumeJob(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null)
                addJob(quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e){
            logger.error("恢复定时任务失败", e);
            throw new BadRequestException("恢复定时任务失败");
        }
    }

    /**
     * 立即执行job
     * @param quartzJob
     * @throws SchedulerException
     */
    public void runAJobNow(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 如果不存在则创建一个定时任务
            if(trigger == null)
                addJob(quartzJob);
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(QuartzJob.JOB_KEY, quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.triggerJob(jobKey,dataMap);
        } catch (Exception e){
            logger.error("定时任务执行失败", e);
            throw new BadRequestException("定时任务执行失败");
        }
    }

    /**
     * 暂停一个job
     * @param quartzJob
     * @throws SchedulerException
     */
    public void pauseJob(QuartzJob quartzJob){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(jobKey);
        } catch (Exception e){
            logger.error("定时任务暂停失败", e);
            throw new BadRequestException("定时任务暂停失败");
        }
    }
}
