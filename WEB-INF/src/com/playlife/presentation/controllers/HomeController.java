package com.playlife.presentation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playlife.utility.LocaleService;

@Controller
@RequestMapping("/*")
public class HomeController {
//	private static final String guestFunctionList = "<a href='login'>Login</a><a href='beforeRegister'>Register</a>";
//	private static final String loginFunctionList = "<a href='book_create'>Write</a><a href='logout'>Logout</a>";
//	
//	@Autowired
//	@Qualifier("handleAccess")
//	IHandleAccess handleAccess;
//	
	@RequestMapping(value="/*")
	protected String registerRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "home";
	}
}
