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
 * 站点信息
 * @author whyxs
 * @create 2019/5/10
 * @since 1.0.0
 */
@TableName("blog_about")
public class BlogAbout extends Model<BlogAbout> {

    // 主键
    @TableId(type= IdType.UUID)
    private String id;

    private String pName;
    private String pSign;
    private String pHead;
    private String pBack;
    private String pProvince;
    private String pCity;
    private String pWechat;
    private String pQq;
    private String pEmail;
    private String pWeibo;
    private String pGit;
    private String pDesc;

    private String wName;
    private String wSign;
    private String wHead;
    private String wBack;
    private Date wOnTime;
    private String wDesc;

    private String lName;
    private String lSign;
    private String lHead;
    private String lBack;
    private String lBody;

    private String mName;
    private String mSign;
    private String mHead;
    private String mBack;
    private String mWechatCode;
    private String mAlibabaCode;

    private String createBy;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSign() {
        return pSign;
    }

    public void setpSign(String pSign) {
        this.pSign = pSign;
    }

    public String getpHead() {
        return pHead;
    }

    public void setpHead(String pHead) {
        this.pHead = pHead;
    }

    public String getpBack() {
        return pBack;
    }

    public void setpBack(String pBack) {
        this.pBack = pBack;
    }

    public String getpProvince() {
        return pProvince;
    }

    public void setpProvince(String pProvince) {
        this.pProvince = pProvince;
    }

    public String getpCity() {
        return pCity;
    }

    public void setpCity(String pCity) {
        this.pCity = pCity;
    }

    public String getpWechat() {
        return pWechat;
    }

    public void setpWechat(String pWechat) {
        this.pWechat = pWechat;
    }

    public String getpQq() {
        return pQq;
    }

    public void setpQq(String pQq) {
        this.pQq = pQq;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public String getpWeibo() {
        return pWeibo;
    }

    public void setpWeibo(String pWeibo) {
        this.pWeibo = pWeibo;
    }

    public String getpGit() {
        return pGit;
    }

    public void setpGit(String pGit) {
        this.pGit = pGit;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwSign() {
        return wSign;
    }

    public void setwSign(String wSign) {
        this.wSign = wSign;
    }

    public String getwHead() {
        return wHead;
    }

    public void setwHead(String wHead) {
        this.wHead = wHead;
    }

    public String getwBack() {
        return wBack;
    }

    public void setwBack(String wBack) {
        this.wBack = wBack;
    }

    public Date getwOnTime() {
        return wOnTime;
    }

    public void setwOnTime(Date wOnTime) {
        this.wOnTime = wOnTime;
    }

    public String getwDesc() {
        return wDesc;
    }

    public void setwDesc(String wDesc) {
        this.wDesc = wDesc;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlSign() {
        return lSign;
    }

    public void setlSign(String lSign) {
        this.lSign = lSign;
    }

    public String getlHead() {
        return lHead;
    }

    public void setlHead(String lHead) {
        this.lHead = lHead;
    }

    public String getlBack() {
        return lBack;
    }

    public void setlBack(String lBack) {
        this.lBack = lBack;
    }

    public String getlBody() {
        return lBody;
    }

    public void setlBody(String lBody) {
        this.lBody = lBody;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSign() {
        return mSign;
    }

    public void setmSign(String mSign) {
        this.mSign = mSign;
    }

    public String getmHead() {
        return mHead;
    }

    public void setmHead(String mHead) {
        this.mHead = mHead;
    }

    public String getmBack() {
        return mBack;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }

    public String getmWechatCode() {
        return mWechatCode;
    }

    public void setmWechatCode(String mWechatCode) {
        this.mWechatCode = mWechatCode;
    }

    public String getmAlibabaCode() {
        return mAlibabaCode;
    }

    public void setmAlibabaCode(String mAlibabaCode) {
        this.mAlibabaCode = mAlibabaCode;
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
