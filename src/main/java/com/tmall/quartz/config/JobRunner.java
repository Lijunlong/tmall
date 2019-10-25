package com.tmall.quartz.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.tmall.dao.QuartzJobMapper;
import com.tmall.quartz.model.QuartzJob;
import com.tmall.quartz.util.QuartzManage;

@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManage quartzManage;

    /**
     * 	项目启动时重新激活启用的定时任务
     * @param applicationArguments
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments applicationArguments){
        System.out.println("--------------------注入定时任务---------------------");
        List<QuartzJob> quartzJobs = quartzJobMapper.findByIsPauseIsFalse();
        quartzJobs.forEach(quartzJob -> {
            quartzManage.addJob(quartzJob);
        });
        System.out.println("--------------------定时任务注入完成---------------------");
    }
}
