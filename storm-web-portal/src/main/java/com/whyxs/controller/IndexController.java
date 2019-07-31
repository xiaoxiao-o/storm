/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IndexController
 * Author:   whyxs
 * Date:     2019/7/10 9:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.controller;

import com.alibaba.fastjson.JSONObject;
import com.whyxs.BaseController;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.common.util.OkHttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> ,
 * 〈〉
 *
 * @author whyxs
 * @create 2019/7/10
 * @since 1.0.0
 */
@Controller
public class IndexController extends BaseController {

    @Value("#{configProperties['site.list']}")
    private String siteList;

    @RequestMapping(value={"/","index"})
    public String index(Model model){
        JSONObject about = (JSONObject) OkHttpClientUtil.getResponseData(OkHttpClientUtil.doGet(siteList));
        //站点信息
        model.addAttribute("about",about);
        //链
        model.addAttribute("links", JSONUtil.parseArray(about.getString("links"), Map.class));
        return "index";
    }

}
