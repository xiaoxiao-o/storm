/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ArticleMapper
 * Author:   whyxs
 * Date:     2019/5/10 15:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.mapper.blog;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.whyxs.common.bean.entity.BlogTag;

import java.util.List;

/**
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
public interface TagMapper extends BaseMapper<BlogTag> {

    List<BlogTag> getTagsByArticleId(String articleId);

    void deleteArticleTagRelByArticleId(String articleId);

}
 

