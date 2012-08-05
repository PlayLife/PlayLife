package com.playlife.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
//	@Autowired
//	@Qualifier("handleUser")
//	IHandleUser handleUser;
//	
//	@Autowired
//	@Qualifier("errorConverter")
//	IErrorConverter errorConverter;
//	
//	@SuppressWarnings("finally")
//	@RequestMapping("/login")
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HashMap<String, String> map = null;
//		try {
//		} catch (Exception ex){
//			if (map == null){
//				map = new HashMap<String, String>();
//			}
//			map.put("error", errorConverter.constructError(ex).toString());
//		} finally {
//			ModelAndView mav = new ModelAndView("login", map);	
//			return mav;
//		}
//	}
}
