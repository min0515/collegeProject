package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Orders {
	private int orders_seq;
	private String member_id;
	private String orders_name;
	private String orders_contact;
	private String orders_address;
	private String orders_zipcode;
	private String g_title;
	private int orders_qty;
	private int g_seq;
	private int g_delivery;
	private int g_total;
	private String orders_pay;

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

	public String getOrders_contact() {
		return orders_contact;
	}

	public void setOrders_contact(String orders_contact) {
		this.orders_contact = orders_contact;
	}

	public String getOrders_zipcode() {
		return orders_zipcode;
	}

	public void setOrders_zipcode(String orders_zipcode) {
		this.orders_zipcode = orders_zipcode;
	}

	public String getOrders_address() {
		return orders_address;
	}

	public void setOrders_address(String orders_address) {
		this.orders_address = orders_address;
	}

	public int getOrders_qty() {
		return orders_qty;
	}

	public void setOrders_qty(int orders_qty) {
		this.orders_qty = orders_qty;
	}

	public String getOrders_pay() {
		return orders_pay;
	}

	public String getG_title() {
		return g_title;
	}

	public void setG_title(String g_title) {
		this.g_title = g_title;
	}

	public int getG_delivery() {
		return g_delivery;
	}

	public void setG_delivery(int g_delivery) {
		this.g_delivery = g_delivery;
	}

	public int getG_total() {
		return g_total;
	}

	public void setG_total(int g_total) {
		this.g_total = g_total;
	}

	public void setOrders_pay(String orders_pay) {
		this.orders_pay = orders_pay;
	}

	public int getG_seq() {
		return g_seq;
	}

	public void setG_seq(int g_seq) {
		this.g_seq = g_seq;
	}

}
