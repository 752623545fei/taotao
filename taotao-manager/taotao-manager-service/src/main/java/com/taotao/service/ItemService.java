package com.taotao.service;

import com.taotao.common.pojo.DateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem queryItem(long id);

	DateGridResult queryItemByPage(Integer page, Integer rows);

	TaotaoResult addItem(TbItem item, String desc, String param);
}
