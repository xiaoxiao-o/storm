package com.whyxs.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.whyxs.common.bean.entity.SysUser;


/**
 * shiro-util
 * @author yx  
 * @date 2019年4月21日
 */
public class ShiroUtil {

	/**
	 * 密码加密
	 */
	public static String md51024Pwd(String password,Object salt){
		return new SimpleHash("MD5", password, salt, 1024).toString();
	}
	
	/**
	 * 获取用户
	 */
	public static SysUser getShiroUser() {
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}
	
}
