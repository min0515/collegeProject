package com.kakao.kitkat.dao;

import com.kakao.kitkat.entities.Orders;

public interface OrdersDao {

	public Orders ordersSelectOne(String member_id) throws Exception;

	public int goodsInsertRow(Orders Orders) throws Exception;

	public int contactMaxNum() throws Exception;

	public int OrderssAdd(Orders Orders) throws Exception;

	public int OrderssAdd2(Orders Orders) throws Exception;

}