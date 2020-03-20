package com.whyxs.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value = "/error/{code}")
	public String index(@PathVariable String code, Model model) {
		return "error/" + code;
	}
}
