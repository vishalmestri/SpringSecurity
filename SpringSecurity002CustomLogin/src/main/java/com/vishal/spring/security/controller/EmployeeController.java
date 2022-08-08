package com.vishal.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

	@GetMapping("/aboutUs")
	public String aboutUs() {
		
		return "employee-aboutus";
	}
	
	@GetMapping("/customLogin")
	public String login() {
		
		return "login";
	}
	
	
	@GetMapping("/employeeHome")
	public String employeeHome() {
		
		return "employee-home";
	}
	
	@GetMapping("/employeeNew")
	public  String employeeNew() {
		
		return "employee-new";
	}
	
	@PostMapping("/employeeAdd")
	public String proceeEmployee() {
		return "redirect:/employeeHome";
	}
	
	@GetMapping("/employeeView")
	public  String employeeView() {
		
		return "employee-view";
	}
}
