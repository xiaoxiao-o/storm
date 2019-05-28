package com.whyxs.controller.blog;

import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.BlogArticle;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.ArticleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/blog/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService blogArticleService;

    /**
     * 主页
     */
    @RequiresPermissions({"article:list"})
    @RequestMapping("/list")
    public String list(Model model){
        return "blog/article/articleList";
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
            Page<BlogArticle> pageResut = blogArticleService.selectPage(this.getPage(page, limit), this.getEtityWrapper(paramJson));
            return PageListVo.success(pageResut.getTotal(), pageResut.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            return PageListVo.error();
        }
    }

    /**
     * 新增页
     */
    @RequestMapping("/toAdd")
    public Object toAdd(Model model) {
        return "blog/article/articleAdd";
    }
}
