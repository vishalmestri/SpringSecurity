package com.vishal.spring.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class EmployeeDTO {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte getGender() {
		return gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public int getC_by() {
		return c_by;
	}
	public void setC_by(int c_by) {
		this.c_by = c_by;
	}
	public Timestamp getC_on() {
		return c_on;
	}
	public void setC_on(Timestamp c_on) {
		this.c_on = c_on;
	}
	public int getM_by() {
		return m_by;
	}
	public void setM_by(int m_by) {
		this.m_by = m_by;
	}
	public Timestamp getM_on() {
		return m_on;
	}
	public void setM_on(Timestamp m_on) {
		this.m_on = m_on;
	}
	private String name;
	private String email;
	public String getProfilePicServername() {
		return profilePicServername;
	}
	public void setProfilePicServername(String profilePicServername) {
		this.profilePicServername = profilePicServername;
	}
	public String getProfilePicUserName() {
		return profilePicUserName;
	}
	public void setProfilePicUserName(String profilePicUserName) {
		this.profilePicUserName = profilePicUserName;
	}
	private String profilePicServername;
	private String profilePicUserName;
	private String dob;
	private byte gender;
	private String pwd;
	private String role;
	private byte status;
	private int c_by;
	private Timestamp c_on;
	private int m_by;
	private Timestamp m_on;
	public EmployeeDTO(int id, String name, String email, byte gender, String pwd, String role, byte status, int c_by,
			Timestamp c_on, int m_by, Timestamp m_on) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.pwd = pwd;
		this.role = role;
		this.status = status;
		this.c_by = c_by;
		this.c_on = c_on;
		this.m_by = m_by;
		this.m_on = m_on;
	}
	
	
	private String profilePicFileContent;

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", name=" + name + ", email=" + email + ", profilePicServername="
				+ profilePicServername + ", profilePicUserName=" + profilePicUserName + ", dob=" + dob + ", gender="
				+ gender + ", pwd=" + pwd + ", role=" + role + ", status=" + status + ", c_by=" + c_by + ", c_on="
				+ c_on + ", m_by=" + m_by + ", m_on=" + m_on + "]";
	}
	public EmployeeDTO() {
		super();
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getProfilePicFileContent() {
		return profilePicFileContent;
	}
	public void setProfilePicFileContent(String profilePicFileContent) {
		this.profilePicFileContent = profilePicFileContent;
	}
	
	
}
