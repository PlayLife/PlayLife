package com.playlife.presentationLayer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.playlife.logicLayer.user.IUserService;
import com.playlife.presentationLayer.converters.IErrorConverter;
import com.playlife.settings.UISetting;
import com.playlife.utility.captcha.ICaptcha;

@Controller
@RequestMapping("/register/*")
public class RegisterController {
	@Autowired
	@Qualifier("userService")
	IUserService userService;
	
	@Autowired
	@Qualifier("errorConverter")
	IErrorConverter errorConverter;
	
	@Autowired
	@Qualifier("captcha")
	ICaptcha captcha;
	
	@SuppressWarnings("finally")
	@RequestMapping("/register/*")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = null;
		try {			
			if (request.getServletPath().equalsIgnoreCase(UISetting.REGISTER_PATH_MAIN)){
				mav = new ModelAndView("register");
			} else if (request.getServletPath().equalsIgnoreCase(UISetting.REGISTER_PATH_INTRO)){
				mav = new ModelAndView("register_intro");
			}
				
		} catch (Exception ex){
			
		} finally {
			return mav;
		}
	}
}
