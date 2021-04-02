package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class CartList {
	private int g_seq;
	private String g_title;
	private int g_price;
	private String g_content;
	private String g_attach;
	private int g_sellyn;
	private int g_qty;
	private int g_total;
	private int g_delivery;
	private String g_info;
	private int orders_seq;
	private String member_id;
	private String orders_name;
	private String orders_contact;
	private String orders_address;
	private String orders_zipcode;
	private int orders_qty;
	private String orders_pay;
	private String orders_delivery;

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

	public String getG_info() {
		return g_info;
	}

	public void setG_info(String g_info) {
		this.g_info = g_info;
	}

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

	public String getOrders_pay() {
		return orders_pay;
	}

	public void setOrders_pay(String orders_pay) {
		this.orders_pay = orders_pay;
	}

	public String getOrders_delivery() {
		return orders_delivery;
	}

	public void setOrders_delivery(String orders_delivery) {
		this.orders_delivery = orders_delivery;
	}

}
