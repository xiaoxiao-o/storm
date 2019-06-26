package com.whyxs.controller;

import com.whyxs.util.OkHttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ArticleListController extends BaseController {

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;

	@Value("#{configProperties['article.page']}")
	private String articlePage;

	@Value("#{configProperties['subject.all']}")
	private String subjectAll;

	@Value("#{configProperties['tag.all']}")
	private String tagAll;

	@RequestMapping(value= {"articlelist"})
	public String home(Model model) throws Exception {
		//站点信息
		model.addAttribute("about",OkHttpClientUtil.getResponseData(aboutInfo,null));
		//分类
		model.addAttribute("subjectAll",OkHttpClientUtil.getResponseData(subjectAll,null));
		//标签
		model.addAttribute("tagAll", OkHttpClientUtil.getResponseData(tagAll,null));

		return "articlelist";
	}


	/**
	 * 文章分页
	 */
	@ResponseBody
	@RequestMapping(value= {"article/page"})
	public Object articlePage(
			@RequestParam(defaultValue="1")String page,
			@RequestParam(defaultValue="8")String limit){
		Map<String,String> map = new HashMap<>();
		map.put("page",page);
		map.put("limit",limit);
		return OkHttpClientUtil.getResponseData(articlePage,map);
	}

}
