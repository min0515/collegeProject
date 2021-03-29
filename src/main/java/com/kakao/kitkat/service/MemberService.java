package com.kakao.kitkat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakao.kitkat.dao.MemberDao;
import com.kakao.kitkat.entities.Member;
import com.kakao.kitkat.entities.Tb_professor;
import com.kakao.kitkat.entities.Tb_student;

@Service
public class MemberService implements MemberDao {

	@Autowired
	MemberDao memberdao;

	@Override
	public int insertRow(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tb_student selectOne(String student_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tb_student> selectAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCodeRowStudent(Tb_student tb_student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Tb_professor> selectAllProfessor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCodeRowProfessor(Tb_professor tb_professor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tb_professor professorSelectOne(String professor_no) {
		// TODO Auto-generated method stub
		return null;
	}

}