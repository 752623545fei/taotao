package com.taotao.rest.service.impl;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyf on 2016/12/7.
 */

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> queryContentList(long contentCid) {
        //redis
        try {
            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
            if(!StringUtils.isBlank(result)){
                List<TbContent> contentList = JsonUtils.jsonToList(result,TbContent.class);
                return contentList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(contentCid);
        List<TbContent> list = contentMapper.selectByExample(example);
        try {
            String json = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid+"", json);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
