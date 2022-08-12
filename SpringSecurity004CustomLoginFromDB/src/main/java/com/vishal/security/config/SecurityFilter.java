package com.vishal.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity(debug = true)
public class SecurityFilter extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/dashboard","/employeeHome","/employeeNew","/employeeAdd").hasAuthority("admin")
		.antMatchers("/employeeView").hasAuthority("user")
		.antMatchers("/customerLogin","/loginProcess").permitAll()
		.and()
		.formLogin().loginPage("/customerLogin").loginProcessingUrl("/loginProcess").defaultSuccessUrl("/dashboard").and().logout();
	}
}
