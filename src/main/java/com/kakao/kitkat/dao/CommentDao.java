package com.kakao.kitkat.dao;

import com.kakao.kitkat.entities.Comment;


public interface CommentDao {
	
	public Comment selectCommentOne(int b_seq) throws Exception;
	
	public int insertCommentRow(Comment comment) throws Exception;

}