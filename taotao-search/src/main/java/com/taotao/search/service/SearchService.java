package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * Created by lyf on 2016/12/10.
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
