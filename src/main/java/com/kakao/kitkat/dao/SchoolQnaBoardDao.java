package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Board;
import com.kakao.kitkat.entities.BoardPaging;
import com.kakao.kitkat.entities.SchoolQnaBoard;

public interface SchoolQnaBoardDao {
	public SchoolQnaBoard SchoolQnaBoardSelectOne(int school_qna_board_seq) throws Exception;

	public int schoolQnaBoardInsertRow(SchoolQnaBoard SchoolQnaBoard) throws Exception;

	public int schoolQnaBoardUpdateRow(SchoolQnaBoard SchoolQnaBoard) throws Exception;

	public ArrayList<SchoolQnaBoard> schoolQnaBoardSelectAll() throws Exception;
	
	public ArrayList<SchoolQnaBoard> findListBoard(BoardPaging boardpaging) throws Exception;
	
	public ArrayList<SchoolQnaBoard> schoolQnaBoardSelectPageList(BoardPaging boardpaging) throws Exception;

//	public int schoolQnaBoardUpdateAjax(Board Board) throws Exception;
//
//	public int schoolQnaBoardDeleteAjax(String b_seq) throws Exception;
//
//	public int schoolQnaBoardSelectCountFirst(BoardPaging boardpaging) throws Exception;
//
//	public int schoolQnaBoardSelectCount(BoardPaging boardpaging) throws Exception;

	

	public void addHit(int b_seq) throws Exception;

	public int insertReplyRow(Board Board) throws Exception;

	public int selectMaxStep(int b_ref) throws Exception;

	int deleteRow(int b_seq) throws Exception;
}