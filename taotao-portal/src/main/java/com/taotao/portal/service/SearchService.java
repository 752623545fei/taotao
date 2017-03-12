package com.taotao.portal.service;


import com.taotao.portal.pojo.SearchResult;

/**
 * Created by lyf on 2016/12/11.
 */
public interface SearchService {

    SearchResult search(String queryString, int page);
}
