package com.vishal.spring.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.vishal.spring.dao.EmployeeDAO;
import com.vishal.spring.dto.EmployeeDTO;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	Logger logger=Logger.getLogger(EmployeeDAOImpl.class.toString());
	
	@Override
	public EmployeeDTO addUser(EmployeeDTO dto) {
		// TODO Auto-generated method stub
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		int count=jdbcTemplate.update(connection -> {
			PreparedStatement ps=	connection.prepareStatement("insert into employeem(name,email,dob,gender,pwd,role,status,c_by,c_on,profilePicServername,profilePicUserName) values(?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getEmail());
			ps.setString(3, dto.getDob());
			ps.setInt(4, dto.getGender());
			ps.setString(5, dto.getPwd());
			ps.setString(6, dto.getRole());
			ps.setInt(7, 1);
			ps.setInt(8, dto.getC_by());
			ps.setTimestamp(9, dto.getC_on());
			ps.setString(10, dto.getProfilePicServername());
			ps.setString(11, dto.getProfilePicUserName());
		return ps;
		},keyHolder);

		logger.info("Count of records inserted in Employee :"+count);
		
		if(count >0) {
			dto.setId(keyHolder.getKey().intValue());
			logger.info(dto.toString());
			return dto;
		}else {
			return null;
		}
		
//		return dto;
	}

	@Override
	public List<EmployeeDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from employeem where status = 1",new BeanPropertyRowMapper(EmployeeDTO.class));
	}
	
	@Override
	public EmployeeDTO getEmployee(int id) {
		// TODO Auto-generated method stub
		Object arry[]= {id};
		List<EmployeeDTO> list=jdbcTemplate.query("select * from employeem where id =?",new BeanPropertyRowMapper(EmployeeDTO.class),arry);
		if(list.size()>0) {
			
			EmployeeDTO dto=list.get(0);
			
			
			File file1 =new File("F:\\ws\\z-uploadedFiles\\"+dto.getProfilePicUserName());
			
			try {
			//	FileInputStream input=new FileInputStream(file1);
				
				dto.setProfilePicFileContent(java.util.Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file1)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return list.get(0);
		}else {
			return null;
		}
	}
	
	@Override
		public int editUser(EmployeeDTO employee) {
			// TODO Auto-generated method stub
		
		Object arry[]= {employee.getName(),employee.getEmail(),employee.getGender(),employee.getRole(),employee.getId()};
		int count=jdbcTemplate.update("update employeem set name=?, email=?, gender=? ,role=? where id =?",arry);
		
			return count;
		}	
	
	
	@Override
	public int deleteUser(EmployeeDTO employee) {
		// TODO Auto-generated method stub
	
	Object arry[]= {employee.getId()};
	int count=jdbcTemplate.update("update employeem set status = 0 where id =?",arry);
	
		return count;
	}	


}
