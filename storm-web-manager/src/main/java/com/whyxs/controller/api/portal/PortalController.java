/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BlogController
 * Author:   whyxs
 * Date:     2019/6/14 15:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.controller.api.portal;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.whyxs.common.bean.entity.PortalAbout;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.controller.BaseController;
import com.whyxs.service.portal.PortalAboutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author whyxs
 * @create 2019/6/14
 * @since 1.0.0
 */
@Api(value="门户系统相关接口")
@RestController
@RequestMapping("/api/portal")
public class PortalController extends BaseController {

    @Autowired
    private PortalAboutService aboutService;

    /**
     * 所有门户链接
     */
    @ApiOperation(value="获取所有门户链接")
    @GetMapping("/selectPortalSiteList")
    public RestResultVo selectPortalSiteList() {
        try {
            PortalAbout about = aboutService.selectOne(new EntityWrapper<PortalAbout>().last("limit 1"));
            return RestResultVo.success(about);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResultVo.error(null);
        }
    }
}
