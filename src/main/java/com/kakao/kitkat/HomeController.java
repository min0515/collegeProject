package com.kakao.kitkat;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.kitkat.dao.Tb_classDao;
import com.kakao.kitkat.entities.Tb_registration;

@Controller
public class HomeController {

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "/")
	public String index(Model model, HttpSession session) throws Exception {
		Tb_classDao dao = sqlSession.getMapper(Tb_classDao.class);
		Tb_registration tb_registration = dao.sessionRegistration();
		session.setAttribute("sessionPreregistrationyn", tb_registration.getPreregistrationyn());
		session.setAttribute("sessionRegistrationyn", tb_registration.getRegistrationyn());
		return "school_index";
	}

	@RequestMapping(value = "/home")
	public String home(Model model, HttpSession session) {
		System.out.println(session.getAttribute("sessionPreregistrationyn"));
		model.addAttribute("message", "thymeleaf  message");
		return "school_index";
	}
}
