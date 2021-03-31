package com.kakao.kitkat.entities;

import org.springframework.stereotype.Component;

@Component
public class Tb_cart {
	private String member_id;
	private int g_seq;
	private int qty;
	private int price;
	private int totalprice;
	private String g_title;
	private int g_delivery;
	private String g_attach;
	private String checkYn;
	private int deliveryTotalPrice;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public String getG_title() {
		return g_title;
	}

	public void setG_title(String g_title) {
		this.g_title = g_title;
	}

	public int getG_deilvery() {
		return g_delivery;
	}

	public void setG_deilvery(int g_deilvery) {
		this.g_delivery = g_deilvery;
	}

	public String getG_attach() {
		return g_attach;
	}

	public void setG_attach(String g_attach) {
		this.g_attach = g_attach;
	}

	public String getCheckYn() {
		return checkYn;
	}

	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}

	public int getDeliveryTotalPrice() {
		return deliveryTotalPrice;
	}

	public void setDeliveryTotalPrice(int deliveryTotalPrice) {
		this.deliveryTotalPrice = deliveryTotalPrice;
	}

	public int getG_delivery() {
		return g_delivery;
	}

	public void setG_delivery(int g_delivery) {
		this.g_delivery = g_delivery;
	}

}
