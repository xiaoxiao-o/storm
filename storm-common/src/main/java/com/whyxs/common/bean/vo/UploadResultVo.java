/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UploadResultVo
 * Author:   whyxs
 * Date:     2019/6/3 15:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.common.bean.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/6/3
 * @since 1.0.0
 */
public class UploadResultVo {

    private int code;           //状态码

    private String msg;         //信息

    private boolean success;    //是否成功

    private String file_name;   //文件名称

    private String file_path;   //文件路径

    public UploadResultVo() {
    }

    public UploadResultVo(int code, String msg, boolean success, String file_name, String file_path) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.file_name = file_name;
        this.file_path = file_path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public static UploadResultVo bulid(int code, String msg, boolean success, String file_name, String file_path){
        return new UploadResultVo(code, msg, success, file_name, file_path);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
