package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Goods {
	private int g_seq;
	private String g_title;
	private int g_price;
	private int g_qty;
	private int g_delivery;

	public Goods() {
	}; // Default Constructor 추가

	public static Goods createInstance(int g_seq, String g_title, int g_price, String g_content, String g_attach,
			int g_sellyn) {
		Goods goods = new Goods();
		goods.g_seq = g_seq;
		goods.g_title = g_title;
		goods.g_price = g_price;
		return goods;
	}

	public int getG_seq() {
		return g_seq;
	}

	public void setG_seq(int g_seq) {
		this.g_seq = g_seq;
	}

	public String getG_title() {
		return g_title;
	}

	public void setG_title(String g_title) {
		this.g_title = g_title;
	}

	public int getG_price() {
		return g_price;
	}

	public void setG_price(int g_price) {
		this.g_price = g_price;
	}

	public int getG_qty() {
		return g_qty;
	}

	public void setG_qty(int g_qty) {
		this.g_qty = g_qty;
	}

	public int getG_delivery() {
		return g_delivery;
	}

	public void setG_delivery(int g_delivery) {
		this.g_delivery = g_delivery;
	}

}
