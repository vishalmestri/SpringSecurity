package com.vishal.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.vishal.security"})
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/jsp/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	/*@Bean
	JdbcTemplate jdbcTemplate() {
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate();
		jdbcTemplate.setDataSource(datasource);
		return jdbcTemplate;
	}*/
	
	
	@Bean
	DataSource dataSource() {
		
DriverManagerDataSource datasource=new DriverManagerDataSource("jdbc:mysql://localhost:3306/springjdbc?usessl=false","root","root");
		
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		return datasource;
	}
	
}
