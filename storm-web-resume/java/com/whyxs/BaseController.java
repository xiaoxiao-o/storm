package com.whyxs;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * super controller
 * @author yx  
 * @date 2019年4月20日
 */
public class BaseController {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	protected HttpSession session;

	@Autowired
	protected ServletContext applicationContext;
	
	/**
	 * 	重定向至地址 url
	 */
	protected String redirectTo(String url) {
		return new StringBuffer("redirect:").append(url).toString();
	}
	

}
