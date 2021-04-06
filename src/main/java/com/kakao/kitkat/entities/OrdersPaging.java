package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class OrdersPaging {
	private String find;
	private int startrow;
	private int endrow;
	
	public OrdersPaging(){}; //Default Constructor 추가

	public String getFind() {
		return find;
	}

	public void setFind(String find) {
		this.find = find;
	}

	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getEndrow() {
		return endrow;
	}

	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}

}
