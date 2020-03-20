package com.whyxs.controller.manager.portal;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whyxs.common.bean.entity.PortalAbout;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.CompleteUtil;
import com.whyxs.common.util.JSONUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.service.portal.PortalAboutService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whyxs
 * @since 2019-07-12
 */
@Controller
@RequestMapping("/portal/about")
public class PortalAboutController extends BaseController {

    @Autowired
    private PortalAboutService aboutService;

    /**
     * list页
     */
    @RequiresPermissions({"portal:about:site"})
    @RequestMapping("/site")
    public String list(Model model){
        PortalAbout about = aboutService.selectOne(new EntityWrapper<PortalAbout>().last("limit 1"));
        model.addAttribute("about",about);
        model.addAttribute("links",JSONUtil.parseArray(about.getLinks(), Map.class));
        return "portal/about/about";
    }


    /**
     * 保存
     */
    @ResponseBody
    @RequestMapping("/save")
    public RestResultVo save(String param) {
        try {
            PortalAbout about = JSONUtil.parseObject(param, PortalAbout.class);
            if (StringUtils.isEmpty(about.getId())){
                CompleteUtil.initCreateInfo(about);	//id为空时，完善创建信息
            }
            aboutService.insertOrUpdate(about);
            return RestResultVo.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }
	
}
