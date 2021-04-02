package com.kakao.kitkat;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.kitkat.dao.Tb_classDao;
import com.kakao.kitkat.dao.Tb_classprofessorDao;
import com.kakao.kitkat.dao.Tb_departmentDao;
import com.kakao.kitkat.dao.Tb_gradeDao;
import com.kakao.kitkat.dao.Tb_professorDao;
import com.kakao.kitkat.entities.Tb_class;
import com.kakao.kitkat.entities.Tb_class_professor;
import com.kakao.kitkat.entities.Tb_department;
import com.kakao.kitkat.entities.Tb_grade;
import com.kakao.kitkat.entities.Tb_professor;

@Controller
public class ProfessorController {
	@Autowired
	Tb_professor tb_professor;
	@Autowired
	Tb_department tb_department;
	@Autowired
	Tb_class_professor tb_classpro;
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	Tb_class tb_class;
	@Autowired
	Tb_grade tb_grade;

	// 교수 로그인페이지
	@RequestMapping(value = "/professor_login", method = RequestMethod.GET)
	public String professor_login(Locale locale, Model model) throws Exception {

		return "login/login";
	}

	// 교수로그인 실행
	@RequestMapping(value = "/professor_loginUp", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute Tb_professor professor, HttpSession session) throws Exception {
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		Tb_professor data = dao.selectOne(professor.getProfessor_no());
		if (data == null) {
			return "login/loginfail";
		} else {
			session.setAttribute("sessionProfessor_no", data.getProfessor_no());
			session.setAttribute("sessionProfessor_name", data.getProfessor_name());
			session.setAttribute("sessionProfessor_pw", data.getProfessor_pw());
			return "redirect:index";
		}
	}

	// 교수 로그아웃
	@RequestMapping(value = "/professor_logout", method = RequestMethod.GET)
	public String professor_logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	// 교수 정보수정페이지
	@RequestMapping(value = "/professorUpdate", method = RequestMethod.GET)
	public String professorUpdate(Model model, HttpSession session) throws Exception {
		String professor_no = (String) session.getAttribute("sessionMember_id");
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		Tb_professor professor = dao.selectOne(professor_no);
		model.addAttribute("professor", professor);
		return "professor/professor_detail";
	}

	// 교수 정보 삭제 페이지
	@RequestMapping(value = "/professorDelete", method = RequestMethod.GET)
	public String professorDelete(Locale locale, Model model, @RequestParam String professor_no) throws Exception {
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		Tb_professor professor = dao.selectOne(professor_no);
		model.addAttribute("professor", professor);
		return "professor/professor_delete";
	}

	// 교수 정보 삭제 진행
	@RequestMapping(value = "/professorDeleteSave", method = RequestMethod.GET)
	public String professorDeleteSave(@ModelAttribute Tb_professor professor, HttpSession session) throws Exception {
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		dao.deleteOne(professor);
		session.invalidate();
		return "index";
	}

	// 교수 정보 수정 저장
	@RequestMapping(value = "/professorUpdateSave", method = RequestMethod.GET)
	public String professorDetailSave(@ModelAttribute Tb_professor professor, Locale locale, Model model)
			throws Exception {
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		dao.updateRow(professor);
		return "index";
	}

	// 교직원 리스트 보기
	@RequestMapping(value = "/professorlist", method = RequestMethod.GET)
	public String professorlist(Model model, @ModelAttribute Tb_professor professor, HttpSession session)
			throws Exception {
		String professor_no = (String) session.getAttribute("sessionMember_id");
		if (professor_no == null) {
			return "redirect:professor_login";
		}
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		ArrayList<Tb_professor> professors = dao.selectAll();
		model.addAttribute("professors", professors);
		return "professor/professor_list";
	}

	// 교직원 페이지에 학과 검색.
	@RequestMapping(value = "/professorjoin", method = RequestMethod.GET)
	public String professorjoin(Model model) throws Exception {
		Tb_departmentDao departdao = sqlSession.getMapper(Tb_departmentDao.class);
		ArrayList<Tb_department> departments = departdao.selectAll();
		model.addAttribute("departments", departments);
		return "professor/professor_insert";
	}

