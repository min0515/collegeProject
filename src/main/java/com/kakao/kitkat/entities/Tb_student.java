package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_student {
	private String student_no;
	private String department_no;
	private String student_name;
	private String absence_yn;
	private String student_pw;
	private int maxcredit;
	private int nowcredit;
	private String entrance_date;
	private String student_address;
	private String memlevel;
	private String student_ssn;
	private String coach_professor_no;

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	public String getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(String department_no) {
		this.department_no = department_no;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getAbsence_yn() {
		return absence_yn;
	}

	public void setAbsence_yn(String absence_yn) {
		this.absence_yn = absence_yn;
	}

	public int getMaxcredit() {
		return maxcredit;
	}

	public void setMaxcredit(int maxcredit) {
		this.maxcredit = maxcredit;
	}

	public int getNowcredit() {
		return nowcredit;
	}

	public void setNowcredit(int nowcredit) {
		this.nowcredit = nowcredit;
	}

	public String getEntrance_date() {
		return entrance_date;
	}

	public void setEntrance_date(String entrance_date) {
		this.entrance_date = entrance_date;
	}

	public String getStudent_pw() {
		return student_pw;
	}

	public void setStudent_pw(String student_pw) {
		this.student_pw = student_pw;
	}

	public String getStudent_address() {
		return student_address;
	}

	public void setStudent_address(String student_address) {
		this.student_address = student_address;
	}

	public String getMemlevel() {
		return memlevel;
	}

	public void setMemlevel(String memlevel) {
		this.memlevel = memlevel;
	}

	public String getStudent_ssn() {
		return student_ssn;
	}

	public void setStudent_ssn(String student_ssn) {
		this.student_ssn = student_ssn;
	}

	public String getCoach_professor_no() {
		return coach_professor_no;
	}

	public void setCoach_professor_no(String coach_professor_no) {
		this.coach_professor_no = coach_professor_no;
	}

	
}
