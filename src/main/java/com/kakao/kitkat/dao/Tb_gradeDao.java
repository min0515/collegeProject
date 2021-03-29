package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Tb_grade;

public interface Tb_gradeDao {
	public int insertRow(Tb_grade clase);

	public Tb_grade selectOne(String department_no);

	public Tb_grade selecttwo(String class_no);

	public ArrayList<Tb_grade> selectClass(String class_no);

	public ArrayList<Tb_grade> selectTerm(Tb_grade grade);

	public ArrayList<Tb_grade> selectAll() throws Exception;

	public int deleteAjax(String cass) throws Exception;

	public int gradeUpdateAjax(Tb_grade grade) throws Exception;

}