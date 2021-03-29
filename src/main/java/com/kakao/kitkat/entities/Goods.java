package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Goods {
	private int g_seq;
	private String g_title;
	private int g_price;
	private String g_content;
	private String g_attach;
	private int g_sellyn;
	private int g_qty;
	private int g_total;
	private int g_delivery;

	public Goods() {
	}; // Default Constructor 추가

	public static Goods createInstance(int g_seq, String g_title, int g_price, String g_content, String g_attach,
			int g_sellyn) {
		Goods goods = new Goods();
		goods.g_seq = g_seq;
		goods.g_title = g_title;
		goods.g_price = g_price;
		goods.g_content = g_content;
		goods.g_attach = g_attach;
		goods.g_sellyn = g_sellyn;
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

	public String getG_content() {
		return g_content;
	}

	public void setG_content(String g_content) {
		this.g_content = g_content;
	}

	public String getG_attach() {
		return g_attach;
	}

	public void setG_attach(String g_attach) {
		this.g_attach = g_attach;
	}

	public int getG_sellyn() {
		return g_sellyn;
	}

	public void setG_sellyn(int g_sellyn) {
		this.g_sellyn = g_sellyn;
	}

	public int getG_qty() {
		return g_qty;
	}

	public void setG_qty(int g_qty) {
		this.g_qty = g_qty;
	}

	public int getG_total() {
		return g_total;
	}

	public void setG_total(int g_total) {
		this.g_total = g_total;
	}

	public int getG_delivery() {
		return g_delivery;
	}

	public void setG_delivery(int g_delivery) {
		this.g_delivery = g_delivery;
	}

}
