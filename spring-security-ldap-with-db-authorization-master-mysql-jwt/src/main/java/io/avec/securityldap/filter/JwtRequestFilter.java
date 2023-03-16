package io.avec.securityldap.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.avec.securityldap.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component("JwtRequestFilter")
//@Order(Integer.MIN_VALUE)
public class JwtRequestFilter extends OncePerRequestFilter {

  //  @Autowired
   // public UserDetailsService userDetailsService;

    @Autowired
    public JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        
        log.info("authorizationHeade1r="+authorizationHeader);
        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        if (username != null ) {

         //   UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean flag=jwtUtil.validateToken(jwt);
            log.info("username=flag="+flag+"|");
            //userDetails.getAuthorities().stream().forEach(a->log.info(a.getAuthority()));
           // jwtUtil.generateToken(auth)
            if (flag) {

            	
            	Claims claim=jwtUtil.extractAllClaims(jwt);
            	log.info("username=flag=1");
            	
            	List<String> l=claim.get("roles", List.class);
            	
            	l.stream().forEach(x->System.out.println(x));
            	
            	
            	List<GrantedAuthority> list1=l.stream().map(x-> new SimpleGrantedAuthority(x)).collect(Collectors.toList());
            	
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, list1);
                log.info("username=flag=2");
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("username=flag=3");
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.info("username=flag=4");
               // return;
                
            }
        }
        log.info("username=flag=5");
        chain.doFilter(request, response);
    }

}