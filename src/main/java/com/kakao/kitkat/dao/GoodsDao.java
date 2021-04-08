package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.CartList;
import com.kakao.kitkat.entities.Goods;
import com.kakao.kitkat.entities.GoodsPaging;
import com.kakao.kitkat.entities.Goods_info;
import com.kakao.kitkat.entities.Goods_qna;
import com.kakao.kitkat.entities.Goods_review;
import com.kakao.kitkat.entities.Tb_cart;

public interface GoodsDao {

	public ArrayList<Goods_review> goodsReviewSelectAll(int g_seq) throws Exception;

	public int goodsReviewInsertRow(Goods_review goods_review) throws Exception;

	public int cartOrderDelete(String member_id) throws Exception;

	public ArrayList<CartList> myOrdersSelectAll(String member_id) throws Exception;

	public Goods_info goodsInfoOneSelectOne(int g_seq) throws Exception;

	public ArrayList<Goods_qna> goodsQnaSelectAll(int g_seq) throws Exception;

	public ArrayList<Goods_qna> goodsQnaAdminSelectAll() throws Exception;

	public int goodsQnaInsertRow(Goods_qna goods_qna) throws Exception;

	public int goodsAnswerInsertRow(Goods_qna goods_qna) throws Exception;

	public int AnswerUpdateRow(Goods_qna goods_qna) throws Exception;

	public Goods goodsSelectOne(int g_seq) throws Exception;

	public Goods goodsAllSelectOne(int g_seq) throws Exception;

	public ArrayList<Goods_info> goodsInfoAllSelectOne(int g_seq) throws Exception;

	public ArrayList<Goods_info> goodsInfoAllSelect() throws Exception;

	public int goodsSelectG_seqOne() throws Exception;

	public int goodsInsertRow(Goods Goods) throws Exception;

	public int goodsSelectCountFirst(GoodsPaging goodspaging) throws Exception;

	public ArrayList<Goods> goodsSelectAll() throws Exception;

	public ArrayList<Goods> goodsSelectPageList(GoodsPaging goodspaging) throws Exception;

	public ArrayList<Goods> goodsFindList(GoodsPaging goodspaging) throws Exception;

	public int goodsSelectCount(GoodsPaging goodspaging) throws Exception;

	public int cartInsertRow(Tb_cart tb_cart) throws Exception;

	public int cartUpdateRow(Tb_cart tb_cart) throws Exception;

	public int myGoodsCartCount(String member_id) throws Exception;

	public ArrayList<Goods_info> goodsInfoCartAllSelect(String member_id) throws Exception;

	public ArrayList<Tb_cart> cartyb(String member_id) throws Exception;

	public ArrayList<Tb_cart> myGoodsCartSelect(String member_id) throws Exception;

	public int selectCountFirst() throws Exception;

	public int cartCheckUpdateRow(Tb_cart tb_cart) throws Exception;

	public Tb_cart myGoodsCartCheckedSelect(String member_id) throws Exception;

	public ArrayList<Tb_cart> myGoodsCartCheckedPaymentSelect(String member_id) throws Exception;

	public int ProductDeleteAjax(Tb_cart tb_cart) throws Exception;

	public int cartQtyUpdateRow(Tb_cart tb_cart) throws Exception;

	public int goodsInfoInsertRow(Goods_info goods_info) throws Exception;

}