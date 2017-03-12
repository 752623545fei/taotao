package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by lyf on 2016/12/11.
 */
public interface ItemService {

    TaotaoResult getItemBaseInfo(long itemId);

    TaotaoResult getItemDescInfo(long itemId);

    TaotaoResult getItemParamInfo(long itemId);

}
