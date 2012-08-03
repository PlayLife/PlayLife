package com.playlife.presentationLayer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
	@SuppressWarnings("finally")
	@RequestMapping("/test")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
		} catch (Exception ex){

		} finally {
			ModelAndView mav = new ModelAndView("jsonOutput");	
			return mav;	
		}
	}
}
