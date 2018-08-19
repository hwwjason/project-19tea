package com.sckj.project19tea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@MapperScan(basePackages = {"com.sckj.dao"})
@ComponentScan(basePackages = {"com.*"})
@EntityScan(basePackages = {"com.sckj.pojo"})
@EnableJpaRepositories(basePackages = {"com.sckj.jpa"})
public class Project19teaApplication {
//
	public static void main(String[] args) {
		SpringApplication.run(Project19teaApplication.class, args);
	}
}
