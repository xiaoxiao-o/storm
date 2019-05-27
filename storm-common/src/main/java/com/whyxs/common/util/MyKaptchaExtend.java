package com.whyxs.common.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.google.code.kaptcha.util.Config;

/**
 * 生成验证码，这里主要做配置
 * @author yx  
 * @date 2019年4月28日
 */
public class MyKaptchaExtend extends KaptchaExtend {
	
	private Properties props = new Properties();
	private Producer kaptchaProducer = null;
	private String sessionKeyValue = null;
	private String sessionKeyDateValue = null;
	
	public MyKaptchaExtend() {
		ImageIO.setUseCache(false);

		this.props.put(com.google.code.kaptcha.Constants.KAPTCHA_BORDER, "no");
		this.props.put(com.google.code.kaptcha.Constants.KAPTCHA_IMAGE_WIDTH, "136");
		this.props.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
		this.props.put(com.google.code.kaptcha.Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
		
		Config config = new Config(this.props);
		this.kaptchaProducer = config.getProducerImpl();
		this.sessionKeyValue = config.getSessionKey();
		this.sessionKeyDateValue = config.getSessionDate();
	}
	
	@Override
	public void captcha(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setHeader("Cache-Control", "no-store, no-cache");
		resp.setContentType("image/jpeg");
		String capText = this.kaptchaProducer.createText();
		req.getSession().setAttribute(this.sessionKeyValue, capText);
		req.getSession().setAttribute(this.sessionKeyDateValue, new Date());
		BufferedImage bi = this.kaptchaProducer.createImage(capText);
		ServletOutputStream out = resp.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		req.getSession().setAttribute(this.sessionKeyValue, capText);
		req.getSession().setAttribute(this.sessionKeyDateValue, new Date());
	}

	@Override
	public String getGeneratedKey(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	}
}
