package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Comment {
	private int b_seq;
	private int comment_step;
	private String comment_name;
	private String comment_inputtime;
	private String comment_content;
	
	public int getB_seq() {
		return b_seq;
	}
	public void setB_seq(int b_seq) {
		this.b_seq = b_seq;
	}
	public int getComment_step() {
		return comment_step;
	}
	public void setComment_step(int comment_step) {
		this.comment_step = comment_step;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public String getComment_inputtime() {
		return comment_inputtime;
	}
	public void setComment_inputtime(String comment_inputtime) {
		this.comment_inputtime = comment_inputtime;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}


	
}