	// 교직원 저장
	@RequestMapping(value = "/professorInsertRow", method = RequestMethod.POST)
	public String professorInsertRow(@ModelAttribute Tb_professor professor) throws Exception {
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		String encodepassword = getEncMD5(professor.getProfessor_ssn());
		professor.setProfessor_ssn(encodepassword);
		dao.insertRow(professor);
		System.out.println(encodepassword);
		return "index";
	}

	// 교직원 비밀번호 암호화 . md5 주민번호 실행 코드 !
	private String getEncMD5(String txt) throws Exception {

		StringBuffer sbuf = new StringBuffer();

		MessageDigest mDigest = MessageDigest.getInstance("MD5");
		mDigest.update(txt.getBytes());

		byte[] msgStr = mDigest.digest();

		for (int i = 0; i < msgStr.length; i++) {
			String tmpEncTxt = Integer.toHexString((int) msgStr[i] & 0x00ff);
			sbuf.append(tmpEncTxt);
		}

		return sbuf.toString();
	}

	// 교수번호 중복확인
	@RequestMapping(value = "/Professor_NoConfirmAjax", method = RequestMethod.POST)
	@ResponseBody
	public String Professor_NoConfirmAjax(@RequestParam String professor_no) throws Exception {
		Tb_professorDao dao = sqlSession.getMapper(Tb_professorDao.class);
		Tb_professor data = dao.selectOne(professor_no);
		String row = "y";
		if (data == null) {
			row = "n";
		} else {
			System.out.println("name:" + data.getProfessor_no());
		}
		return row;
	}

	// Tb_class_professor 리스트 조회
	@RequestMapping(value = "/classProfessorlist", method = RequestMethod.GET)
	public String classProfessorlist(Model model, @RequestParam String professor_no) throws Exception {
		Tb_classprofessorDao dao = sqlSession.getMapper(Tb_classprofessorDao.class);
		ArrayList<Tb_class_professor> classe = dao.selectOne2(professor_no);
		model.addAttribute("classe", classe);
		return "classprofessor/classprofessor_list";
	}

	// tb_class_professor 등록. 교수 수업 등록페이지
	@RequestMapping(value = "/tbclassprojoin", method = RequestMethod.GET)
	public String tbclassprojoin(Model model, HttpSession session) throws Exception {
		String professor_no = (String) session.getAttribute("sessionMember_id");
		if (professor_no == null) {
			return "redirect:professor_login";
		}
		Tb_professorDao dao2 = sqlSession.getMapper(Tb_professorDao.class);
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		tb_professor = dao2.selectOne(professor_no);
		ArrayList<Tb_class> classpro = dao.selectClass(tb_professor.getDepartment_no());
		model.addAttribute("professor", tb_professor);
		model.addAttribute("classpro", classpro);
		return "classprofessor/classprofessor_insert";
	}

	// tb_class_professor 교수 수업 저장
	@RequestMapping(value = "/tbclassInsertRow", method = RequestMethod.POST)
	public String tbclassInsertRow(@ModelAttribute Tb_class_professor classpro) throws Exception {
		Tb_classprofessorDao dao = sqlSession.getMapper(Tb_classprofessorDao.class);
		dao.insertRow(classpro);
		return "index";
	}

	// 교수 수업 수정 페이지
	@RequestMapping(value = "/tbclassupdateRow", method = RequestMethod.GET)
	public String tbclassupdateRow(HttpSession session, Model model) throws Exception {
		String professor_no = (String) session.getAttribute("sessionMember_id");
		Tb_classprofessorDao dao = sqlSession.getMapper(Tb_classprofessorDao.class);
		Tb_professorDao dao2 = sqlSession.getMapper(Tb_professorDao.class);
		tb_professor = dao2.selectOne(professor_no);
		ArrayList<Tb_class_professor> classproA = dao.selectOne2(professor_no);

		int result = 0;
		if (classproA.size() == 0) {
			result = 0;
		} else {
			result = 1;
		}

		model.addAttribute("result", result);
		model.addAttribute("professor", tb_professor);
		model.addAttribute("classpro", classproA);
		return "classprofessor/classprofessor_update";
	}

