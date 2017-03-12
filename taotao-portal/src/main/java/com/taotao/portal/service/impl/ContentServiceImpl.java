package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyf on 2016/12/7.
 */

@Service
public class ContentServiceImpl implements ContentService {


    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;


    @Override
    public String getContentList() {
        try {
            String resp = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
            TaotaoResult result = TaotaoResult.formatToList(resp, TbContent.class);
            List<TbContent> list = (List<TbContent>) result.getData();
            List<Map> resultList = new ArrayList<>();
            for (TbContent content : list){
                Map map = new HashMap<>();
                map.put("src",content.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB",content.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href",content.getUrl());
                map.put("alt",content.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
