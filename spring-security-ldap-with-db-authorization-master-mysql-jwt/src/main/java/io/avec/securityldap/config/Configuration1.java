package io.avec.securityldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.search.LdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsManager;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Configuration1 {


	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("hi");
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean(name = "authenticationManager")
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
	            throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		
		
		UserDetails normalUsers=  User.withUsername("vishal").password(passwordEncoder().encode("vishal")).roles("NORMAL").build();
		
		UserDetails adminUsers=  User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
		
		
		UserDetails monitoringUsers=  User.withUsername("superadmin").password(passwordEncoder().encode("superadmin")).authorities("SUPERADMIN-SESSION").build();
				//roles("SUPERADMIN").build();
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(normalUsers,adminUsers,monitoringUsers);
		
		return inMemoryUserDetailsManager;
		
		
		
	}
	*/
	
/*	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails normalUsers=  User.withUsername("vishal").password(passwordEncoder().encode("vishal")).roles("NORMAL").build();
		
		UserDetails adminUsers=  User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
		
		
		UserDetails monitoringUsers=  User.withUsername("superadmin").password(passwordEncoder().encode("superadmin")).authorities("SUPERADMIN-SESSION").build();
				//roles("SUPERADMIN").build();
		
		InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(normalUsers,adminUsers,monitoringUsers);
		
		return inMemoryUserDetailsManager;
	}
	*/
}
