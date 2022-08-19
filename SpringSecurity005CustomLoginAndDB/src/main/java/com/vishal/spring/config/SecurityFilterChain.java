package com.vishal.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


@EnableWebSecurity(debug = true)
public class SecurityFilterChain extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsManager userDetailsManager;
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsManager);
		
		
	}
	
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		 http
		 .authorizeRequests()
		/* .antMatchers("/**").permitAll()*/
		 
		 .antMatchers("/employeeNew","/employeeAdd").hasAuthority("admin")
		 .antMatchers("/customLogin","/resources/**").permitAll()
		 . anyRequest().authenticated()
		 .and()
		 .formLogin().loginPage("/customLogin").loginProcessingUrl("/loginProcess").defaultSuccessUrl("/dashboard").and()
		 .logout();
		 
	}
}
