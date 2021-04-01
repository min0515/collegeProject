package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Board;
import com.kakao.kitkat.entities.BoardPaging;
import com.kakao.kitkat.entities.Comment;


public interface BoardDao {
	public Board selectOne(int b_seq) throws Exception;
	
	public Comment selectCommentOne(int b_seq) throws Exception;

	public int insertRow(Board Board) throws Exception;
	
	public int insertCommentRow(Board Board) throws Exception;

	public int updateRow(Board Board) throws Exception;

	public ArrayList<Board> selectAll() throws Exception;
	
	public ArrayList<Board> findListBoard(BoardPaging boardpaging) throws Exception;
	
	public ArrayList<Board> selectPageList(BoardPaging boardpaging) throws Exception;

	public int updateAjax(Board Board) throws Exception;

	public int deleteAjax(String b_seq) throws Exception;

	public int selectCountFirst(BoardPaging boardpaging) throws Exception;

	public int selectCount(BoardPaging boardpaging) throws Exception;

	

	public void addHit(int b_seq) throws Exception;

	public int insertReplyRow(Board Board) throws Exception;

	public int selectMaxStep(int b_ref) throws Exception;

	int deleteRow(int b_seq) throws Exception;
}