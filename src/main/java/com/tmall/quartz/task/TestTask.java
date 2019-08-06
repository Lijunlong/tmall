package com.tmall.quartz.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 	测试用
 * Created by Mr.Li on 2019/08/06
 */
@Component
public class TestTask {
	private Logger logger = LogManager.getLogger(TestTask.class);

    public void run(){
    	logger.info("执行成功");
    }

    public void run1(String str){
    	logger.info("执行成功，参数为： {}" + str);
    }
}
