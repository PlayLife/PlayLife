package com.playlife.presentationLayer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playlife.logicLayer.user.IUserService;
import com.playlife.persistenceLayer.domainObject.User;
import com.playlife.persistenceLayer.domainObject.User_Role;
import com.playlife.utility.LocaleService;

@Controller
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	@Qualifier("userService")
	IUserService userService;
	
	private User user;
	@ModelAttribute
	public User getUser() {
		return user;
	}
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/userHeader")
	protected String mainRequest(HttpServletRequest request, HttpServletResponse response) {
		LocaleService.resolve(request, response);
		user = (User)request.getSession().getAttribute("user");
		
		if (user == null)
			return "template/header_user_normal";
		else {
			if (user.isDisabled())
				return "template/header_user_bammed";
			else if (user.getRole() == User_Role.ADMIN)
				return "template/header_user_admin";
			else 
				return "template/header_user_loggedin";
		}
	}
}
