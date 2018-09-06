package com.sckj.project19tea;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Properties;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication//(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@MapperScan(basePackages = {"com.sckj.repository.mybatis"})
@ComponentScan(basePackages = {"com.*"})
@EntityScan(basePackages = {"com.sckj.model"})
@EnableJpaRepositories(basePackages = {"com.sckj.repository"})
public class Project19teaApplication
		//extends SpringBootServletInitializer
{

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Project19teaApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(Project19teaApplication.class, args);
	}

	@Bean
	PageHelper pageHelper(){
		//分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);

		//添加插件
		new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});
		return pageHelper;
	}



}
