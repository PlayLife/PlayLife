package com.playlife.presentation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playlife.persistence.domainObjects.Type_UserRole;
import com.playlife.persistence.domainObjects.User;
import com.playlife.utility.LocaleService;

@Controller
@RequestMapping(value="/template")
public class HeaderController {
	private User user;
	@ModelAttribute
	public User getUser() {
		return user;
	}
	
	@RequestMapping(value="/userHeader")
	protected String mainRequest(HttpServletRequest request, HttpServletResponse response) {
		LocaleService.resolve(request, response);
		user = (User)request.getSession().getAttribute("user");
		
		if (user == null)
			return "template/header_user_normal";
		else {
			if (user.getUserRole() == Type_UserRole.ADMIN)
				return "template/header_user_admin";
			else 
				return "template/header_user_loggedin";
		}
	}
}
