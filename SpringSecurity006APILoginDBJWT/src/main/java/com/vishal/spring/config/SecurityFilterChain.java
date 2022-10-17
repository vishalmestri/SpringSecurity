package com.vishal.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vishal.spring.filter.JwtRequestFilter;


@EnableWebSecurity(debug = true)
public class SecurityFilterChain extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 System.out.println("Reached here -3");

		auth.userDetailsService(userDetailsManager);
		 System.out.println("Reached here -4");

		
	}
	
	@Override
	@Bean(name = "authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println("authenticationManagerBean-1");
		return super.authenticationManagerBean();
	}
	
	 @Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		 
		 System.out.println("Reached here -1");
		 http.csrf().disable()
		 .authorizeRequests()
		/* .antMatchers("/**").permitAll()*/
		 
		 .antMatchers("/employeeNew","/employeeAdd").hasAuthority("admin")
		 .antMatchers("/authenticate","/customLogin","/resources/**").permitAll()
		 . anyRequest().authenticated()
		 .and()
		 .exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); 
		 System.out.println("Reached here -2");
		 
		 /*.formLogin().loginPage("/customLogin").loginProcessingUrl("/loginProcess").defaultSuccessUrl("/dashboard").and()
		 .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 .logoutSuccessUrl("/customLogin")
		 .invalidateHttpSession(true)
		 .deleteCookies("JSESSIONID");
		 */
	}
}
