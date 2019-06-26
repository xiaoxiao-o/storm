package com.whyxs.controller.blog;

import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.BlogNote;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.NoteService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog/note")
public class NoteController extends BaseController{
	
	@Autowired
	private NoteService noteService;
	
	/**
	 * list页
	 */
	@RequiresPermissions({"note:list"})
    @RequestMapping("/list")  
    public String list(Model model){
    	return "blog/note/noteList";
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
			Page<BlogNote> pageResut = noteService.selectPage(this.getPage(page, limit), this.getEtityWrapper(paramJson));
			return PageListVo.success(pageResut.getTotal(), pageResut.getRecords());
		} catch (Exception e) {
			e.printStackTrace();
			return PageListVo.error();
		}
    }

	/**
	 * 新增
	 */
    @RequestMapping("/toAdd")
    public Object toAdd(Model model) {
		return "blog/note/noteAdd";
    }

	/**
	 * 编辑
	 */
    @RequestMapping("/toEdit")
    public Object edit(String id,Model model) {
		BlogNote note =  noteService.selectById(id);
		model.addAttribute("note", note);
		return "blog/note/noteEdit";
    }

	/**
	 * 保存
	 */
    @ResponseBody
    @RequestMapping("/save")
    public RestResultVo save(String param) {
    	try {
			BlogNote note = JSONUtil.parseObject(param, BlogNote.class);
			if (StringUtils.isEmpty(note.getId())){
				CompleteUtil.initCreateInfo(note);	//id为空时，完善创建信息
			}
			noteService.insertOrUpdate(note);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }

	/**
	 * 删除
	 */
    @ResponseBody
    @RequestMapping("/del")
    public RestResultVo del(String id) {
    	try {
			noteService.deleteById(id);
			return RestResultVo.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return RestResultVo.error(null);
		}
    }

    
}
