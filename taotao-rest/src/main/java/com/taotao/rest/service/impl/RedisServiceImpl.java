package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lyf on 2016/12/9.
 */

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Value("${INDEX_ITEMCAT_REDIS_KEY}")
    String INDEX_ITEMCAT_REDIS_KEY;

    @Override
    public TaotaoResult syncContent(long contentCid) {
        long hdel = jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult syncItemCat() {
        long del = jedisClient.del(INDEX_ITEMCAT_REDIS_KEY);
        return TaotaoResult.ok();
    }
}
