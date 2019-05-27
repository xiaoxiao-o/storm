package com.whyxs.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.util.JSONUtil;

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
	 * 当前登陆的用户
	 */
	protected SysUser currentUser() {
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 获取分页对象
	 */
	protected <T> Page<T> getPage( int pageNumber,int pageSize) {
		return new Page<T>(pageNumber, pageSize);
	}
	
	/**
	 * 获取参数对象
	 */
	@SuppressWarnings("unchecked")
	protected <T> EntityWrapper<T> getEtityWrapper(String paramJson) {
		//设置所有条件为相等，局限性较大，仅适用于部分功能
		return (EntityWrapper<T>) new EntityWrapper<T>().allEq(JSONUtil.parseObject(paramJson, Map.class));
	}
	
	
	
}
