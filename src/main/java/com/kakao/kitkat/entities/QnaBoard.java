package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class QnaBoard {
	private int qna_seq;
	private String qna_title;
	private String qna_content;
	private String qna_writer;
	private String qna_attach;
	public int getQna_seq() {
		return qna_seq;
	}
	public void setQna_seq(int qna_seq) {
		this.qna_seq = qna_seq;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_writer() {
		return qna_writer;
	}
	public void setQna_writer(String qna_writer) {
		this.qna_writer = qna_writer;
	}
	public String getQna_attach() {
		return qna_attach;
	}
	public void setQna_attach(String qna_attach) {
		this.qna_attach = qna_attach;
	}
	
	
}
