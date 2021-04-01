package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Comment;

public interface CommentDao {

	public Comment selectCommentOne(int b_seq) throws Exception;

	public int insertCommentRow(Comment comment) throws Exception;
	
	public ArrayList<Comment> selectCommentList(int b_seq) throws Exception;


}