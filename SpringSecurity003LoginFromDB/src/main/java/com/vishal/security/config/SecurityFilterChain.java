package com.vishal.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity(debug = true)
public class SecurityFilterChain extends WebSecurityConfigurerAdapter {

	
	@Autowired
	DataSource datasource;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication().dataSource(datasource);
	}
	
}
