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
import com.whyxs.common.bean.entity.BlogComment;
import com.whyxs.mapper.blog.CommentMapper;
import com.whyxs.service.blog.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, BlogComment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

}
