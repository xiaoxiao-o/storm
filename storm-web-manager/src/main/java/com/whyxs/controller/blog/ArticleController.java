package com.whyxs.controller.blog;

import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.BlogArticle;
import com.whyxs.common.bean.entity.Enclosure;
import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.ArticleService;
import com.whyxs.service.blog.SubjectService;
import com.whyxs.service.blog.TagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    private ArticleService articleService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TagService tagService;

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
            Page<BlogArticle> pageResut = articleService.selectPage(this.getPage(page, limit), this.getEtityWrapper(paramJson));
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
        model.addAttribute("subjects",subjectService.selectList(null));
        model.addAttribute("tags",tagService.selectList(null));
        return "blog/article/articleAdd";
    }

    /**
     * 新增页
     */
    @RequestMapping("/toEdit")
    public Object toEdit(String id,Model model) {
        BlogArticle article = articleService.selectById(id);
        List<Enclosure> enclosures = JSONUtil.parseArray(article.getEnclosure(),Enclosure.class);
        model.addAttribute("article",article);
        model.addAttribute("enclosures",enclosures);

        //获取文章标签
        model.addAttribute("aTags",tagService.getTagsByArticleId(article.getId()));

        model.addAttribute("subjects",subjectService.selectList(null));
        model.addAttribute("tags",tagService.selectList(null));
        return "blog/article/articleEdit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    public RestResultVo save(String param) {

        try {
            BlogArticle article = JSONUtil.parseObject(param, BlogArticle.class);
            List<String> tagIds = JSONUtil.parseArray(JSONUtil.parseObject(param).get("tag").toString(), String.class);
            articleService.save(article, tagIds);
            return RestResultVo.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 预览文章
     */
    @RequestMapping("/showArticle")
    public String showArticle(String id,Model model){
        BlogArticle article = articleService.selectById(id);
        List<Enclosure> enclosures = JSONUtil.parseArray(article.getEnclosure(),Enclosure.class);
        model.addAttribute("article",article);
        model.addAttribute("enclosures",enclosures);
        return "blog/article/showArticle";
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    public RestResultVo delete(String id) {
        try {
            articleService.deleteById(id);
            tagService.deleteArticleTagRelByArticleId(id);
            return RestResultVo.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 修改文章的各个状态
     */
    @ResponseBody
    @RequestMapping("/changeSomeStatus")
    public RestResultVo changeSomeStatus(String id,String key,String val) {
        try {
            articleService.changeSomeStatus(id,key,val);
            return RestResultVo.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

}
