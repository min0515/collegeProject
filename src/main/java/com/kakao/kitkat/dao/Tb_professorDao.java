package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Tb_professor;

public interface Tb_professorDao {
	public int insertRow(Tb_professor professor);

	public Tb_professor selectOne(String professor_no);

	public ArrayList<Tb_professor> selectAll() throws Exception;

	public int updateRow(Tb_professor professor) throws Exception;

	public int deleteAjax(String Professor_No) throws Exception;

	public void deleteOne(Tb_professor professor);
}