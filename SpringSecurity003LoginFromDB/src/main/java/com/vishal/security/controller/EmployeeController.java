package com.vishal.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeController {

	@GetMapping("/employeeHome")
	public String employeeHome() {
		return "employee-home";
	}
	@GetMapping("/employeeNew")
	public String employeeNew() {
		return "employee-new";
	}
	@GetMapping("/employee/{id}")
	public String employeeView(@PathVariable("id") String id) {
		return "employee-view";
	}
	
}
