package com.whyxs.controller;

import com.alibaba.fastjson.JSONObject;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.util.OkHttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController extends BaseController {

	@Value("#{configProperties['note.available']}")
	private String noteAvailable;

	@Value("#{configProperties['article.lunbo']}")
	private String articleLunbo;

	@Value("#{configProperties['article.top']}")
	private String articleTop;

	@Value("#{configProperties['article.recom']}")
	private String articleRecom;

	@Value("#{configProperties['article.hot']}")
	private String articleHot;

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;
	
	@RequestMapping(value= {"/","/home"})
	public String home(Model model) throws Exception {
		//首页通知
		model.addAttribute("noteAvailables",OkHttpClientUtil.getResponseData(noteAvailable,null));
		//轮播的文章
		model.addAttribute("articleLunbos",OkHttpClientUtil.getResponseData(articleLunbo,null));
		//置顶的文章

		model.addAttribute("articleTops",OkHttpClientUtil.getResponseData(articleTop,null));
		//推荐的文章
		model.addAttribute("articleRecoms",OkHttpClientUtil.getResponseData(articleRecom,null));
		//热门的文章
		model.addAttribute("articleHots",OkHttpClientUtil.getResponseData(articleHot,null));

		//站点信息
		JSONObject about = (JSONObject)OkHttpClientUtil.getResponseData(aboutInfo,null);
		model.addAttribute("about",about);
		//友链
		model.addAttribute("lBody", JSONUtil.parseArray(about.getString("lBody"), Map.class));

		return "home";
	}

}
