package com.kakao.kitkat.dao;

import com.kakao.kitkat.entities.Orders;
import com.kakao.kitkat.entities.Tb_cart;

public interface OrdersDao {

	public Orders ordersSelectOne(String member_id) throws Exception;

	public int goodsInsertRow(Orders Orders) throws Exception;

	public int contactMaxNum() throws Exception;

	public int OrderssAdd(Orders Orders) throws Exception;

	public int OrderssAdd2(Tb_cart tb_cart) throws Exception;

	public int rororororor(Orders orders);

	public int maxg_seq(int orders_seq) throws Exception;

}