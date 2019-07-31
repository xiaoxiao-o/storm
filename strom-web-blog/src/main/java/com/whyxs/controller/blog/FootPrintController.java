package com.whyxs.controller.blog;

import com.alibaba.fastjson.JSONArray;
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
public class FootPrintController extends BaseController {

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;

	@Value("#{configProperties['word.all']}")
	private String wordAll;

	@Value("#{configProperties['article.record']}")
	private String articleRecord;

	@RequestMapping(value= {"/footprint","/footprint/timeline"})
	public String timeline(Model model) throws Exception {
		//站点信息
		model.addAttribute("about", OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo)));
		model.addAttribute("words",OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(wordAll)));
		return "timeline";
	}

	@RequestMapping(value= {"/footprint/articlerecord"})
	public String articlerecord(Model model) throws Exception {
		//站点信息
		model.addAttribute("about",OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo)));

		JSONArray articleRecords = (JSONArray) OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(articleRecord));
		for (Object articleRecord : articleRecords) {
			JSONObject articleJson = (JSONObject)articleRecord;
			articleJson.put("articles", JSONUtil.parseArray(articleJson.getString("articles"), Map.class));
		}
		model.addAttribute("articleRecords",articleRecords);
		return "articlerecord";
	}

}
