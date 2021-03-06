package com.sckj.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
 如果将要访问的静态资源放在项目的类路径下面即配置为"classpath:/BookPicture/"那么当该路径下的资      源发生变化时是不立即生效的，即只有重启后才能访问到变化的资源；
 解决办法将静态资源放在非项目类路径下即可 "file:F:/bookpicture/");即下面这句
  registry.addResourceHandler("/bookpicture/**").addResourceLocations("file:C:/bookpicture/"); 
*/
@Configuration
public class StateResourceConfigurer extends WebMvcConfigurerAdapter {
    /**
     * 配置访问静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/Users/hww/IdeaProjects/ShouChaKj/project-19tea/src/main/resources/static/shoucha/**").addResourceLocations("classpath:/Users/hww/IdeaProjects/ShouChaKj/project-19tea/src/main/resources/static/shoucha/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/we/**").addResourceLocations("file:F:/preview/");

        super.addResourceHandlers(registry);
    }
}
