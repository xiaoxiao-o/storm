/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SolrUtil
 * Author:   whyxs
 * Date:     2019/7/30 14:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.whyxs.util.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/7/30
 * @since 1.0.0
 */
public class SolrUtil {

    public static SolrClient init(String url){
        return new HttpSolrClient.Builder(url).build();
    }

    /**
     * 分页关键词查询
     */
    public static SolrDocumentList query(
            SolrClient client, String keyword, int start, int rows) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setQuery(keyword).setStart(start).setRows(rows);
        return client.query(query).getResults();
    }

}
