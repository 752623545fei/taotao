package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by lyf on 2016/12/12.
 */
public interface CheckService {

    TaotaoResult checkData(String data, Integer type);
}
