package com.vishal.spring.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.PreparableStatement;
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
			PreparedStatement ps=	connection.prepareStatement("insert into employeem(name,email,gender,pwd,role,status,c_by,c_on) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getEmail());
			ps.setInt(3, dto.getGender());
			ps.setString(4, dto.getPwd());
			ps.setString(5, dto.getRole());
			ps.setInt(6, 1);
			ps.setInt(7, dto.getC_by());
			ps.setTimestamp(8, dto.getC_on());
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
