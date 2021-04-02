package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_grade {
	private String term_no;
	private String student_no;
	private String class_no;
	private Double point;

	public String getTerm_no() {
		return term_no;
	}

	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point2) {
		this.point = point2;
	}
}
