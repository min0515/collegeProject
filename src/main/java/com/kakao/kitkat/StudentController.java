package com.kakao.kitkat;

import java.util.ArrayList;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.kitkat.dao.Tb_studentDao;
import com.kakao.kitkat.entities.Board;
import com.kakao.kitkat.entities.BoardPaging;
import com.kakao.kitkat.entities.Tb_student;


@Controller
public class StudentController {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	Board board;
	@Autowired
	BoardPaging boardpaging;

	public static String find;
	
	//학생회원관리
	@RequestMapping(value = "/studentList", method = RequestMethod.GET)
	public String studentList(Locale locale, Model model) throws Exception {
		Tb_studentDao dao = sqlSession.getMapper(Tb_studentDao.class);
		ArrayList<Tb_student> students = dao.selectAll();
		model.addAttribute("students", students);
		return "student/student_list";
	}
	
	//쇼핑몰 사용자목록
	@RequestMapping(value = "/studentList1", method = RequestMethod.GET)
	public String studentList1(Locale locale, Model model) throws Exception {
		Tb_studentDao dao = sqlSession.getMapper(Tb_studentDao.class);
		ArrayList<Tb_student> students = dao.selectAll();
		model.addAttribute("students", students);
		return "goods/member_list";
	}
	
	//학생회원삭제
	@RequestMapping(value = "/studentDeleteAjax", method = RequestMethod.POST)
	@ResponseBody
	public String studentDeleteAjax(@RequestParam String student_no) throws Exception {
		Tb_studentDao dao = sqlSession.getMapper(Tb_studentDao.class);
		int result = dao.studentDeleteAjax(student_no);
		if (result > 0) {
			return "y";
		} else {
			return "n.";
		}
	}


}
