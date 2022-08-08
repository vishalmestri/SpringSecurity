package com.vishal.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.vishal.spring"})
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		
		
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/jsp/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

}
