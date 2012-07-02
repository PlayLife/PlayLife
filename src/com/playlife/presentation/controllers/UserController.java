package com.playlife.presentation.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.playlife.logic.UserService;
import com.playlife.persistence.domainObjects.Type_UserRole;
import com.playlife.persistence.domainObjects.User;
import com.playlife.presentation.validator.UserValidator;
import com.playlife.utility.JSONConverter;
import com.playlife.utility.LocaleService;
import com.playlife.utility.PresentationException;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	@RequestMapping(value="/*")
	protected String registerRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		LocaleService.resolve(request, response);

		return redirect(request, "user/register", false);
	}
	
	@RequestMapping(value="/login")
	protected String loginRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		LocaleService.resolve(request, response);

		return redirect(request, "user/login", false);
	}
	
	@RequestMapping(value="/create.json")
	@ResponseBody
	protected String create(User registerUser, HttpServletRequest request) {
		try {
			redirect(request, null, true);
			
			userValidator.validate(registerUser);
			
			JSONObject obj_return = new JSONObject();
			User user = userService.create(registerUser.getUsername(), registerUser.getPassword(), registerUser.getEmail(), Type_UserRole.USER);
			obj_return.put("user", user);
			obj_return.put("status", "ok");
			request.getSession(true).setAttribute("user", user);
			return obj_return.toString();
		} catch (Exception ex){
			throw new PresentationException(-9999, ex);
		}
	}
	
	@RequestMapping(value="/login.json")
	@ResponseBody
	protected String login(User loginUser, HttpServletRequest request) {
		try {
			redirect(request, null, true);
			
			User user = userValidator.checkLogin(loginUser);
			JSONObject obj_return = new JSONObject();
			obj_return.put("user", user);
			obj_return.put("status", "ok");
			request.getSession(true).setAttribute("user", user);
			return obj_return.toString();
		} catch (Exception ex){
			throw new PresentationException(-9999, ex);
		}
	}
	
	private String redirect(HttpServletRequest request, String path, boolean throwException){
		User user = (User)request.getSession().getAttribute("user");
		if (user != null){
			if (throwException)
				throw new PresentationException(-9999);
			return "home";
		} else
			return path;
	}
	
	@ExceptionHandler(PresentationException.class)
	@ResponseBody
	public String handlerException(HttpServletRequest request, PresentationException ex){
		JSONObject obj_return = new JSONObject();
		obj_return.put("error", JSONConverter.constructError(ex, messageSource, LocaleService.getLocale(request)));
		obj_return.put("status", "error");
		return obj_return.toString();
	}
}