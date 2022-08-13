package com.vishal.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mysql.cj.log.Log;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class EmployeeController {

	
	
	
	@GetMapping("/customerLogin")
	public String customerLogin() {
		
		
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Authentication auth) {
		
		System.out.println("auth.getPrincipal().toString()="+auth.getPrincipal().toString());
		System.out.println("auth.getName()="+auth.getName());
		return "dashboard";
	}
	
	@GetMapping("/employeeHome")
	public String employeeHome() {
		
		return "employeeHome";
	}
	
	@GetMapping("/employeeNew")
	public String employeeNew() {
		
		return "employeeNew";
	}
	
	@PostMapping
	public String employeeAdd() {
	
		return "redirect:/employeeHome";
	}
	
	@GetMapping("/employeeView")
	public String employeeView() {
		
		return "employeeView";
	}
}