	// 교수 수업 중복 체크!
	@RequestMapping(value = "/Professor_classConfirmAjax", method = RequestMethod.POST)
	@ResponseBody
	public String Professor_classConfirmAjax(@RequestParam String class_no, @RequestParam String professor_no)
			throws Exception {
		Tb_classprofessorDao dao = sqlSession.getMapper(Tb_classprofessorDao.class);
		HashMap<String, String> classpro_no = new HashMap<String, String>();
		classpro_no.putIfAbsent("class_no", class_no);
		classpro_no.putIfAbsent("professor_no", professor_no);
		int data = dao.selectClassPro(classpro_no);
		String row = "y";
		if (data == 0) {
			row = "n";
		}
		return row;
	}

	// 수업 삭제 진행 .
	@RequestMapping(value = "/professorclassdelete", method = RequestMethod.POST)
	public String professorclassdelete(@RequestParam String class_no, HttpServletRequest request) throws Exception {
		Tb_classprofessorDao dao2 = sqlSession.getMapper(Tb_classprofessorDao.class);
		String[] strArry = request.getParameterValues("class_no");
		System.out.println(strArry);
		dao2.classnodeleteAjax(class_no);
		return "redirect:tbclassupdateRow";
	}

	// 학생 검색 페이지
	@RequestMapping(value = "/gradesearch", method = RequestMethod.GET)
	public String gradereg(Model model, HttpSession session) throws Exception {
		String professor_no = (String) session.getAttribute("sessionMember_id");
		Tb_classprofessorDao dao = sqlSession.getMapper(Tb_classprofessorDao.class);
		ArrayList<Tb_class_professor> clase = dao.selectOne2(professor_no);
		model.addAttribute("clase", clase);
		String yyyys[] = new String[27];
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		int start = year - 26;
		for (int i = 0; i < yyyys.length; i++) {
			yyyys[i] = start++ + "";
		}

		model.addAttribute("yyyys", yyyys);

		String thisyyyy = year + "";
		model.addAttribute("thisyyyy", thisyyyy);
		String mms[] = { "01", "02", "03" };
		model.addAttribute("mms", mms);
		String thismm = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		model.addAttribute("thismm", thismm);
		return "grade/grade_serach";
	}

//교수가맡고있는 성적확인.
	@RequestMapping(value = "/gradesearchlist", method = RequestMethod.POST)
	public String gradesearchlist(Model model, String yyyy, String mm, String class_no) throws Exception {
		String term_no = yyyy + mm;
		tb_grade.setClass_no(class_no);
		tb_grade.setTerm_no(term_no);
		Tb_gradeDao dao = sqlSession.getMapper(Tb_gradeDao.class);
		ArrayList<Tb_grade> ma = dao.selectTerm(tb_grade);
		model.addAttribute("ma", ma);
		return "grade/grade_list";
	}

//학생성적 변경.
	@RequestMapping(value = "/gradeUpdateAjax", method = RequestMethod.POST)
	@ResponseBody
	public String gradeUpdateAjax(@RequestParam Double point, @RequestParam String student_no) throws Exception {
		Tb_gradeDao dao = sqlSession.getMapper(Tb_gradeDao.class);
		tb_grade.setPoint(point);
		tb_grade.setStudent_no(student_no);
		int result = dao.gradeUpdateAjax(tb_grade);
		if (result > 0) {
			return "y";
		} else {
			return "n";

		}
	}

	@RequestMapping(value = "/classNoItemDeleteAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classNoItemDeleteAjax(@RequestBody List<Map<String, Object>> classNojson, HttpSession session)
			throws Exception {
		Tb_classprofessorDao dao = sqlSession.getMapper(Tb_classprofessorDao.class);
		String professor_no = (String) session.getAttribute("sessionMember_id");
		tb_classpro.setProfessor_no(professor_no);
		for (Map class_nos : classNojson) {
			String class_no = (String) class_nos.get("a");
			tb_classpro.setClass_no(class_no);
			dao.professorclassDelete(tb_classpro);
		}
		/*
		 * for (int i = 0; i < classNojson.size(); i++) { System.out.println(
		 * classNojson.); }
		 */
		return "y";
	}
}
