package com.whyxs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.util.JSONUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
	
	/**
	 * 获取分页对象
	 */
	protected <T> Page<T> getPage( int pageNumber,int pageSize) {
		return new Page<T>(pageNumber, pageSize);
	}

	/**
	 * 获取ip
	 */
	public String getIpAddress() {
		 String ip = request.getHeader("x-forwarded-for");
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				 ip = request.getHeader("Proxy-Client-IP");
			 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				 ip = request.getHeader("WL-Proxy-Client-IP");
			 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				 ip = request.getHeader("HTTP_CLIENT_IP");
			 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				 ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			 }
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				 ip = request.getRemoteAddr();
			 }
		 return ip;
	 }


}
