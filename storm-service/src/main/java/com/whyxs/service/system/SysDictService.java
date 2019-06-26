/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SysDictService
 * Author:   whyxs
 * Date:     2019/6/21 17:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.service.system;

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
public interface SysDictService {

    List<Map<String,Object>> getAreaListByParent(String pid);
}
 

