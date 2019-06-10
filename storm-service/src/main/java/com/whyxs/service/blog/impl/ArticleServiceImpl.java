/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BlogArticleServiceImpl
 * Author:   whyxs
 * Date:     2019/5/10 15:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.service.blog.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.whyxs.common.bean.entity.BlogArticle;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.UUIDUtil;
import com.whyxs.mapper.blog.ArticleMapper;
import com.whyxs.service.blog.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, BlogArticle> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void save(BlogArticle article, List<String> tagIds) {
        String articleId = article.getId();
        //根据id更新或新增用户
        if(articleId != null && !"".equals(articleId)) {
            articleMapper.updateById(article);
            //删除原有关联角色
            articleMapper.deleteArticleTagRelByArticleId(articleId);
        }else {
            articleId = UUIDUtil.get32UUID();
            article.setId(articleId);
            CompleteUtil.initCreateInfo(article);
            articleMapper.insert(article);
        }
        //重置标签
        List<Map<String,Object>> articleTagS = new ArrayList();
        for (String tagId : tagIds) {
            Map<String,Object> articleTag = new HashMap();
            articleTag.put("id", UUIDUtil.get32UUID());
            articleTag.put("articleId", articleId);
            articleTag.put("tagId", tagId);
            articleTagS.add(articleTag);
        }
        articleMapper.BatchSaveUserRoleRel(articleTagS);
    }
}
