package io.avec.securityldap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;

import io.avec.securityldap.data.authorities.AuthoritiesRepository;
import io.avec.securityldap.data.user.MyUserRepository;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig  {

	//@Autowired
	//private WebSecurity web;
	
	@Autowired
	private TemplateEngine templateEngine;
	
    private final AuthoritiesRepository authoritiesRepository;
    private final MyUserRepository myUserRepository;

    public WebSecurityConfig(AuthoritiesRepository authoritiesRepository, MyUserRepository myUserRepository) {
    	System.out.println("WebSecurityConfig- constructor called-start");
        this.authoritiesRepository = authoritiesRepository;
        this.myUserRepository = myUserRepository;
        
        
    }

  /*  @Bean
    public TemplateEngine templateEngine() {
    	System.out.println("WebSecurityConfig- templateEngine -start");
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new SpringSecurityDialect());
        engine.setEnableSpringELCompiler(true);
 //       engine.setTemplateResolver(templateResolver());
        System.out.println("WebSecurityConfig- templateEngine -end");
        return engine;
    }
    */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		templateEngine.addDialect(new SpringSecurityDialect());
	/*	httpSecurity.csrf().disable().
					authorizeHttpRequests()
					.requestMatchers("/mysite/home")
					.permitAll()
					.anyRequest().authenticated()
					.and()
					.formLogin();
		*/
		System.out.println("hi");
		/*httpSecurity.
		
		csrf().disable().
		authorizeHttpRequests()
		.requestMatchers("/mysite/home/normalUser/**").hasRole("NORMAL")
		.requestMatchers("/mysite/home/adminUser/**").hasRole("ADMIN")
		//.requestMatchers("/actuator/**").hasRole("SUPERADMIN")
		.requestMatchers("/actuator/**").hasAuthority("SUPERADMIN-SESSION")
		
		.requestMatchers("/mysite/home","/mysite/authenticate").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		*/
		//httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); 
		
	/*	
		 auth
         // https://spring.io/guides/gs/authenticating-ldap/
         .ldapAuthentication()
         .ldapAuthoritiesPopulator(new CustomAuthoritiesPopulator(authoritiesRepository, myUserRepository))
         .userDetailsContextMapper(new CustomUserDetailsMapper())
         .userDnPatterns("uid={0},ou=people")
         .groupSearchBase("ou=groups")
         .contextSource()
         .url("ldap://localhost:8389/dc=springframework,dc=org")
         .and()
         .passwordCompare()
         .passwordEncoder(new BCryptPasswordEncoder())
         .passwordAttribute("userPassword");
		*/ 
		 
		 System.out.println("WebSecurityConfig- configure3-start");
		 /*
		// web.ignoring().requestMatchers(patterns)
	        web.ignoring().requestMatchers(
	                "/h2-console/**"
	        );
	       */ 

  
	        System.out.println("WebSecurityConfig- configure3-end");
	        httpSecurity.
			authorizeHttpRequests()

			.requestMatchers("/","/h2-console/**","/actuator/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().defaultSuccessUrl("/hello", true)
            .and()
        .logout().logoutSuccessUrl("/");
	        
			
	        
		return httpSecurity.build();
	}
    /*
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("WebSecurityConfig- configure-start");
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll() // Open for all
                    .anyRequest().authenticated() // All others requires authentication
                    .and()
                .formLogin()
                    .defaultSuccessUrl("/hello", true)
                    .and()
                .logout().logoutSuccessUrl("/");
        System.out.println("WebSecurityConfig- configure-end");
    }*/


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("WebSecurityConfig- configure1-start");
        auth
                // https://spring.io/guides/gs/authenticating-ldap/
                .ldapAuthentication()
                .ldapAuthoritiesPopulator(new CustomAuthoritiesPopulator(authoritiesRepository, myUserRepository))
                .userDetailsContextMapper(new CustomUserDetailsMapper())
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:8389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                .passwordAttribute("userPassword");
        System.out.println("WebSecurityConfig- configure1-end");

    }

    /* https://stackoverflow.com/questions/52973232/spring-security-ldap-authentication-and-gather-user-details-from-local-database
    // https://medium.com/@viraj.rajaguru/how-to-use-spring-security-to-authenticate-with-microsoft-active-directory-1caff11c57f2
    // Also https://www.ziaconsulting.com/developer-help/spring-security-active-directory/
    // And this one https://tcl-digitrade.com/2017/01/12/spring-boot-active-directory-authentication/
    // and https://javarevisited.blogspot.com/2018/07/ldap-authentication-active-directory-authentication-java-spring-security-example.html
    // https://geeks18.com/?p=474 Nice figure showing different authentication optios

    // this replaces
    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider(){
        ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider = new
                ActiveDirectoryLdapAuthenticationProvider("pizzashop.com.us", "ldap://10.100.100.100:389/");
        return activeDirectoryLdapAuthenticationProvider;
    }*/

    
    /*
    @Override
    public void configure(WebSecurity web) {
    	System.out.println("WebSecurityConfig- configure3-start");
        web.ignoring().antMatchers(
                "/h2-console/**"
        );
        System.out.println("WebSecurityConfig- configure3-end");
    }
*/
}
