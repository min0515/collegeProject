package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_professor {
	private String professor_no;
	private String professor_name;
	private String professor_ssn;
	private String professor_address;
	private String department_no;
	private String professor_pw;
	private String department_name;
	private String memlevel;

	public String getProfessor_no() {
		return professor_no;
	}

	public void setProfessor_no(String professor_no) {
		this.professor_no = professor_no;
	}

	public String getProfessor_name() {
		return professor_name;
	}

	public void setProfessor_name(String professor_name) {
		this.professor_name = professor_name;
	}

	public String getProfessor_ssn() {
		return professor_ssn;
	}

	public void setProfessor_ssn(String professor_ssn) {
		this.professor_ssn = professor_ssn;
	}

	public String getProfessor_address() {
		return professor_address;
	}

	public void setProfessor_address(String professor_address) {
		this.professor_address = professor_address;
	}

	public String getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(String department_no) {
		this.department_no = department_no;
	}

	public String getProfessor_pw() {
		return professor_pw;
	}

	public void setProfessor_pw(String professor_pw) {
		this.professor_pw = professor_pw;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getMemlevel() {
		return memlevel;
	}

	public void setMemlevel(String memlevel) {
		this.memlevel = memlevel;
	}
	
	

}
