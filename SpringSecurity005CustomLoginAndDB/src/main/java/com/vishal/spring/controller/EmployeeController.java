package com.vishal.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vishal.spring.dao.EmployeeDAO;
import com.vishal.spring.dto.EmployeeDTO;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;
	
	@GetMapping("/customLogin")
	public String customLogin() {
		
		return "customLogin";
	}
	
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		List<EmployeeDTO> list=employeeDAO.getAllUsers();
		model.addAttribute("employeeList", list);
		return "dashboard";
	}
	
	@GetMapping("/employeeNew")
	public String employeeNew(@ModelAttribute("employee")EmployeeDTO employee ) {
		
		return "employeeNew";
	}
	
	@PostMapping("/employeeAdd")
	public String employeeAdd(@ModelAttribute("employee")EmployeeDTO employee, BindingResult bindingResult) {
		
		System.out.println(employee);
		String error="";
		
		EmployeeDTO fromDB=employeeDAO.addUser(employee);
		if(fromDB!=null) {
			
		}else {
			error="Problem while inserting";
		}
		return "redirect:/dashboard?error="+error;
	}
	
	
	
	
	@GetMapping("/delete/{id}")
	public String employeeDelete(@PathVariable("id") int id,Model model) {
		
		EmployeeDTO employeeDTO=employeeDAO.getEmployee(id);
		model.addAttribute("employee", employeeDTO);
		return "employeeDelete";
	}
	
	@GetMapping("/edit/{id}")
	public String employeeEdit(@PathVariable("id") int id,Model model) {
		
		EmployeeDTO employeeDTO=employeeDAO.getEmployee(id);
		model.addAttribute("employee", employeeDTO);
		
		return "employeeEdit";
	}
	
	@PostMapping("/employeeEdit")
	public String employeeEdit1(@ModelAttribute("employee")EmployeeDTO employee, BindingResult bindingResult) {
		
		System.out.println(employee);
		String error="";
		
		int count=employeeDAO.editUser(employee);
		if(count>0) {
			
		}else {
			error="Problem while updating";
		}
		return "redirect:/dashboard?error="+error;
	}
	
	@PostMapping("/employeeDelete")
	public String employeeDelete(@ModelAttribute("employee")EmployeeDTO employee, BindingResult bindingResult) {
		
		System.out.println(employee);
		String error="";
		
		int count=employeeDAO.deleteUser(employee);
		if(count>0) {
			
		}else {
			error="Problem while deleting";
		}
		return "redirect:/dashboard?error="+error;
	}
	
	

	
	@GetMapping("/view/{id}")
	public String employeeView(@PathVariable("id") int id,Model model) {
		
		
		EmployeeDTO employeeDTO=employeeDAO.getEmployee(id);
		model.addAttribute("employee", employeeDTO);
		return "employeeView";
	}
	
	
	
}
