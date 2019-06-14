/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Enclosure
 * Author:   whyxs
 * Date:     2019/6/10 16:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.common.bean.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉文章的附件
 *
 * @author whyxs
 * @create 2019/6/10
 * @since 1.0.0
 */
public class Enclosure {

    private String fileName;

    private String filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
