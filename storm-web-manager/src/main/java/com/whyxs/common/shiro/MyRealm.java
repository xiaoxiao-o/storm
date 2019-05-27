package com.whyxs.common.shiro;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whyxs.common.bean.entity.SysUser;
import com.whyxs.common.constants.Constants;
import com.whyxs.common.exception.CaptchaException;
import com.whyxs.service.system.SysMenuService;
import com.whyxs.service.system.SysUserService;

public class MyRealm extends AuthorizingRealm {
	
	@Autowired 
	private SysUserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); 
		SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
		Set<String> permissions = userService.findResourceByUserId(sysUser.getId());
		info.setStringPermissions(permissions);
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordKaptchaToken user = (UsernamePasswordKaptchaToken) token;
		
		String kaptcha = user.getCaptcha();
		if(kaptcha==null||!kaptcha.equals((String)SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
			throw new CaptchaException();
		}
		
		SysUser sysUser = userService.selectOne(new EntityWrapper<SysUser>().eq("username", user.getUsername()));
		if(sysUser == null){
			throw new UnknownAccountException();
		}
		if(sysUser.getUserState() == Constants.SYSUSER_LOCK){
			throw new LockedAccountException();
		}
		//盐值加密
		ByteSource byteSource = ByteSource.Util.bytes(user.getUsername());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),byteSource,getName());
		return info;
	}

}
