package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_class_professor {
	private String class_no;
	private String professor_no;
	private String class_name;

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public String getProfessor_no() {
		return professor_no;
	}

	public void setProfessor_no(String professor_no) {
		this.professor_no = professor_no;
	}

}
