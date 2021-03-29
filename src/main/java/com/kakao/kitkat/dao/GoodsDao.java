package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Goods;
import com.kakao.kitkat.entities.GoodsPaging;
import com.kakao.kitkat.entities.Tb_cart;

public interface GoodsDao {

	public Goods goodsSelectOne(int g_seq) throws Exception;

	public int goodsInsertRow(Goods Goods) throws Exception;

	public int goodsSelectCountFirst(GoodsPaging goodspaging) throws Exception;

	public ArrayList<Goods> goodsSelectAll() throws Exception;

	public ArrayList<Goods> goodsSelectPageList(GoodsPaging goodspaging) throws Exception;

	public ArrayList<Goods> goodsFindList(GoodsPaging goodspaging) throws Exception;

	public int goodsSelectCount(GoodsPaging goodspaging) throws Exception;

	public int cartInsertRow(Tb_cart tb_cart) throws Exception;

	public int cartUpdateRow(Tb_cart tb_cart) throws Exception;

	public int myGoodsCartCount(String member_id) throws Exception;

	public ArrayList<Tb_cart> myGoodsCartSelect(String member_id) throws Exception;

//	public int goodsUpdateAjax(Goods Goods) throws Exception;
//	public int goodsUpdateRow(Goods Goods) throws Exception;
//	public int goodsDeleteAjax(String g_seq) throws Exception;
//	public ArrayList<Goods> selectPageList(BoardPaging boardpaging) throws Exception;
//	public int selectCount(BoardPaging boardpaging) throws Exception;
//	public int selectMaxStep(int g_ref) throws Exception;
//	int goodsDeleteRow(int g_seq) throws Exception;
}