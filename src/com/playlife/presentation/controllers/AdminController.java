package com.playlife.presentation.controllers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value="/admin")
public class AdminController {
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;
	
	@RequestMapping(value="/")
	protected String registerRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		LocaleService.resolve(request, response);
		return redirect(request, "admin/dashboard", false);
	}
	
	@RequestMapping(value="/{action}")
	protected String mainRequest(@PathVariable String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LocaleService.resolve(request, response);
		
		if (action.equalsIgnoreCase("nav"))
			return redirect(request, "admin/nav", false);
		else
			return redirect(request, "admin/dashboard", false);
	}
	
	@RequestMapping(value="/user/{action}")
	protected String user_mainRequest(@PathVariable String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LocaleService.resolve(request, response);
		
		if (action.equalsIgnoreCase("create"))
			return redirect(request, "admin/user/create", false);
		else 
			return redirect(request, "admin/user/userList", false);
	}
	
	@RequestMapping(value="/user/userList.ajax")
	@ResponseBody
	protected String user_listRequest(String[] search, int start, int end, String[] orderList, String[] orderByList, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LocaleService.resolve(request, response);
		//redirect(request, "", true);
		
		JSONObject obj_return = new JSONObject();
		obj_return.put("users", userService.getAll(Arrays.asList(search), start, end, Arrays.asList(orderList), Arrays.asList(orderByList)));
		obj_return.put("status", "ok");
		
		return obj_return.toString();
	}
	
	@RequestMapping(value="/error/{action}")
	protected String error_mainRequest(@PathVariable String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LocaleService.resolve(request, response);
		
		return redirect(request, "admin/error/report", false);
	}
	
	private String redirect(HttpServletRequest request, String path, boolean throwException){
		User user = (User)request.getSession().getAttribute("user");
		if (user == null || user.getUserRole() != Type_UserRole.ADMIN){
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
