package com.whyxs.common.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordKaptchaToken extends UsernamePasswordToken {
	
	private static final long serialVersionUID = 6855034463887035291L;
	
	private String captcha;
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UsernamePasswordKaptchaToken() {
		super();

	}

	public UsernamePasswordKaptchaToken(String username, String password, String captcha) {
		super(username, password, password);
		this.captcha = captcha;
	}
	
	

}
