package com.playlife.utility.captcha;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class OrderingCaptcha implements ICaptcha {
	public String order;
	private HashMap<String, OrderingCaptchaBean> beanHash;
	private HashMap<String, Boolean> answerHash;
	
	public OrderingCaptcha(String order){
		this.order = order;
		this.beanHash = new HashMap<String, OrderingCaptchaBean>();
		this.answerHash = new HashMap<String, Boolean>();
	}
	
	public void put(String sessionID, String path){
		this.answerHash.put(sessionID, null);
		this.beanHash.put(sessionID, new OrderingCaptchaBean(order, path));
	}
	
	public ByteArrayOutputStream get(String sessionID, int pos){
		return this.beanHash.get(sessionID).getOutput(pos);
	}
	
	public boolean check(String sessionID, String reorder){
		Boolean isAnswered = this.answerHash.get(sessionID);
		
		if (reorder != null && isAnswered == null){
			Boolean result = this.beanHash.get(sessionID).check(sessionID, reorder);
			this.answerHash.put(sessionID, result);
			return result;
		} else if (isAnswered != null && reorder == null){
			this.answerHash.put(sessionID, null);
			return isAnswered;
		} else {
			this.answerHash.put(sessionID, null);
			return false;
		}
	}
}
