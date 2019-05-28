/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BlogArticle
 * Author:   whyxs
 * Date:     2019/5/10 15:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.common.bean.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
@TableName("blog_tags")
public class BlogTags extends Model<BlogTags> {

    // 主键
    @TableId(type= IdType.UUID)
    private String id;
    private String tagsName;
    private String tagsDesc;
    private String createBy;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public String getTagsDesc() {
        return tagsDesc;
    }

    public void setTagsDesc(String tagsDesc) {
        this.tagsDesc = tagsDesc;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
