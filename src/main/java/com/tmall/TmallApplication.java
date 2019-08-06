package com.tmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@MapperScan("com.tmall.dao")
@EnableTransactionManagement // 开启事务支持
public class TmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmallApplication.class, args);
	}

}
