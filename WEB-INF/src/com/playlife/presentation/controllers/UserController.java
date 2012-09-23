package com.playlife.presentation.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.face4j.facebook.Client;
import com.face4j.facebook.Facebook;
import com.face4j.facebook.OAuthAccessToken;
import com.face4j.facebook.entity.User;
import com.face4j.facebook.enums.Display;
import com.face4j.facebook.enums.Permission;
import com.face4j.facebook.factory.FacebookFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playlife.logic.user.PlayLifeUserService;
import com.playlife.persistence.domainObject.PlayLifeUser;
import com.playlife.persistence.domainObject.User_Role;
import com.playlife.persistence.domainObject.User_Type;
import com.playlife.presentation.converters.JSONConverter;
import com.playlife.utility.LocaleService;
import com.playlife.utility.exceptions.PresentationException;
import com.playlife.utility.validators.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	ObjectMapper mapper = JSONConverter.mapper;
	
	@Autowired
	PlayLifeUserService playLifeUserService;
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	MessageSource messageSource;
	
	Client client = new Client("288454414516535", "1692468f44286665c477a2c73c59c52c");
	FacebookFactory facebookFactory;
	
	@RequestMapping(value="/register")
	protected String registerRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "user/register";
	}
	
	@RequestMapping(value="/login")
	protected String loginRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "user/login";
	}
	
	@RequestMapping(value="/forgot")
	protected String forgotRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "user/forgot";
	}
	
	@RequestMapping(value="/accountSetting")
	protected String accountSettingRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);

		return "user/accountSetting";
	}
	
	@RequestMapping(value="/accountSetting.json")
	@ResponseBody
	protected String accountSettingModifyRequest(PlayLifeUser new_user, String old_password, String new_password, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		LocaleService.resolve(request, response);
		
		userValidator.validate(new_user);
		
		PlayLifeUser user = (PlayLifeUser)request.getSession().getAttribute("user");
		
		if (new_password != null && !new_password.isEmpty())
			playLifeUserService.modifyPassword(user.getUserId(), old_password, new_password);
		playLifeUserService.modifyUsername(user.getUserId(), new_user.getUsername());
		user.setUsername(new_user.getUsername());
		
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("status", "ok");
		return mapper.writeValueAsString(map_return);
	}
	
	@RequestMapping(value="/sendForgotCode.json")
	@ResponseBody
	protected String sendForgotCodeRequest(String email, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);
		playLifeUserService.sendForgotPasswordEmail(email);
		
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("status", "ok");
		return mapper.writeValueAsString(map_return);
	}
	
	@RequestMapping(value="/changePassword.json")
	@ResponseBody
	protected String changePasswordRequest(String email, String forgotCode, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		LocaleService.resolve(request, response);
		request.getSession(true).setAttribute("user", playLifeUserService.changePassword(email, forgotCode, password));
		
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("status", "ok");
		return mapper.writeValueAsString(map_return);
	}
	
	@RequestMapping(value="/facebook/login")
	@ResponseBody
	protected String FacebookLoginRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LocaleService.resolve(request, response);
		
		facebookFactory = new FacebookFactory(client);
		
		response.sendRedirect(facebookFactory.getRedirectURL("http://playlife.no-ip.org:8080/user/facebook/create", Display.POPUP, Permission.EMAIL, Permission.OFFLINE_ACCESS));
		return "";
	}
	
	@RequestMapping(value="/facebook/create")
	@ResponseBody
	protected String FacebookRequest(HttpServletRequest request, HttpServletResponse response, String code) throws Exception {
		LocaleService.resolve(request, response);
		
		OAuthAccessToken accessToken = facebookFactory.getOAuthAccessToken(code, "http://playlife.no-ip.org:8080/user/facebook/create");
		Facebook facebook = facebookFactory.getInstance(accessToken);
		User fbUser = facebook.getCurrentUser();
		
		PlayLifeUser user = playLifeUserService.getUserByEmailAllowNull(fbUser.getEmail());
		if (user == null)		
			user = playLifeUserService.register(fbUser.getEmail(), "", fbUser.getUsername(), User_Type.FACEBOOK, User_Role.USER);
		
		request.getSession(true).setAttribute("user", user);
		response.sendRedirect("/home");
		return "";
	}
	
	@RequestMapping(value="/logout")
	@ResponseBody
	protected String LogoutRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		LocaleService.resolve(request, response);
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.setAttribute("user", null);
		}
		response.sendRedirect("/home");
		return null;
	}
	
	@RequestMapping(value="/create.json")
	@ResponseBody
	protected String create(PlayLifeUser registerUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LocaleService.resolve(request, response);
		
		userValidator.validate(registerUser);
		
		Map<String, Object> map_return = new HashMap<String, Object>();
		PlayLifeUser user = playLifeUserService.register(registerUser.getEmail(), registerUser.getPassword(), registerUser.getUsername(), User_Type.NORMAL, User_Role.USER);
		map_return.put("user", user);
		map_return.put("status", "ok");
		request.getSession(true).setAttribute("user", user);
		return mapper.writeValueAsString(map_return);
	}
	
	@RequestMapping(value="/login.json")
	@ResponseBody
	protected String login(PlayLifeUser loginUser, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		PlayLifeUser user = playLifeUserService.login(loginUser);
		if (user == null || user.type != User_Type.NORMAL)
			throw new PresentationException(-9999);
		
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("user", user);
		map_return.put("status", "ok");
		request.getSession(true).setAttribute("user", user);
		return mapper.writeValueAsString(map_return);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handlerException(HttpServletRequest request, Exception ex) throws JsonGenerationException, JsonMappingException, IOException{		
		Map<String, Object> map_return = new HashMap<String, Object>();
		map_return.put("error", JSONConverter.constructError(ex, messageSource, LocaleService.getLocale(request)));
		map_return.put("status", "error");
		return mapper.writeValueAsString(map_return);
	}
}
