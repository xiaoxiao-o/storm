package com.whyxs.controller.blog;

import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.BlogComment;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.CommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog/comment")
public class CommentController extends BaseController{
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * list页
	 */
	@RequiresPermissions({"comment:list"})
    @RequestMapping("/list")  
    public String list(Model model){
    	return "blog/comment/commentList";
    }
    
	/**
	 * 分页带参查询
	 */
    @ResponseBody
    @RequestMapping("/selectListPage")
    public PageListVo selectListPage(
    		@RequestParam(defaultValue="1")int page,
    		@RequestParam(defaultValue="10")int limit,
    		@RequestParam(required=false)String paramJson) {
    	try {
			Page<BlogComment> pageResut = commentService.selectPage(this.getPage(page, limit), this.getEtityWrapper(paramJson));
			return PageListVo.success(pageResut.getTotal(), pageResut.getRecords());
		} catch (Exception e) {
			e.printStackTrace();
			return PageListVo.error();
		}
    }

	/**
	 * 删除
	 */
    @ResponseBody
    @RequestMapping("/del")
    public RestResultVo del(String id) {
    	try {
			commentService.deleteById(id);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }

	/**
	 * 屏蔽该留言,状态置0,1
	 */
	@ResponseBody
	@RequestMapping("/shield")
	public RestResultVo shield (String id,Integer status) {
		try {
			BlogComment comment = commentService.selectById(id);
			comment.setStatus(status);
			commentService.updateById(comment);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
	}


}
