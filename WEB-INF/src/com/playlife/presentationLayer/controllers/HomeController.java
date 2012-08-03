package com.playlife.presentationLayer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class HomeController {
//	private static final String guestFunctionList = "<a href='login'>Login</a><a href='beforeRegister'>Register</a>";
//	private static final String loginFunctionList = "<a href='book_create'>Write</a><a href='logout'>Logout</a>";
//	
//	@Autowired
//	@Qualifier("handleAccess")
//	IHandleAccess handleAccess;
//	
//	@SuppressWarnings("finally")
//	@RequestMapping("/*")
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HashMap<String, String> map = null;
//		try {
//			map = new HashMap<String, String>();
//						
//			Long uid = Request.getSessionLongAllowNull(request, UserSetting.USER_SESSIONNAME_UID);
//						
//			User user = null;
//			if (uid != null)
//				user = handleAccess.checkUser(uid);
//			if (user == null){
//				map.put("functionList", guestFunctionList);	
//			} else {
//				map.put("functionList", loginFunctionList);
//			}
//		} catch (Exception ex){
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		} finally {
//			ModelAndView mav = new ModelAndView("home", map);	
//			return mav;
//		}
//	}
}
