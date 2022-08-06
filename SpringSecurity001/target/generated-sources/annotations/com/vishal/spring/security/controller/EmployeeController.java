package com.vishal.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

	
	@GetMapping("getAllEmployees")
	public String getAllEmployees() {
		
		
		return "employee-home";
	}
	
	@GetMapping("newEmployee")
	public String newEmployee() {
		
		
		return "employee-new";
	}
	
	
	@PostMapping("addEmployee")
	public String addEmployee() {
		
		
		return "employee-home";
	}
	
	
	@GetMapping("getEmployee/{id}")
	public String getEmployee(@PathVariable("id") String id) {
		
		
		return "employee-view";
	}
	
	
}
