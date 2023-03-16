package io.avec.securityldap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.avec.securityldap.ldap.LdapUser;
import io.avec.securityldap.model.AuthenticationRequest;
import io.avec.securityldap.model.AuthenticationResponse;
import io.avec.securityldap.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/mysite")
public class HomeController {

	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

//	@Autowired
	//private UserDetailsService userDetailsService;
	
	@GetMapping("/home")
	@ResponseBody
	public String homeWithoutLogin() {
		return "home withtout login";
	}
	
	@GetMapping("/home/normalUser")
	@ResponseBody
	public String homeLoginWithNormalUser() {
	//	log.info("session ="+httpRequest.getSession());
		log.info("homeLoginWithNormalUser=>reached... here....");
		return "home login with normal user";
	}
	
	@GetMapping("/home/adminUser")
	@ResponseBody
	public String homeLoginWithAdminUser() {
//		log.info("session ="+httpRequest.getSession());
		return "home login with admin user";
	}
	
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		
		Authentication auth=null;
		try {
			log.info("createAuthenticationToken-1");
			auth=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
				log.info("Incorrect username or password"+e.getMessage());
			return ResponseEntity.ok(new AuthenticationResponse("Incorrect username or password"+e.getMessage()));
		}
		catch (Exception e1) {
			log.info("exception="+e1.getMessage());
		return ResponseEntity.ok(new AuthenticationResponse("exception="+e1.getMessage()));
	}

log.info("auth.getDetails()=["+auth.getDetails()+"]");
log.info("auth.getPrincipal()=["+auth.getPrincipal()+"]");
log.info("auth.getCredentials()=["+auth.getCredentials()+"]");
log.info("auth.getPrincipal()=["+auth.getPrincipal().toString()+"]");

LdapUser user=(LdapUser)auth.getPrincipal();

log.info("auth.getUsername()=["+user.getUsername()+"]");
log.info("auth.getPassword()=["+user.getPassword()+"]");
log.info("auth.getCommonName()=["+user.getCommonName()+"]");
log.info("auth.getAuthorities()=["+user.getAuthorities()+"]");


		log.info("Before calling loadUserByUsername");
		//final UserDetails userDetails = userDetailsService
			//	.loadUserByUsername(authenticationRequest.getUsername());
		log.info("After calling loadUserByUsername");
		final String jwt = jwtTokenUtil.generateToken(auth);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
