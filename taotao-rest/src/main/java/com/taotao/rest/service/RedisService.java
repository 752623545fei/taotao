package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by lyf on 2016/12/9.
 */
public interface RedisService {

    TaotaoResult syncContent(long contentCid);

    TaotaoResult syncItemCat();
}
