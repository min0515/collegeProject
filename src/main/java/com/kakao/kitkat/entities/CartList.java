package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class CartList {
	private int orders_seq;
	private String member_id;
	private String orders_name;
	private String orders_date;
	private String orders_address;
	private String orders_zipcode;
	private int orders_qty;
	private int orders_total;
	private int g_seq;
	private String g_title;
	private int g_price;
	private int g_qty;
	private int g_delivery;
	private int info_seq;
	private String g_content;
	private String g_attach;

	public int getOrders_seq() {
		return orders_seq;
	}

	public void setOrders_seq(int orders_seq) {
		this.orders_seq = orders_seq;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getOrders_name() {
		return orders_name;
	}

	public void setOrders_name(String orders_name) {
		this.orders_name = orders_name;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public String getOrders_address() {
		return orders_address;
	}

	public void setOrders_address(String orders_address) {
		this.orders_address = orders_address;
	}

	public String getOrders_zipcode() {
		return orders_zipcode;
	}

	public void setOrders_zipcode(String orders_zipcode) {
		this.orders_zipcode = orders_zipcode;
	}

	public int getOrders_qty() {
		return orders_qty;
	}

	public void setOrders_qty(int orders_qty) {
		this.orders_qty = orders_qty;
	}

	public int getOrders_total() {
		return orders_total;
	}

	public void setOrders_total(int orders_total) {
		this.orders_total = orders_total;
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

	public int getInfo_seq() {
		return info_seq;
	}

	public void setInfo_seq(int info_seq) {
		this.info_seq = info_seq;
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

}
