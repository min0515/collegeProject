package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Tb_student;


public interface Tb_studentDao {
	public int insertRow(Tb_student student);
	
	public Tb_student selectOne(String student_no);
	
	public ArrayList<Tb_student> selectAll() throws Exception;
	
	public int studentDeleteAjax(String student_no) throws Exception;

	public int StudentUpdateAjax(Tb_student student) throws Exception;
	
}