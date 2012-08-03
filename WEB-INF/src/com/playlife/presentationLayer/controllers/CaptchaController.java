package com.playlife.presentationLayer.controllers;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.playlife.settings.UISetting;
import com.playlife.utility.Request;
import com.playlife.utility.captcha.ICaptcha;

@Controller
@RequestMapping("/captcha/*")
public class CaptchaController {
	@Autowired
	@Qualifier("captcha")
	ICaptcha captcha;
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/captcha/*")
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String uri = request.getServletPath();
	    	String sessionId = request.getSession().getId();
	    	byte[] captchaChallengeAsJpeg = null;
			if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_1)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 0).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_2)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 1).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_3)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 2).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_4)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 3).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_5)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 4).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_6)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 5).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_7)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 6).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_8)){
				captchaChallengeAsJpeg = captcha.get(sessionId, 7).toByteArray();
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_REFRESH)){
				captcha.put(sessionId, request.getRealPath(""));
				
				JSONObject output = new JSONObject();
				output.put(UISetting.STATUS, UISetting.STATUS_OK);
				
				ModelAndView mav = new ModelAndView("jsonOutput", "output", output);
				return mav;
			} else if (uri.equalsIgnoreCase(UISetting.ACTION_CAPTCHA_VERIFY)){
				String reorder = Request.getString(request, UISetting.CAPTCHA_INPUT_REORDER);
				boolean answer = captcha.check(sessionId, reorder);
				
				JSONObject output = new JSONObject();
				if (answer)
					output.put(UISetting.STATUS, UISetting.STATUS_OK);
				else 
					output.put(UISetting.STATUS, UISetting.STATUS_ERROR);
				
				ModelAndView mav = new ModelAndView("jsonOutput", "output", output);
				return mav;
			} else {
				response.sendRedirect("/PlayTheLife/home");
				return null;
			}
			
	        response.setHeader("Cache-Control", "no-store");
	        response.setHeader("Pragma", "no-cache");
	        response.setDateHeader("Expires", 0);
	        response.setContentType("image/jpeg");
	        
	        ServletOutputStream responseOutputStream = response.getOutputStream();
	        responseOutputStream.write(captchaChallengeAsJpeg);
	        responseOutputStream.flush();
	        responseOutputStream.close();
		} catch (Exception ex){

		} finally {	
				
		}
		return null;
	}
}
