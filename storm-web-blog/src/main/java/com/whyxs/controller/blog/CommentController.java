package com.whyxs.controller.blog;

import com.whyxs.common.util.JSONUtil;
import com.whyxs.common.util.OkHttpClientUtil;
import com.whyxs.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CommentController extends BaseController {

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;

	@Value("#{configProperties['comment.page']}")
	private String commentPage;

	@Value("#{configProperties['comment.add']}")
	private String addComment;

	@RequestMapping(value= {"/comment"})
	public String comment(Model model) throws Exception {
		//站点信息
		model.addAttribute("about", OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo)));
		return "comment";
	}

	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping(value= {"/comment/add"})
	public Object commentAdd(String param) throws Exception {

		Map<String,Object> comment = JSONUtil.parseObject(param, Map.class);
		comment.put("ipAddress",this.getIpAddress());
		//站点信息
		return OkHttpClientUtil.doPost(addComment,JSONUtil.toJSONString(comment));
	}

	/**
	 * 分页
	 */
	@ResponseBody
	@RequestMapping(value= {"/comment/page"})
	public Object commentAll(String param) throws Exception {
		return OkHttpClientUtil.doPost(commentPage,JSONUtil.parseObject(param,Map.class));
	}

}
