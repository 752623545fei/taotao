package com.taotao.search.mapper;

import com.taotao.search.pojo.Item;

import java.util.List;

/**
 * Created by lyf on 2016/12/10.
 */
public interface ItemMapper {

    List<Item> getItemList();

    Item getItemById(String id);
}
