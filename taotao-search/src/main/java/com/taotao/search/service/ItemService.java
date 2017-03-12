package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by lyf on 2016/12/10.
 */
public interface ItemService {

    TaotaoResult importAllItems();

    TaotaoResult deleteAllItems();

    TaotaoResult importItem(String itemId);

    TaotaoResult deleteItem(String itemId);
}
