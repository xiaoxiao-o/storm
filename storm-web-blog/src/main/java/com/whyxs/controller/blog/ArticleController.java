package com.whyxs.controller.blog;

import com.whyxs.common.util.JSONUtil;
import com.whyxs.common.util.OkHttpClientUtil;
import com.whyxs.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ArticleController extends BaseController {

	@Value("#{configProperties['about.info']}")
	private String aboutInfo;

	@Value("#{configProperties['article.page']}")
	private String articlePage;

	@Value("#{configProperties['subject.all']}")
	private String subjectAll;

	@Value("#{configProperties['tag.all']}")
	private String tagAll;

	@Value("#{configProperties['article.detail']}")
	private String articleDetail;

	@Value("#{configProperties['article.read.add']}")
	private String articleReadAdd;

	@Value("#{configProperties['article.comment.add']}")
	private String articleCommentAdd;

	@Value("#{configProperties['article.praise.add']}")
	private String articlePraiseAdd;

	@Value("#{configProperties['article.share.add']}")
	private String articleShareAdd;

	@RequestMapping(value= {"articlelist"})
	public String home(Model model) throws Exception {
		//站点信息
		model.addAttribute("about", OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo)));
		//分类
		model.addAttribute("subjectAll",OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(subjectAll)));
		//标签
		model.addAttribute("tagAll", OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(tagAll)));

		//原地址携带信息
		model.addAttribute("condition", JSONUtil.toJSONString(request.getParameterMap()));

		return "articlelist";
	}


	/**
	 * 文章分页
	 */
	@ResponseBody
	@RequestMapping(value= {"article/page"})
	public Object articlePage(String param){
		return OkHttpClientUtil.doPost(articlePage, JSONUtil.parseObject(param,Map.class));
	}


	/**
	 * 根据id查询文章
	 */
	@RequestMapping(value= {"article/detail/{id}"})
	public String home(@PathVariable(value = "id") String id, Model model) throws Exception {
		//站点信息
		model.addAttribute("about",OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(aboutInfo)));
		Map<String,String> param = new HashMap<>();
		param.put("id",id);
		model.addAttribute("article",OkHttpClientUtil.getResponseData(OkHttpClientUtil.doPost(articleDetail,param)));

		//分类
		model.addAttribute("subjectAll",OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(subjectAll)));
		//标签
		model.addAttribute("tagAll", OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(tagAll)));
		return "detail";
	}


	/**
	 * 当前文章阅读+1
	 */
	@ResponseBody
	@RequestMapping("/article/read/add")
	public Object articleReadCountAddOne( String param) {
		return OkHttpClientUtil.doPost(articleReadAdd, JSONUtil.parseObject(param,Map.class));
	}

	/**
	 * 当前文章评论+1
	 */
	@ResponseBody
	@RequestMapping("/article/comment/add")
	public Object articleCommentountAddOne( String param) {
		return OkHttpClientUtil.doPost(articleCommentAdd, JSONUtil.parseObject(param,Map.class));
	}

	/**
	 * 当前文章点赞+1
	 */
	@ResponseBody
	@RequestMapping("/article/praise/add")
	public Object articlePraiseCountAddOne( String param) {
		return OkHttpClientUtil.doPost(articlePraiseAdd, JSONUtil.parseObject(param,Map.class));
	}

	/**
	 * 当前文章分享+1
	 */
	@ResponseBody
	@RequestMapping("/article/share/add")
	public Object articleShareCountAddOne( String param) {
		return OkHttpClientUtil.doPost(articleShareAdd, JSONUtil.parseObject(param,Map.class));
	}

}
