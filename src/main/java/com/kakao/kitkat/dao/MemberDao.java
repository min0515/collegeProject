package com.kakao.kitkat.dao;

import java.util.ArrayList;

import com.kakao.kitkat.entities.Member;
import com.kakao.kitkat.entities.Tb_professor;
import com.kakao.kitkat.entities.Tb_student;

public interface MemberDao {
	public int insertRow(Member member);

	public Tb_student selectOne(String student_no);

	public ArrayList<Tb_student> selectAllStudent();

	public int updateCodeRowStudent(Tb_student tb_student);

	public ArrayList<Tb_professor> selectAllProfessor();

	public int updateCodeRowProfessor(Tb_professor tb_professor);

	public Tb_professor professorSelectOne(String professor_no);

}