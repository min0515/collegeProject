package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_class {
	private String class_no;
	private String department_no;
	private String preattending_class_no;
	private String class_name;
	private String class_type;
	private int credit;
	private int maxcapacity;
	private int nowcapacity;
	// db에는 없는것들
	private int term_no;
	private String student_no;
	private int nowcredit;
	private float point;

	public String getClass_no() {
		return class_no;
	}

	public void setClass_no(String class_no) {
		this.class_no = class_no;
	}

	public String getDepartment_no() {
		return department_no;
	}

	public void setDepartment_no(String department_no) {
		this.department_no = department_no;
	}

	public String getPreattending_class_no() {
		return preattending_class_no;
	}

	public void setPreattending_class_no(String preattending_class_no) {
		this.preattending_class_no = preattending_class_no;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getClass_type() {
		return class_type;
	}

	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getMaxcapacity() {
		return maxcapacity;
	}

	public void setMaxcapacity(int maxcapacity) {
		this.maxcapacity = maxcapacity;
	}

	public int getNowcapacity() {
		return nowcapacity;
	}

	public void setNowcapacity(int nowcapacity) {
		this.nowcapacity = nowcapacity;
	}

	public int getTerm_no() {
		return term_no;
	}

	public void setTerm_no(int term_no) {
		this.term_no = term_no;
	}

	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	public int getNowcredit() {
		return nowcredit;
	}

	public void setNowcredit(int nowcredit) {
		this.nowcredit = nowcredit;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

}
