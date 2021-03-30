package com.kakao.kitkat.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.kakao.kitkat.entities.Tb_class;
import com.kakao.kitkat.entities.Tb_class_professor;
import com.kakao.kitkat.entities.Tb_classreview;
import com.kakao.kitkat.entities.Tb_department;
import com.kakao.kitkat.entities.Tb_grade;
import com.kakao.kitkat.entities.Tb_professor;
import com.kakao.kitkat.entities.Tb_registration;
import com.kakao.kitkat.entities.Tb_student;

public interface Tb_classDao {
	public int insertRow(Tb_class tb_class);

	public Tb_class selectOne(String class_no);

	public ArrayList<Tb_department> departmentSelectAll();

	public Tb_department departmentSelectOne(String department_no);

	public ArrayList<Tb_class> selectClass(String department_no);

	public int updateRow(Tb_class tb_class) throws Exception;

	public int classProfessorUpdateRow(Tb_class_professor tb_class_professor) throws Exception;

	public ArrayList<Tb_class> selectAll();

	public int deleteAjax(String class_no) throws Exception;

	public ArrayList<Tb_professor> professorSelectAll();

	public int professorInsertRow(Tb_class_professor tb_class_professor);

	public int professorDeleteAjax(String class_no) throws Exception;

	public Tb_class_professor professorSelectOneLimit(String class_no);

	public Tb_professor professorSelectOne(String professor_no);

	/* 학생용 dao */

	public ArrayList<Tb_class> selectFindClass(Tb_class tb_class);

	public int selectFindClassEmpty(Tb_class tb_class);

	public int ClassCapacityUpdateRow(String class_no) throws Exception;

	public int NewClassStudentInsert(Tb_grade tb_grade);

	public ArrayList<Tb_class> selectMyClassList(Tb_grade tb_grade);

	public int MyClassDeleteAjax(Tb_grade tb_grade) throws Exception;

	public int MyClassCapacityUpdateRow(String class_no) throws Exception;

	public void StudentNowcreditUpdateRow(HashMap hash) throws Exception;

	public void StudentNowcreditCancelUpdateRow(HashMap hash) throws Exception;

	public Tb_student studentCreditSelectOne(String student_no);

	/* 수강신청 전체관리 */

	public int preRegistrationStartUpdate() throws Exception;

	public int preRegistrationStartClassUpdate() throws Exception;

	public int preRegistrationEndUpdate() throws Exception;

	public int preRegistrationStartStudentUpdate() throws Exception;

	public int preRegistrationEndGradeDelete1(String term_no) throws Exception;

	public ArrayList<Tb_class> SelectPreRegistrationEndCreditList(String term_no);

	public int preRegistrationEndCreditUpdate1(Tb_class tb_class) throws Exception;

	public int preRegistrationEndCapacityUpdate1() throws Exception;

	public int preRegistrationEndCapacityUpdate2() throws Exception;

	public int registrationStartUpdate() throws Exception;

	public int registrationEndUpdate() throws Exception;

	public int registrationEndCapacityUpdate() throws Exception;

	/* 수강내역 검색 */
	public ArrayList<Tb_class> studentClassSearchListView(Tb_grade tb_grade);

	public int registrationEndCreditUpdate() throws Exception;

	public int classReviewinsertRow(Tb_classreview tb_classreview);

	public int classReviewCountSelect(Tb_classreview tb_classreview);

	public ArrayList<Tb_classreview> classReviewListSelect(String class_no);

	public Tb_registration sessionRegistration() throws Exception;

}