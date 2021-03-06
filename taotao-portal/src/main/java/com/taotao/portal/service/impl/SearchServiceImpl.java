package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyf on 2016/12/11.
 */
@Service
public class SearchServiceImpl implements SearchService {


    @Value("${SEARCH_BASE_URL}")
    String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {
        Map<String,String> param = new HashMap<>();
        param.put("q", queryString);
        param.put("page",page+"");

        try {
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
            if (taotaoResult.getStatus() == 200){
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
