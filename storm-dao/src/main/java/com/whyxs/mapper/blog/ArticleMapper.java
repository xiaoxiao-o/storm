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
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.whyxs.common.bean.entity.BlogArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
public interface ArticleMapper extends BaseMapper<BlogArticle> {

    void BatchSaveUserRoleRel(List<Map<String,Object>> articleTags);

    void deleteArticleTagRelByArticleId(String articleId);

    void changeSomeStatus(@Param("id") String id, @Param("key") String key,@Param("val")  String val);


    //api
    List<BlogArticle> selectArticleTopList();

    List<BlogArticle> selectPageCustom(Pagination page);

}
 

