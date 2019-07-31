/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestSolrClient
 * Author:   whyxs
 * Date:     2019/7/30 14:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.util.solr.SolrUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocumentList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author whyxs
 * @create 2019/7/30
 * @since 1.0.0
 */
public class TestSolrClient {

    public static void main(String[] args) throws Exception {

        SolrClient client = SolrUtil.init("http://localhost:8081/solr/blogArticle");
        SolrDocumentList list = SolrUtil.query(client,"keyword:框架",0,2);

        PageListVo vo = PageListVo.success((int)list.getNumFound(),list);

        System.out.println(vo);
    }
}
