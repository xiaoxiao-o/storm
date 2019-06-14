package com.whyxs.service.blog;

import com.baomidou.mybatisplus.service.IService;
import com.whyxs.common.bean.entity.BlogTag;

import java.util.List;

/**
 *
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
public interface TagService extends IService<BlogTag> {

    List<BlogTag> getTagsByArticleId(String articleId);

    void deleteArticleTagRelByArticleId(String articleId);
}
 

