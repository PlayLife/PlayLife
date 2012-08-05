package com.playlife.presentation.controllers;

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

import com.playlife.logic.user.IPlayLifeUserService;
import com.playlife.presentation.converters.JSONConverter;
import com.playlife.utility.LocaleService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	IPlayLifeUserService playLifeUserService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value="/register")
	protected String registerRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "user/register";
	}
	
	@RequestMapping(value="/facebook.do")
	@ResponseBody
	protected String FacebookRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LocaleService.resolve(request, response);

		return "user/register";
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handlerException(HttpServletRequest request, Exception ex){
		JSONObject obj_return = new JSONObject();
		obj_return.put("error", JSONConverter.constructError(ex, messageSource, LocaleService.getLocale(request)));
		obj_return.put("status", "error");
		return obj_return.toString();
	}
}
