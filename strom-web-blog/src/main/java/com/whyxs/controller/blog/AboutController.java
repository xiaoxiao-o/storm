package com.whyxs.controller.blog;

import com.alibaba.fastjson.JSONObject;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.common.util.OkHttpClientUtil;
import com.whyxs.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AboutController extends BaseController {

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;

	@RequestMapping(value= {"/about"})
	public String home(Model model) throws Exception {
		JSONObject about = (JSONObject) OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo));
		//站点信息
		model.addAttribute("about",about);
		//友链
		model.addAttribute("lBody", JSONUtil.parseArray(about.getString("lBody"), Map.class));
		return "about";
	}

}
