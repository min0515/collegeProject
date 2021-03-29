package com.kakao.kitkat.dao;

import com.kakao.kitkat.entities.QnaBoard;

public interface QnaBoardDao {
	public QnaBoard qnaSelectOne(int qna_seq) throws Exception;
}