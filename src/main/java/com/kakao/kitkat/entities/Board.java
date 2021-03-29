package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Board {
	private int b_seq;
	private int b_ref;
	private int b_step;
	private String b_studentno;
	private String b_name;
	private String b_title;
	private String b_content;
	private int b_hit;
	private String b_attach;
	private String b_inputtime;
	private String b_inputip;

	public int getB_hit() {
		return b_hit;
	}

	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}

	public String getB_inputtime() {
		return b_inputtime;
	}

	public void setB_inputtime(String b_inputtime) {
		this.b_inputtime = b_inputtime;
	}

	public String getB_inputip() {
		return b_inputip;
	}

	public void setB_inputip(String b_inputip) {
		this.b_inputip = b_inputip;
	}

	public int getB_seq() {
		return b_seq;
	}

	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
	}

	public int getB_ref() {
		return b_ref;
	}

	public void setB_ref(int b_ref) {
		this.b_ref = b_ref;
	}

	public int getB_step() {
		return b_step;
	}

	public void setB_step(int b_step) {
		this.b_step = b_step;
	}

	public String getB_studentno() {
		return b_studentno;
	}

	public void setB_studentno(String b_studentno) {
		this.b_studentno = b_studentno;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public int getB_int() {
		return b_hit;
	}

	public void setB_int(int b_int) {
		this.b_hit = b_int;
	}

	public String getB_attach() {
		return b_attach;
	}

	public void setB_attach(String b_attach) {
		this.b_attach = b_attach;
	}
}
