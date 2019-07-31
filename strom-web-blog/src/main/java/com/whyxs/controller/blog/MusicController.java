package com.whyxs.controller.blog;

import com.whyxs.common.util.OkHttpClientUtil;
import com.whyxs.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MusicController extends BaseController {

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;

	@Value("#{configProperties['music.all']}")
	private String musicAll;

	@RequestMapping(value= {"/music"})
	public String music(Model model) throws Exception {
		//站点信息
		model.addAttribute("about", OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo)));
		return "music";
	}

	/**
	 * 音乐数据接口
	 */
	@ResponseBody
	@RequestMapping(value= {"/music/all"})
	public Object musicAll(Model model) throws Exception {
		return OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(musicAll));
	}

}
