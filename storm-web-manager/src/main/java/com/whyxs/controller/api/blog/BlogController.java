/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BlogController
 * Author:   whyxs
 * Date:     2019/6/14 15:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.controller.api.blog;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whyxs.common.bean.entity.*;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author whyxs
 * @create 2019/6/14
 * @since 1.0.0
 */
@Api(value="博客系统相关接口")
@RestController
@RequestMapping("/api/blog")
public class BlogController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private AboutService aboutService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TagService tagService;

    /**
     * 所有可用通知
     */
    @ApiOperation(value="获取可用通知")
    @GetMapping("/selectNoteAvailableList")
    public RestResultVo selectNoteAvailableList() {
        try {
            List<BlogNote> list = noteService.selectList(new EntityWrapper<BlogNote>().eq("status","1").orderBy("create_time",false));
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }


    /**
     * 所有文章
     */
    @ApiOperation(value="获取轮播文章*8")
    @GetMapping("/selectArticleLunboList")
    public RestResultVo selectArticleLunboList() {
        try {
            List<BlogArticle> list = articleService.selectList(new EntityWrapper<BlogArticle>().eq("lunbo","1").orderBy("create_time",false).last("limit 8"));
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有文章
     */
    @ApiOperation(value="获取置顶文章*8")
    @GetMapping("/selectArticleTopList")
    public RestResultVo selectArticleTopList() {
        try {
            List<BlogArticle> list = articleService.selectArticleTopList();
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有文章
     */
    @ApiOperation(value="获取推荐文章*8")
    @GetMapping("/selectArticleRecomList")
    public RestResultVo selectArticleRecomList() {
        try {
            List<BlogArticle> list = articleService.selectList(new EntityWrapper<BlogArticle>().eq("recom","1").orderBy("create_time",false).last("limit 8"));
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有文章
     */
    @ApiOperation(value="获取热文排行*8")
    @GetMapping("/selectArticleHotList")
    public RestResultVo selectArticleHotList() {
        try {
            List<BlogArticle> list = articleService.selectList(new EntityWrapper<BlogArticle>().orderBy("read_count",false).orderBy("create_time",false).last("limit 8"));
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 获取站点信息
     */
    @ApiOperation(value="获取站点信息")
    @GetMapping("/selectAboutInfo")
    public RestResultVo selectAboutInfo() {
        try {
            BlogAbout about = aboutService.selectOne(new EntityWrapper<BlogAbout>().last("limit 1"));
            return RestResultVo.success(about);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    //****************//


    /**
     * 分页查询
     */
    @ApiOperation(value="获取分页文章")
    @PostMapping("/selectArticleListPage")
    public RestResultVo selectListPage(
            @RequestParam(defaultValue="1")String page,
            @RequestParam(defaultValue="8")String limit) {
        try {
            Page<BlogArticle> pageResut = articleService.selectPageCustom(this.getPage(Integer.valueOf(page), Integer.valueOf(limit)));
            return RestResultVo.success(pageResut);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有分类
     */
    @ApiOperation(value="获取所有分类")
    @GetMapping("/selectSubjectList")
    public RestResultVo selectSubjectList() {
        try {
            List<BlogSubject> list = subjectService.selectList(null);
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有标签
     */
    @ApiOperation(value="获取所有标签")
    @GetMapping("/selectTagList")
    public RestResultVo selectTagList() {
        try {
            List<BlogTag> list = tagService.selectList(null);
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }
}
