package com.kakao.kitkat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("message", "thymeleaf  message");
		return "school_index";
	}

	@RequestMapping(value = "/home")
	public String home(Model model) {
		model.addAttribute("message", "thymeleaf  message");
		return "school_index";
	}
}
