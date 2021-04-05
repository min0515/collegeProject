package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_cart {
	private String member_id;
	private int g_seq;
	private int qty;
	private int g_price;
	private int totalprice;
	private String checkYn;

	private int deliveryTotalPrice;
	private String g_title;
	private int g_delivery;
	private String g_attach;
	private String orders_date;

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public int getG_seq() {
		return g_seq;
	}

	public void setG_seq(int g_seq) {
		this.g_seq = g_seq;
	}

	public String getCheckYn() {
		return checkYn;
	}

	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}

	public int getG_price() {
		return g_price;
	}

	public void setG_price(int g_price) {
		this.g_price = g_price;
	}

	public int getDeliveryTotalPrice() {
		return deliveryTotalPrice;
	}

	public void setDeliveryTotalPrice(int deliveryTotalPrice) {
		this.deliveryTotalPrice = deliveryTotalPrice;
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

	public String getG_attach() {
		return g_attach;
	}

	public void setG_attach(String g_attach) {
		this.g_attach = g_attach;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

}
