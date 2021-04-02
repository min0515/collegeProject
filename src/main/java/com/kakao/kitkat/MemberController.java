package com.kakao.kitkat;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakao.kitkat.dao.MemberDao;
import com.kakao.kitkat.dao.Tb_classDao;
import com.kakao.kitkat.dao.Tb_studentDao;
import com.kakao.kitkat.entities.Member;
import com.kakao.kitkat.entities.Tb_professor;
import com.kakao.kitkat.entities.Tb_registration;
import com.kakao.kitkat.entities.Tb_student;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	Member member;

	@Autowired
	Tb_student tb_student;

	@Autowired
	Tb_professor tb_professor;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/member_insert";
	}

	@RequestMapping(value = "/memberinsertsave", method = RequestMethod.POST)
	public String memberinsert(Model model, @ModelAttribute Member member) {
		// getMapper는 인터페이스를 받아옴
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		// 사진
		if (member.getPhoto() == null) {
			member.setPhoto("/images/noimage.png");
		}
		// 비밀번호 암호화
		String encodepassword = hashPassword(member.getPassword());
		member.setPassword(encodepassword);

		dao.insertRow(member);
		return "member/member_insert";
	}

	@RequestMapping(value = "/studentLoginUp", method = RequestMethod.POST)
	public String loginUp(Model model, @ModelAttribute Tb_student tb_student, HttpSession session) throws Exception {
		Tb_classDao dao1 = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao1.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_student data = dao.selectOne(tb_student.getStudent_no());

		if (data == null) {
			return "login/login";
		} else {
			boolean passchk = BCrypt.checkpw(tb_student.getStudent_pw(), data.getStudent_pw());
			if (passchk) {
				session.setAttribute("sessionMember_id", data.getStudent_no());
				session.setAttribute("sessionMember_name", data.getStudent_name());
				session.setAttribute("sessionDepartment_no", data.getDepartment_no());
				session.setAttribute("sessionAbsence_yn", data.getAbsence_yn());
				session.setAttribute("sessionlevel", data.getMemlevel());
				return "index";
			} else {
				return "login/login";
			}

		}

	}

	@RequestMapping(value = "/professorLoginUp", method = RequestMethod.POST)
	public String loginUp(@ModelAttribute Tb_professor tb_professor, HttpSession session) throws Exception {
		Tb_classDao dao1 = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao1.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());

		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_professor data = dao.professorSelectOne(tb_professor.getProfessor_no());

		if (data == null) {
			return "login/login";
		} else {
			boolean passchk = BCrypt.checkpw(tb_professor.getProfessor_pw(), data.getProfessor_pw());
			if (passchk) {
				session.setAttribute("sessionMember_id", data.getProfessor_no());
				session.setAttribute("sessionMember_name", data.getProfessor_name());
				session.setAttribute("sessionDepartment_no", data.getDepartment_no());
				session.setAttribute("sessionlevel", data.getMemlevel());
				return "index";
			} else {
				return "login/login";
			}

		}

	}

	@RequestMapping(value = "/studentLoginUp2", method = RequestMethod.POST)
	public String studentLoginUp2(Model model, @ModelAttribute Tb_student tb_student, HttpSession session) {
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_student data = dao.selectOne(tb_student.getStudent_no());
		if (data == null) {
			return "login/login2";
		} else {
			boolean passchk = BCrypt.checkpw(tb_student.getStudent_pw(), data.getStudent_pw());
			if (passchk) {
				session.setAttribute("sessionMember_id", data.getStudent_no());
				session.setAttribute("sessionMember_name", data.getStudent_name());
				session.setAttribute("sessionDepartment_no", data.getDepartment_no());
				session.setAttribute("sessionAbsence_yn", data.getAbsence_yn());
				session.setAttribute("sessionlevel", data.getMemlevel());
				return "index2";
			} else {
				return "login/login2";
			}

		}

	}

	@RequestMapping(value = "/professorLoginUp2", method = RequestMethod.POST)
	public String professorLoginUp2(@ModelAttribute Tb_professor tb_professor, HttpSession session) {
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_professor data = dao.professorSelectOne(tb_professor.getProfessor_no());
		if (data == null) {
			return "login/login2";
		} else {
			boolean passchk = BCrypt.checkpw(tb_professor.getProfessor_pw(), data.getProfessor_pw());
			if (passchk) {
				session.setAttribute("sessionMember_id", data.getProfessor_no());
				session.setAttribute("sessionMember_name", data.getProfessor_name());
				session.setAttribute("sessionDepartment_no", data.getDepartment_no());
				session.setAttribute("sessionlevel", data.getMemlevel());
				return "index2";
			} else {
				return "login/login2";
			}

		}

	}

	@RequestMapping(value = "/studentLoginUp3", method = RequestMethod.POST)
	public String studentLoginUp3(Model model, @ModelAttribute Tb_student tb_student, HttpSession session) {
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_student data = dao.selectOne(tb_student.getStudent_no());
		if (data == null) {
			return "login/login3";
		} else {
			boolean passchk = BCrypt.checkpw(tb_student.getStudent_pw(), data.getStudent_pw());
			if (passchk) {
				session.setAttribute("sessionMember_id", data.getStudent_no());
				session.setAttribute("sessionMember_name", data.getStudent_name());
				session.setAttribute("sessionDepartment_no", data.getDepartment_no());
				session.setAttribute("sessionAbsence_yn", data.getAbsence_yn());
				session.setAttribute("sessionlevel", data.getMemlevel());
				return "school_index";
			} else {
				return "login/login3";
			}

		}

	}

	@RequestMapping(value = "/professorLoginUp3", method = RequestMethod.POST)
	public String professorLoginUp3(@ModelAttribute Tb_professor tb_professor, HttpSession session) {
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_professor data = dao.professorSelectOne(tb_professor.getProfessor_no());
		if (data == null) {
			return "login/login3";
		} else {
			boolean passchk = BCrypt.checkpw(tb_professor.getProfessor_pw(), data.getProfessor_pw());
			if (passchk) {
				session.setAttribute("sessionMember_id", data.getProfessor_no());
				session.setAttribute("sessionMember_name", data.getProfessor_name());
				session.setAttribute("sessionDepartment_no", data.getDepartment_no());
				session.setAttribute("sessionlevel", data.getMemlevel());
				return "school_index";
			} else {
				return "login/login3";
			}

		}

	}

	@RequestMapping(value = "/shoppingLogin", method = RequestMethod.GET)
	public String shoppingLogin(HttpSession session) {
		return "login/login2";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		return "login/login";
	}

	@RequestMapping(value = "/schoolLogin", method = RequestMethod.GET)
	public String login3(HttpSession session) {
		return "login/login3";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Locale locale) {
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = "/logout3", method = RequestMethod.GET)
	public String logout3(HttpSession session, Locale locale) {
		session.invalidate();
		return "school_index";
	}

	@RequestMapping(value = "/memberUpdate", method = RequestMethod.GET)
	public String memberUpdate(Locale locale, Model model, HttpSession session) throws Exception {

		String stuno = (String) session.getAttribute("sessionMember_id");

		Tb_studentDao dao = sqlSession.getMapper(Tb_studentDao.class);
		Tb_student student = dao.selectOne(stuno);
		model.addAttribute("student", student);
		return "goods/shopping_update";
	}

	@RequestMapping(value = "/memberUpdate1", method = RequestMethod.GET)
	public String memberUpdate1(Locale locale, Model model, HttpSession session) throws Exception {

		String stuno = (String) session.getAttribute("sessionMember_id");

		Tb_studentDao dao = sqlSession.getMapper(Tb_studentDao.class);
		Tb_student student = dao.selectOne(stuno);
		model.addAttribute("student", student);
		return "school/school_update";
	}

	private String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

//비밀번호암호화
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login() {
//		MemberDao dao = sqlSession.getMapper(MemberDao.class);
//		ArrayList<Tb_student> pwList = dao.selectAllStudent();
//		for (Tb_student pwStudent : pwList) {
//			String encodepassword = hashPassword(pwStudent.getStudent_pw());
//			tb_student.setStudent_pw(encodepassword);
//			tb_student.setStudent_no(pwStudent.getStudent_no());
//			dao.updateCodeRowStudent(tb_student);
//		}
//
//		return "login/login";
//	}

//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login() {
//		MemberDao dao = sqlSession.getMapper(MemberDao.class);
//		ArrayList<Tb_professor> pwList = dao.selectAllProfessor();
//		for (Tb_professor pwProfessor : pwList) {
//			String encodepassword = hashPassword(pwProfessor.getProfessor_pw());
//			tb_professor.setProfessor_no(pwProfessor.getProfessor_no());
//			tb_professor.setProfessor_pw(encodepassword);
//			dao.updateCodeRowProfessor(tb_professor);
//		}
//		return "login/login";
//	}

}
