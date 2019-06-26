package com.whyxs.controller.blog;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whyxs.common.bean.entity.BlogAbout;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.AboutService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/blog/about")
public class AboutController extends BaseController{
	
	@Autowired
	private AboutService aboutService;
	
	/**
	 * list页
	 */
	@RequiresPermissions({"about:site"})
    @RequestMapping("/site")
    public String list(Model model){
		BlogAbout about = aboutService.selectOne(new EntityWrapper<BlogAbout>().last("limit 1"));
		JSONUtil.parse(about.getlBody());
		model.addAttribute("about",about);
		model.addAttribute("lBody",JSONUtil.parseArray(about.getlBody(), Map.class));
		return "blog/about/about";
    }
    

	/**
	 * 保存
	 */
    @ResponseBody
    @RequestMapping("/save")
    public RestResultVo save(String param) {
    	try {
			BlogAbout about = JSONUtil.parseObject(param, BlogAbout.class);
			if (StringUtils.isEmpty(about.getId())){
				CompleteUtil.initCreateInfo(about);	//id为空时，完善创建信息
			}
			aboutService.insertOrUpdate(about);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }

}
