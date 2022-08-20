package com.vishal.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String dashboard(Model model,@RequestParam(required = false) Integer page) {
		
		List<EmployeeDTO> list=employeeDAO.getAllUsers();
		model.addAttribute("employeeList", list);
		pagination(model, list, page);
		return "dashboard";
	}
	
	@GetMapping("/employeeNew")
	public String employeeNew(@ModelAttribute("employee")EmployeeDTO employee ) {
		
		return "employeeNew";
	}
	
	@PostMapping("/employeeAdd")
	public String employeeAdd(@ModelAttribute("employee")EmployeeDTO employee, BindingResult bindingResult,@RequestParam("profilePicUserName") MultipartFile profilePicUserName) {
		
		System.out.println(employee);
		String error="";

		if(profilePicUserName!=null) {
			
			System.out.println("employee.getProfilePicUserName()="+employee.getProfilePicUserName());
			employee.setProfilePicUserName(profilePicUserName.getOriginalFilename());
			try {
				profilePicUserName.transferTo(new File("F:\\ws\\z-uploadedFiles\\"+profilePicUserName.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}
		}else {
			System.out.println("Multipart is null");
		}
		System.out.println();
		
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
	public String employeeEdit1(@ModelAttribute("employee")EmployeeDTO employee, BindingResult bindingResult,@RequestParam("profilePicUserName") MultipartFile profilePicUserName) {
		
		System.out.println(employee);
		String error="";
		
		if(profilePicUserName!=null) {
			
			System.out.println("employee.getProfilePicUserName()="+employee.getProfilePicUserName());
			System.out.println("profilePicUserName.getOriginalFilename()="+profilePicUserName.getOriginalFilename());
			employee.setProfilePicUserName(profilePicUserName.getOriginalFilename());
			try {
				profilePicUserName.transferTo(new File("F:\\ws\\z-uploadedFiles\\"+profilePicUserName.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(System.out);
			}
		}else {
			System.out.println("Multipart is null");
		}
		
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
	
	
	
	private void pagination(Model modelAndView,List<EmployeeDTO> list,Integer page) {
byte RECORDS_ON_ONE_PAGE=2;
		
		byte NUMBER_OF_PAGES=3;
		
		//---pagination-start
		//List<User> users = userService.getUsers();
        PagedListHolder<EmployeeDTO> pagedListHolder = new PagedListHolder<>(list);
        pagedListHolder.setPageSize(RECORDS_ON_ONE_PAGE);
        int maxPage=pagedListHolder.getPageCount();
        modelAndView.addAttribute("maxPages",maxPage);
        
       System.out.println("pagedListHolder.getPageCount()="+pagedListHolder.getPageCount());

        if(page==null || page < 1 || page > pagedListHolder.getPageCount())
        	page=1;

        modelAndView.addAttribute("page", page);
        
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addAttribute("users", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addAttribute("users", pagedListHolder.getPageList());
        }
        
        
        if(NUMBER_OF_PAGES>pagedListHolder.getPageCount()) {
        	NUMBER_OF_PAGES=(byte)pagedListHolder.getPageCount();
        }
        
        
        modelAndView.addAttribute("NUMBER_OF_PAGES", NUMBER_OF_PAGES);
        
        
        
        int lastPageInPagination=0;
        int firstPageInPagination=page/NUMBER_OF_PAGES;
     	firstPageInPagination=firstPageInPagination*NUMBER_OF_PAGES;
     	if(page!=firstPageInPagination)
     	{	
     		firstPageInPagination++;
     		lastPageInPagination=firstPageInPagination+NUMBER_OF_PAGES-1;
     	}else {
     		lastPageInPagination=firstPageInPagination;
     		firstPageInPagination=firstPageInPagination-NUMBER_OF_PAGES+1;
     		
     	}
     	
     	
     	if(lastPageInPagination>maxPage) {
     		lastPageInPagination=maxPage;
     	}
     	
        //int remainder=page%NUMBER_OF_PAGES;
        
        System.out.println("page="+page+"|firstPageInPagination="+firstPageInPagination+"|lastPageInPagination="+lastPageInPagination);
        
        modelAndView.addAttribute("lastPageInPagination", lastPageInPagination);
        modelAndView.addAttribute("firstPageInPagination", firstPageInPagination);
        
        //---pagination-end
	
	}
	
}
