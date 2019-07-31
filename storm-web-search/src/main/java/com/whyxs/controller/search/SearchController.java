package com.whyxs.controller.search;

import com.whyxs.common.bean.vo.PageListVo;
import com.whyxs.common.bean.vo.RestResultVo;
import com.whyxs.common.util.OkHttpClientUtil;
import com.whyxs.controller.BaseController;
import com.whyxs.util.solr.SolrUtil;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/blog")
public class SearchController extends BaseController {

	@Value("#{configProperties['solr.blog.article.url']}")
	private String url;

	@Value("#{configProperties['solr.blog.article.core']}")
	private String core;

	@Value("#{configProperties['solr.blog.article.import.full.param']}")
	private String fullImportParam;

	@Value("#{configProperties['solr.blog.article.import.delta.param']}")
	private String deltaImportParam;

	/**
	 * 全文检索,分页
	 */
	@RequestMapping(value= {"/article/page"})
	public PageListVo blogArticle(String current, String size,String keyword) throws Exception {
		try {
			SolrDocumentList list = SolrUtil.query(SolrUtil.init(url+core),
					"keyword:"+keyword,Integer.valueOf(current),Integer.valueOf(size));
			return PageListVo.success((int)list.getNumFound(),list);
		}catch (Exception e){
			e.printStackTrace();
			return PageListVo.error();
		}
	}

	/**
	 * 全量更新索引
	 */
	@RequestMapping(value= {"/article/import/full"})
	public RestResultVo importFull(){
		try {
			OkHttpClientUtil.doGet(url+core+"/dataimport?"+fullImportParam);
			return RestResultVo.success(null);
		}catch (Exception e){
			e.printStackTrace();
			return RestResultVo.error(null);
		}
	}

	/**
	 * 增量更新索引
	 */
	@RequestMapping(value= {"/article/import/delta"})
	public RestResultVo importDelta(){
		try {
			OkHttpClientUtil.doGet(url+core+"/dataimport?"+deltaImportParam);
			return RestResultVo.success(null);
		}catch (Exception e){
			e.printStackTrace();
			return RestResultVo.error(null);
		}
	}

}
