package com.playlife.logicLayer.user;

import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class ImageCaptchaService extends DefaultManageableImageCaptchaService {
	public DefaultManageableImageCaptchaService defaultImageCaptchaService;

	public DefaultManageableImageCaptchaService getDefaultImageCaptchaService() {
		return defaultImageCaptchaService;
	}

	public void setDefaultImageCaptchaService(
			DefaultManageableImageCaptchaService defaultImageCaptchaService) {
		this.defaultImageCaptchaService = defaultImageCaptchaService;
	}
	
}
