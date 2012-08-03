package com.playlife.utility.captcha;

import java.io.ByteArrayOutputStream;

public interface ICaptcha {
	public void put(String sessionID, String path);
	public ByteArrayOutputStream get(String sessionID, int pos);
	public boolean check(String sessionID, String reorder);
}
