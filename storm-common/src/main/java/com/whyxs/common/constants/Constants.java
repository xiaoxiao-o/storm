package com.whyxs.common.constants;

public class Constants {

	//登录异常状态
	public static final String LOGIN_UNKONWN_ACCOUNT 			= "没有此用户,请核对用户名！";
	public static final String LOGIN_INCORRECT_CREDENTIALS 		= "用户名或密码不正确！";
	public static final String LOGIN_LOCKED_ACCOUNT 			= "账号已被锁定，请联系管理员！";
	public static final String LOGIN_CAPTCHA_ERROE 				= "验证码错误,请核对！";
	public static final String LOGIN_AUTHENTICATION				= "服务器忙,请稍后再试！";
	
	//用户状态
	public static final Integer SYSUSER_LOCK						= 0;
	public static final Integer SYSUSER_OPEN						= 1;
	
	//角色状态
	public static final Integer SYSROLE_LOCK						= 0;
	public static final Integer SYSROLE_OPEN						= 1;
	
	//验证码存储的位置
	public static final String KAPTCHA_SESSION_KEY	 			= com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
	
	//密码初始值
	public static final String ORIGINAL_PASSWORD				= "123456";
	
}
