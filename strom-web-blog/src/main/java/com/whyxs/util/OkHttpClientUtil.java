/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OkHttpClientUtil
 * Author:   whyxs
 * Date:     2019/6/20 10:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.util;

import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.JSONUtil;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/6/20
 * @since 1.0.0
 */
public class OkHttpClientUtil<T> {

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * get请求，并封装求情结果
     */
    public static String doGet(String url){
        Request request = new Request.Builder().url(url).get().build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()){
                return response.body().string();
            }else{
                throw new Exception("接口异常："+response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求，并封装求情结果
     */
    public static String doPost(String url,Map<String,String> param){
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<Map.Entry<String, String>> it = param.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, String> entry = it.next();
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()){
                return response.body().string();
            }else{
                throw new Exception("接口异常："+response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从请求中获取数据
     */
    public static Object getResponseData(String url, Map<String,String> param){
        String str = param==null ? doGet(url):doPost(url,param);
        if(!StringUtils.isEmpty(str)){
            RestResultVo result = JSONUtil.parseObject(str,RestResultVo.class);
            if(result != null && result.getCode()==RestResultVo.SUCCESS_CODE){
                return result.getData();
            }
        }
        return null;
    }

}
