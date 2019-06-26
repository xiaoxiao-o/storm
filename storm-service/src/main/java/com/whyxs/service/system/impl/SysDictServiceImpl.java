/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SysDictServiceImpl
 * Author:   whyxs
 * Date:     2019/6/21 17:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.service.system.impl;

import com.whyxs.mapper.system.SysDictMapper;
import com.whyxs.service.system.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/6/21
 * @since 1.0.0
 */
@Service
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    private SysDictMapper dictMapper;

    @Override
    public List<Map<String, Object>> getAreaListByParent(String pid) {
        return dictMapper.getAreaListByParent(pid);
    }
}
