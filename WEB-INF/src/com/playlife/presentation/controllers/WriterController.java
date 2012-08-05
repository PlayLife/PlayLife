package com.playlife.presentation.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/writer")
public class WriterController {
	
	@SuppressWarnings("finally")
	@RequestMapping("/writer")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
		} catch (Exception ex){

		} finally {
			ModelAndView mav = new ModelAndView("writer");	
			return mav;
		}
	}
}
