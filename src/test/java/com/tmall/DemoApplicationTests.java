package com.tmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.tmall.quartz.task.TestTask;
import com.tmall.util.SpringContextHolder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		System.err.println("11111111111111111111111111111111111");
		TestTask bean = (TestTask)SpringContextHolder.getBean("testTask");
		System.err.println(JSONObject.toJSONString(bean));
	}

}
