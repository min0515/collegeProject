package com.kakao.kitkat.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.kakao.kitkat.entities.Tb_class_professor;

public interface Tb_classprofessorDao {
	public int insertRow(Tb_class_professor classpro);

	public ArrayList<Tb_class_professor> selectOne(String professor_no);   //Tb_classprofessor을 Tb_class_professor로 수정하였습니다. 03월24일

	public ArrayList<Tb_class_professor> selectTwo(String class_no);

	public int selectClassPro(HashMap<String, String> classpro_no);

	public ArrayList<Tb_class_professor> selectOne2(String professor_no);

	public ArrayList<Tb_class_professor> selectAll() throws Exception;

	public int professordeleteAjax(String Professor_No) throws Exception;

	public int classnodeleteAjax(String class_no) throws Exception;

	public void professorclassDelete(Tb_class_professor tb_classprofessor);
}