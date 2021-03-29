package com.kakao.kitkat;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakao.kitkat.entities.Goods;
import com.kakao.kitkat.entities.GoodsPaging;

@Controller
public class SchoolController {

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	Goods goods;
	@Autowired
	GoodsPaging goodspaging;
	public static String find;

	@RequestMapping(value = "/schoolMain", method = RequestMethod.GET)
	public String schoolMain(Model model) throws Exception {
		return "school_index";
	}

	@RequestMapping(value = "/loginSelection", method = RequestMethod.GET)
	public String loginSelection(Model model) throws Exception {
		return "login/login_selection";
	}

	@RequestMapping(value = "/schoolIntro", method = RequestMethod.GET)
	public String schoolIntro(Model model) throws Exception {
		return "school/school_intro";
	}
}
