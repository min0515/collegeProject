package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.CartList;
import com.kakao.kitkat.entities.Goods;
import com.kakao.kitkat.entities.GoodsPaging;
import com.kakao.kitkat.entities.Orders;
import com.kakao.kitkat.entities.OrdersPaging;
import com.kakao.kitkat.entities.Tb_cart;

public interface OrdersDao {

	public Orders ordersSelectOne(String member_id) throws Exception;

	public int goodsInsertRow(Orders Orders) throws Exception;

	public int contactMaxNum() throws Exception;

	public int OrderssAdd(Orders Orders) throws Exception;

	public int OrderssAdd2(Tb_cart tb_cart) throws Exception;

	public int ordersUpdateInsert(Orders orders);

	public int maxg_seq(int orders_seq) throws Exception;

	public int myOrdersqtySum(String member_id) throws Exception;

	public int myOrderstotalSum(String member_id) throws Exception;

	public ArrayList<CartList> selectproyo(String member_id);

	public ArrayList<Orders> ordersSelectPageList(OrdersPaging orderspaging);

	public ArrayList<Orders> ordersSelectAll() throws Exception;

	public int qtyUpdate(Tb_cart tb_cart);

}