package com.whyxs.controller.manager;

import java.io.IOException;

import javax.servlet.ServletException;

import com.whyxs.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.whyxs.common.constants.Constants;
import com.whyxs.common.exception.CaptchaException;
import com.whyxs.common.shiro.UsernamePasswordKaptchaToken;
import com.whyxs.common.util.MyKaptchaExtend;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@RequestMapping("")
	public String login(Model model) {
		return "login";
	}
	
	/**
	 * 	登录过程其实只是处理异常的相关信息，具体的登录验证交给shiro来处理
	 */
	@RequestMapping("/doLogin")
	public String doLogin(String username,String password, String kaptcha,RedirectAttributesModelMap map) {
		UsernamePasswordKaptchaToken token = new UsernamePasswordKaptchaToken(username, password,kaptcha);
		Subject subject = SecurityUtils.getSubject();
		boolean loginSucces = false;
		try {
			subject.login(token);
			loginSucces = true;
        } catch (UnknownAccountException e) {
        	map.addFlashAttribute("error", Constants.LOGIN_UNKONWN_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
        	map.addFlashAttribute("error", Constants.LOGIN_INCORRECT_CREDENTIALS);
        } catch (LockedAccountException e) {
        	map.addFlashAttribute("error", Constants.LOGIN_LOCKED_ACCOUNT);
        } catch (CaptchaException aee) {
        	map.addFlashAttribute("error", Constants.LOGIN_CAPTCHA_ERROE);
        } catch (AuthenticationException e) {
        	map.addFlashAttribute("error", Constants.LOGIN_AUTHENTICATION);
        } catch (Exception e) {
        	map.addFlashAttribute("error", Constants.LOGIN_AUTHENTICATION);
		}
		return loginSucces? redirectTo("/index"):redirectTo("/login");
	}
	
	/**
     * 验证码
     */
    @RequestMapping("/kaptcha")
	@ResponseBody
    public  void captcha() throws ServletException, IOException{
		 new MyKaptchaExtend().captcha(request, response);
    }
	
}
