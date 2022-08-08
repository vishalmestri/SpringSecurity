package com.vishal.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity(debug = true)
public class FilterChain extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.inMemoryAuthentication()
		.withUser("vishal")
		.password("$2a$10$MVR0UVepmorbWhoGty8HC.wK.FfXl8iGp9p6QkjI0KHhNFcEz0zNS")
		.roles("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/employeeHome","/employeeNew","/employeeAdd","/employeeView").authenticated()
		.antMatchers("/aboutUs","/customLogin").permitAll()
		.anyRequest().denyAll()
		.and()
		.formLogin().loginPage("/customLogin").loginProcessingUrl("/loginProcessing").defaultSuccessUrl("/employeeHome");
	
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
