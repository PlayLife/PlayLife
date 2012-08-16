package com.playlife.presentation.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.playlife.logic.user.PlayLifeUserService;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.User_Role;
import com.playlife.utility.LocaleService;

@Controller
@RequestMapping("/template")
public class TemplateController {
	@Autowired
	@Qualifier("playLifeUserService")
	PlayLifeUserService playLifeUserService;
	
	private PlayLifeUser user;
	@ModelAttribute
	public PlayLifeUser getUser() {
		return user;
	}
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/userHeader")
	protected String mainRequest(HttpServletRequest request, HttpServletResponse response) {
		LocaleService.resolve(request, response);
		user = (PlayLifeUser)request.getSession().getAttribute("user");
		
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
	@RequestMapping(value="/locale.js")
	protected void localeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("/js/locale/" + LocaleService.getLocale(request).toString()+".js");
	}
}
