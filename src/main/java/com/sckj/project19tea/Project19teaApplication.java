package com.sckj.project19tea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sckj.dao")
@ComponentScan(basePackages = {"com.sckj.*"})
public class Project19teaApplication {
//
	public static void main(String[] args) {
		SpringApplication.run(Project19teaApplication.class, args);
	}
}
