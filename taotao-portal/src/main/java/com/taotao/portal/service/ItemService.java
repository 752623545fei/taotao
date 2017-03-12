package com.taotao.portal.service;


import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.ItemInfo;


/**
 * Created by lyf on 2016/12/11.
 */
public interface ItemService {

    ItemInfo getItemById(long itemId);

    TbItemDesc getItemDescById(long itemId);

    String getItemParamById(long itemId);
}
