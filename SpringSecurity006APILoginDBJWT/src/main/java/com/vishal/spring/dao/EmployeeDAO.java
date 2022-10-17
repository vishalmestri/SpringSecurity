package com.vishal.spring.dao;

import java.util.List;

import com.vishal.spring.dto.EmployeeDTO;


public interface EmployeeDAO {

	public EmployeeDTO addUser(EmployeeDTO dto);
	
	public List<EmployeeDTO> getAllUsers();

	public EmployeeDTO getEmployee(int id);

	public int editUser(EmployeeDTO employee);

	public int deleteUser(EmployeeDTO employee);
}
