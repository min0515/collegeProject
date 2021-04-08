package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Goods_review {
	private String member_id;
	private int review_seq;
	private int g_seq;
	private String review_title;
	private String review_content;
	private String review_attach;
	private String review_date;

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getReview_seq() {
		return review_seq;
	}

	public void setReview_seq(int review_seq) {
		this.review_seq = review_seq;
	}

	public int getG_seq() {
		return g_seq;
	}

	public void setG_seq(int g_seq) {
		this.g_seq = g_seq;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getReview_attach() {
		return review_attach;
	}

	public void setReview_attach(String review_attach) {
		this.review_attach = review_attach;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

}
