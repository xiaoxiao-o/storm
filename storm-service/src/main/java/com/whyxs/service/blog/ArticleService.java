package com.whyxs.service.blog;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.whyxs.common.bean.entity.BlogArticle;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
public interface ArticleService extends IService<BlogArticle> {

    void save(BlogArticle article, List<String> tagIds);

    void changeSomeStatus(String id,String key,String val);


    //api
    List<BlogArticle> selectArticleTopList();

    Page<BlogArticle> selectPageCustom(Page page,Map<String,Object> condition);

    List<Map<String,Object>> selectArticleRecord();

    BlogArticle selectByIdCustom(String id);

}
 

