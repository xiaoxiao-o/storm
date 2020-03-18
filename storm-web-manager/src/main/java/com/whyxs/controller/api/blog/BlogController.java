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
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.blog.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private MusicService musicService;

    @Autowired
    private WordService wordService;

    @Autowired
    private CommentService commentService;

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
    @ApiOperation(value="获取轮播文章前八条数据")
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
    @ApiOperation(value="获取置顶文章前八条数据")
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
    @ApiOperation(value="获取推荐文章前八条数据")
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
    @ApiOperation(value="获取热文排行前八条数据")
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
    public RestResultVo selectListPage(String current, String size,String conditionJson) {
        try {
            Page<BlogArticle> pageResut = articleService.selectPageCustom(
                    this.getPage(Integer.valueOf(current),Integer.valueOf(size)),
                            JSONUtil.parseObject(conditionJson,Map.class));
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

    /**
     * 所有音乐
     */
    @ApiOperation(value="获取所有音乐")
    @GetMapping("/selectMusicList")
    public RestResultVo selectMusicList() {
        try {
            List<BlogMusic> list = musicService.selectList(null);
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有文章
     */
    @ApiOperation(value="获取文章归档")
    @GetMapping("/selectArticleRecord")
    public RestResultVo selectArticleRecord() {
        try {
            List<Map<String,Object>> list = articleService.selectArticleRecord();
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 所有心情
     */
    @ApiOperation(value="获取所有闲言碎语")
    @GetMapping("/selectWordList")
    public RestResultVo selectWordList() {
        try {
            List<BlogWord> list = wordService.selectList(new EntityWrapper<BlogWord>().orderBy("create_time",false));
            return RestResultVo.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 分页留言
     */
    @ApiOperation(value="获取分页留言")
    @PostMapping("/selectCommentListPage")
    public RestResultVo selectCommentListPage(String current, String size, @RequestParam(required = false) String articleId) {
        try {
            EntityWrapper<BlogComment> ew = new EntityWrapper<>();
            if(StringUtils.isNotEmpty(articleId)){
                ew.eq("article_id",articleId);
            }
            return RestResultVo.success(
                    commentService.selectPage(
                            this.getPage(Integer.valueOf(current),Integer.valueOf(size)),
                            ew.orderBy("create_time",false)));
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 保存留言
     */
    @ApiOperation(value="新增留言")
    @PostMapping("/addComment")
    public RestResultVo save(@RequestBody BlogComment comment) {
        try {
            commentService.insert(comment);
            return RestResultVo.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 根据条件查询文章的详细信息
     */
    @ApiOperation(value="根据id查询文章的详细信息")
    @PostMapping("/selectArticleDetail")
    public RestResultVo selectArticleDetail(@RequestParam String id) {
        try {
            BlogArticle article = articleService.selectByIdCustom(id);
            return RestResultVo.success(article);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 当前文章阅读+1
     */
    @ApiOperation(value="当前文章阅读+1")
    @PostMapping("/articleReadCountAddOne")
    public RestResultVo articleReadCountAddOne(@RequestParam String id) {
        try {
            BlogArticle article = articleService.selectById(id);
            article.setReadCount(article.getReadCount() + 1);
            articleService.updateById(article);
            return RestResultVo.success(article);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 当前文章评论+1
     */
    @ApiOperation(value="当前文章评论+1")
    @PostMapping("/articleCommentCountAddOne")
    public RestResultVo articleCommentountAddOne(@RequestParam String id) {
        try {
            BlogArticle article = articleService.selectById(id);
            article.setCommentCount(article.getCommentCount() + 1);
            articleService.updateById(article);
            return RestResultVo.success(article);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 当前文章点赞+1
     */
    @ApiOperation(value="当前文章点赞+1")
    @PostMapping("/articlePraiseCountAddOne")
    public RestResultVo articlePraiseCountAddOne(@RequestParam String id) {
        try {
            BlogArticle article = articleService.selectById(id);
            article.setPraiseCount(article.getPraiseCount() + 1);
            articleService.updateById(article);
            return RestResultVo.success(article);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

    /**
     * 当前文章点赞+1
     */
    @ApiOperation(value="当前文章分享+1")
    @PostMapping("/articleShareCountAddOne")
    public RestResultVo articleShareCountAddOne(@RequestParam String id) {
        try {
            BlogArticle article = articleService.selectById(id);
            article.setShareCount(article.getShareCount() + 1);
            articleService.updateById(article);
            return RestResultVo.success(article);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }

}
