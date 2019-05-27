package com.whyxs.common.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 1181910311800632044L;

	public CaptchaException() {
		super();
	}

	public CaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CaptchaException(String message) {
		super(message);
	}

	public CaptchaException(Throwable cause) {
		super(cause);
	}
}

