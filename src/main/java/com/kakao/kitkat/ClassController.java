package com.kakao.kitkat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakao.kitkat.dao.Tb_classDao;
import com.kakao.kitkat.entities.Tb_class;
import com.kakao.kitkat.entities.Tb_class_professor;
import com.kakao.kitkat.entities.Tb_classreview;
import com.kakao.kitkat.entities.Tb_department;
import com.kakao.kitkat.entities.Tb_grade;
import com.kakao.kitkat.entities.Tb_professor;
import com.kakao.kitkat.entities.Tb_registration;
import com.kakao.kitkat.entities.Tb_student;

@Controller
public class ClassController {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	Tb_department tb_department;
	@Autowired
	Tb_professor tb_professor;
	@Autowired
	Tb_grade tb_grade;
	@Autowired
	Tb_class tb_class;

	public String makeTermno() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String yearstr = Integer.toString(year);
		String monthstr = null;
		if (month < 6) {
			monthstr = "01";
		} else {
			monthstr = "02";
		}
		String term_no = yearstr + monthstr;
		return term_no;
	}

	/*               */
	/* 관리자용 강의 페이지 */
	/*               */
	@RequestMapping(value = "/classCancel")
	public String classCancel() {
		return "index";
	}

	@RequestMapping(value = "/classInsert")
	public String classInsert(Model model) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		ArrayList<Tb_department> departments = dao.departmentSelectAll();
		model.addAttribute("departments", departments);
		ArrayList<Tb_professor> professors = dao.professorSelectAll();
		model.addAttribute("professors", professors);
		return "class/class_insert";
	}

	@RequestMapping(value = "/classAdminListGo")
	public String classAdminListGo(Model model) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		ArrayList<Tb_class> classtbs = dao.selectAll();
		model.addAttribute("classtbs", classtbs);

		return "class/classAdminList";
	}

	@RequestMapping(value = "/classDeleteAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classDeleteAjax(@RequestParam String class_no) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		int check = 1;
		try {
			dao.professorDeleteAjax(class_no);
		} catch (Exception e) {
			return "n";
		}
		try {
			dao.deleteAjax(class_no);
			return "y";
		} catch (Exception e) {
			return "n";
		}
	}

	@RequestMapping(value = "/classInsertSave")
	public String classInsertSave(@ModelAttribute Tb_class tb_class,
			@ModelAttribute Tb_class_professor tb_class_professor) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		int check = 1;
		try {
			dao.insertRow(tb_class);
		} catch (Exception e) {
			check = 0;
		}
		if (check == 0) {
			System.out.println("ehldjfk");
			return "redirect:classInsert";
		} else {
			dao.professorInsertRow(tb_class_professor);
		}
		return "index";
	}

	@RequestMapping(value = "/classNumConfirmAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classNumConfirmAjax(@RequestParam String class_no) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		Tb_class data = dao.selectOne(class_no);
		String row = "y";
		if (data == null) {
			row = "n";
		}
		return row;
	}

	@RequestMapping(value = "/classAdminUpdateGo")
	public String classAdminUpdateGo(Model model, @RequestParam String class_no) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		Tb_class classtb = dao.selectOne(class_no);
		model.addAttribute("classtb", classtb);
		Tb_department departmentOne = dao.departmentSelectOne(classtb.getDepartment_no());
		model.addAttribute("departmentOne", departmentOne);
		Tb_class_professor classpro = dao.professorSelectOneLimit(class_no);
		try {
			Tb_professor professorOne = dao.professorSelectOne(classpro.getProfessor_no());
			model.addAttribute("professorOne", professorOne);
		} catch (Exception e) {
			tb_professor.setProfessor_name("없음");
			tb_professor.setProfessor_no("");
			Tb_professor professorOne = tb_professor;
			model.addAttribute("professorOne", professorOne);
		}

		// 학과검색
		ArrayList<Tb_department> departments = dao.departmentSelectAll();
		model.addAttribute("departments", departments);
		ArrayList<Tb_professor> professors = dao.professorSelectAll();
		model.addAttribute("professors", professors);
		return "class/class_update";
	}

	@RequestMapping(value = "/classUpdateSave")
	public String classUpdateSave(@ModelAttribute Tb_class tb_class,
			@ModelAttribute Tb_class_professor tb_class_professor) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		dao.classProfessorUpdateRow(tb_class_professor);
		dao.updateRow(tb_class);
		return "redirect:classAdminListGo";

	}
	/*                   */
	/* 관리자용 강의 페이지 end */
	/*                   */

	/*              */
	/* 학생용 강의 페이지 */
	/*              */

	@RequestMapping(value = "/classRegistrationGo")
	public String classRegistrationGo(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login/login";
		} else {
			tb_grade.setStudent_no(student_no);
			tb_grade.setTerm_no(makeTermno());
			ArrayList<Tb_class> myClassTb = dao.selectMyClassList(tb_grade);
			model.addAttribute("myClassTb", myClassTb);
			Tb_student student = dao.studentCreditSelectOne(student_no);
			model.addAttribute("student", student);

			// 학과검색
			ArrayList<Tb_department> departments = dao.departmentSelectAll();
			model.addAttribute("departments", departments);
			ArrayList<Tb_professor> professors = dao.professorSelectAll();
			model.addAttribute("professors", professors);
			return "class/class_registration_list";
		}
	}

	@RequestMapping(value = "/classFindListView")
	public String classFindListView(Model model, Tb_class tb_class) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		ArrayList<Tb_class> classTb = dao.selectFindClass(tb_class);
		model.addAttribute("classTb", classTb);
		return "class/class_registration_list :: #ajaxReplace";
	}

	@RequestMapping(value = "/classRegistrationAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classRegistrationAjax(@RequestParam String class_no, @RequestParam int credit, HttpSession session)
			throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		// tb_studnet nowcredit -#{credit}
		HashMap hash = new HashMap();
		hash.put("student_no", student_no);
		hash.put("credit", credit);
		try {
			dao.StudentNowcreditUpdateRow(hash);
		} catch (Exception e) {
			if (student_no == null) {
				return "login";
			} else {
				return "creditfull";
			}
		}
		// tb_class nowcapacity에 -1
		try {
			dao.ClassCapacityUpdateRow(class_no);
		} catch (Exception e1) {
			dao.StudentNowcreditCancelUpdateRow(hash);
			return "full";
		}
		// tb_grade에 넣을 데이터
		String term_no = makeTermno();
		tb_grade.setClass_no(class_no);
		tb_grade.setTerm_no(term_no);
		tb_grade.setStudent_no(student_no);
		try {
			dao.NewClassStudentInsert(tb_grade);
		} catch (Exception e2) {
			if (student_no == null) {
				dao.MyClassCapacityUpdateRow(class_no);
				dao.StudentNowcreditCancelUpdateRow(hash);
				return "login";
			} else {
				dao.MyClassCapacityUpdateRow(class_no);
				dao.StudentNowcreditCancelUpdateRow(hash);
				return "already";
			}
		}
		return null;
	}

	@RequestMapping(value = "/classRegistrationCancelAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classRegistrationCancelAjax(@RequestParam String class_no, @RequestParam int credit,
			HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		tb_grade.setClass_no(class_no);
		if (session.getAttribute("sessionMember_id") == null) {
			return "login";
		}
		tb_grade.setStudent_no(student_no);
		tb_grade.setTerm_no(makeTermno());
		HashMap hash = new HashMap();
		hash.put("student_no", student_no);
		hash.put("credit", credit);
		dao.StudentNowcreditCancelUpdateRow(hash);
		dao.MyClassDeleteAjax(tb_grade);
		dao.MyClassCapacityUpdateRow(class_no);
		return "y";
	}

	@RequestMapping(value = "/myClassListRefreshAjax")
	public String myClassListRefreshAjax(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		tb_grade.setStudent_no((String) session.getAttribute("sessionMember_id"));
		tb_grade.setTerm_no(makeTermno());
		ArrayList<Tb_class> myClassTb = dao.selectMyClassList(tb_grade);
		model.addAttribute("myClassTb", myClassTb);
		return "class/class_registration_list :: #ajaxReplaceMyClass";
	}

	@RequestMapping(value = "/myClassListRefreshCreditAjax")
	public String myClassListRefreshCreditAjax(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		Tb_student student = dao.studentCreditSelectOne(student_no);
		model.addAttribute("student", student);
		return "class/class_registration_list :: #creditRefresh";
	}

	/* 수강바구니 */
	@RequestMapping(value = "/ClassPreRegistrationGo")
	public String ClassPreRegistrationGo(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login/login";
		} else {
			tb_grade.setStudent_no(student_no);
			tb_grade.setTerm_no(makeTermno());
			ArrayList<Tb_class> myClassTb = dao.selectMyClassList(tb_grade);
			model.addAttribute("myClassTb", myClassTb);
			Tb_student student = dao.studentCreditSelectOne(student_no);
			model.addAttribute("student", student);

			// 학과검색
			ArrayList<Tb_department> departments = dao.departmentSelectAll();
			model.addAttribute("departments", departments);
			ArrayList<Tb_professor> professors = dao.professorSelectAll();
			model.addAttribute("professors", professors);
			return "class/class_preRegistration_list";
		}
	}

	@RequestMapping(value = "/classBasketFindListView")
	public String classBasketFindListView(Model model, Tb_class tb_class) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		ArrayList<Tb_class> classTb = dao.selectFindClass(tb_class);
		model.addAttribute("classTb", classTb);
		return "class/class_preRegistration_list :: #ajaxBasketReplace";
	}

	@RequestMapping(value = "/myClassBasketListRefreshAjax")
	public String myClassBasketListRefreshAjax(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		tb_grade.setStudent_no(student_no);
		tb_grade.setTerm_no(makeTermno());
		ArrayList<Tb_class> myClassTb = dao.selectMyClassList(tb_grade);
		model.addAttribute("myClassTb", myClassTb);
		Tb_student student = dao.studentCreditSelectOne(student_no);
		model.addAttribute("student", student);
		return "class/class_preRegistration_list :: #ajaxReplaceMyClassBasket";
	}

	@RequestMapping(value = "/myClassBasketListRefreshCreditAjax")
	public String myClassBasketListRefreshCreditAjax(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		Tb_student student = dao.studentCreditSelectOne(student_no);
		model.addAttribute("student", student);
		return "class/class_preRegistration_list :: #preCreditRefresh";
	}

	@RequestMapping(value = "/classPreRegistrationAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classPreRegistrationAjax(@RequestParam String class_no, @RequestParam int credit, HttpSession session)
			throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		// tb_studnet nowcredit -#{credit}
		HashMap hash = new HashMap();
		hash.put("student_no", student_no);
		hash.put("credit", credit);
		try {
			dao.StudentNowcreditUpdateRow(hash);
		} catch (Exception e) {
			if (student_no == null) {
				return "login";
			} else {
				return "creditfull";
			}
		}
		// tb_grade에 넣을 데이터
		String term_no = makeTermno();
		tb_grade.setClass_no(class_no);
		tb_grade.setTerm_no(term_no);
		tb_grade.setStudent_no(student_no);
		try {
			dao.NewClassStudentInsert(tb_grade);
		} catch (Exception e) {
			if (student_no == null) {
				dao.StudentNowcreditCancelUpdateRow(hash);
				return "login";
			} else {
				dao.StudentNowcreditCancelUpdateRow(hash);
				return "already";
			}
		}
		dao.MyClassCapacityUpdateRow(class_no);
		return null;
	}

	@RequestMapping(value = "/classPreRegistrationCancelAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classPreRegistrationCancelAjax(@RequestParam String class_no, @RequestParam int credit,
			HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		tb_grade.setClass_no(class_no);
		if (student_no == null) {
			return "login";
		}
		HashMap hash = new HashMap();
		hash.put("student_no", student_no);
		hash.put("credit", credit);
		dao.StudentNowcreditCancelUpdateRow(hash);
		tb_grade.setStudent_no(student_no);
		tb_grade.setTerm_no(makeTermno());
		dao.MyClassDeleteAjax(tb_grade);
		dao.ClassCapacityUpdateRow(class_no);
		return "y";
	}

	/* 수강신청 관리 */
	@RequestMapping(value = "/classRegistrationAdminPageGo")
	public String classRegistrationAdminPageGo(Model model, Tb_class tb_class) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		return "class/class_registration_admin";
	}

	@RequestMapping(value = "/preRegistrationStartAjax", method = RequestMethod.POST)
	@ResponseBody
	public String preRegistrationStartAjax(HttpSession session) throws Exception {

		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		dao.preRegistrationStartUpdate();

		dao.preRegistrationStartClassUpdate();
		dao.preRegistrationStartStudentUpdate();

		Tb_classDao dao1 = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao1.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());
		return "y";
	}

	@RequestMapping(value = "/preRegistrationEndAjax", method = RequestMethod.POST)
	@ResponseBody
	public String preRegistrationEndAjax(HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		// n으로 바꿔줌
		dao.preRegistrationEndUpdate();
		/* 정원이상 들어온 강의 */
		// tb_Student에서 정원이상인것들 credit update처리
		String term_no = makeTermno();
		ArrayList<Tb_class> creditSubList = dao.SelectPreRegistrationEndCreditList(term_no);
		for (Tb_class creditsub : creditSubList) {
			dao.preRegistrationEndCreditUpdate1(creditsub);
		}
		// tb_grade정원 이상인것들 다 지우기
		dao.preRegistrationEndGradeDelete1(term_no);
		// tb_class에서 정원이상인것들 정원 update처리
		dao.preRegistrationEndCapacityUpdate1();

		/* 정원 이하 들어온강의 */
		// tb_class에서 정원이하인것들 정원 update처리
		dao.preRegistrationEndCapacityUpdate2();

		Tb_classDao dao1 = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao1.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());
		return "y";
	}

	@RequestMapping(value = "/registrationStartAjax", method = RequestMethod.POST)
	@ResponseBody
	public String registrationStartAjax(HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		dao.registrationStartUpdate();

		Tb_classDao dao1 = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao1.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());
		return "y";
	}

	@RequestMapping(value = "/registrationEndAjax", method = RequestMethod.POST)
	@ResponseBody
	public String registrationEndAjax(HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		dao.registrationEndUpdate();
		dao.registrationEndCapacityUpdate();
		dao.registrationEndCreditUpdate();

		Tb_classDao dao1 = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao1.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());
		return "y";
	}

	/* 내 강의 검색 및 수강평 */
	@RequestMapping(value = "/myclassFinalList")
	public String myclassFinalList(Model model, HttpSession session) {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login/login";
		} else {
			Tb_student tb_student = dao.studentCreditSelectOne(student_no);
			// 학생의 입학년도
			String entranceYearStr = tb_student.getEntrance_date().substring(0, 4);
			int entranceYear = Integer.parseInt(entranceYearStr);
			// 지금년도
			Calendar cal = Calendar.getInstance();
			int thisyear = cal.get(Calendar.YEAR);
			int yyyyNum = thisyear - entranceYear + 1;
			String yyyys[] = new String[yyyyNum];

			for (int i = 0; i < yyyyNum; i++) {
				yyyys[i] = entranceYear++ + "";
			}
			model.addAttribute("yyyys", yyyys);
			return "class/myclass_check";
		}

	}

	@RequestMapping(value = "/myclassFindListViewFinalAjax")
	public String myclassFindListViewFinalAjax(Model model, @RequestParam String term_no, HttpSession session)
			throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login/login";
		} else {
			tb_grade.setTerm_no(term_no);
			tb_grade.setStudent_no(student_no);
			ArrayList<Tb_class> classTb = dao.studentClassSearchListView(tb_grade);
			model.addAttribute("classTb", classTb);
			return "class/myclass_check :: #ajaxReplaceFinalList";
		}

	}

	@RequestMapping(value = "/classReviewWrite", method = RequestMethod.GET)
	public String classReviewWrite(Model model, @RequestParam String class_no, @RequestParam String class_name)
			throws Exception {
		tb_class.setClass_no(class_no);
		tb_class.setClass_name(class_name);
		model.addAttribute("tb_class", tb_class);
		return "class/class_review";
	}

	@RequestMapping(value = "/classReviewInsert", method = RequestMethod.POST)
	public String classReviewInsert(@ModelAttribute Tb_classreview tb_classreview, HttpSession session)
			throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		if (student_no == null) {
			return "login/login";
		} else {
			tb_classreview.setTerm_no(makeTermno());
			tb_classreview.setStudent_no(student_no);
			dao.classReviewinsertRow(tb_classreview);
			return "redirect:myclassFinalList";
		}
	}

	@RequestMapping(value = "/classReviewAlreadyCheckAjax", method = RequestMethod.POST)
	@ResponseBody
	public String classReviewAlreadyCheckAjax(Tb_classreview tb_classreview, HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		String student_no = (String) session.getAttribute("sessionMember_id");
		tb_classreview.setTerm_no(makeTermno());
		tb_classreview.setStudent_no(student_no);
		int alreadyReview = dao.classReviewCountSelect(tb_classreview);
		if (student_no == null) {
			return "login";
		} else if (alreadyReview == 0) {
			return "ok";
		} else {
			return "already";
		}

	}

	@RequestMapping(value = "/classSearchList", method = RequestMethod.GET)
	public String classSearchList(Model model) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		// 학과검색
		ArrayList<Tb_department> departments = dao.departmentSelectAll();
		model.addAttribute("departments", departments);
		ArrayList<Tb_professor> professors = dao.professorSelectAll();
		model.addAttribute("professors", professors);
		return "class/class_search_list";
	}

	@RequestMapping(value = "/classSearchListView")
	public String classSearchListView(Model model, Tb_class tb_class) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		ArrayList<Tb_class> classTb = dao.selectFindClass(tb_class);
		model.addAttribute("classTb", classTb);
		return "class/class_search_list :: #ajaxSearchReplace";
	}

	@RequestMapping(value = "/classReviewSearchAjax")
	public String classReviewSearchAjax(Model model, @RequestParam String class_no) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		ArrayList<Tb_classreview> classReviews = dao.classReviewListSelect(class_no);
		model.addAttribute("classReviews", classReviews);
		return "class/class_search_list :: #ajaxReviewReplace";
	}

}
