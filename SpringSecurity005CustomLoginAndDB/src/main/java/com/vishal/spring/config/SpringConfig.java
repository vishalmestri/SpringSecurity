package com.vishal.spring.config;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.vishal.spring"})
public class SpringConfig extends WebMvcConfigurerAdapter {

	@Autowired
	DataSource datasource;
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("addResourceHandlers called...");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/view/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		
		
		return internalResourceViewResolver;
	}
	
	@Bean
	JdbcTemplate jdbcTemplate() {
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(datasource);
		return jdbcTemplate;
	}
	
	@Bean
	DataSource dataSource() {
		
		
		DriverManagerDataSource datasource=new DriverManagerDataSource("jdbc:mysql://localhost:3306/springsecurity?usessl=false","root","root");
		
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		
		return datasource;
	}
	
	@Bean
	UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager j=new JdbcUserDetailsManager(dataSource());
		
		j.setUsersByUsernameQuery("select email,pwd,status from employeem where email = ?");
		j.setAuthoritiesByUsernameQuery("select email,role from employeem where email = ?");
		return j;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }
	
	
}
