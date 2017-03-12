package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * Created by lyf on 2016/12/10.
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws SolrServerException, Exception;
}
