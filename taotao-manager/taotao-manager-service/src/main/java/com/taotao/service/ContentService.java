package com.taotao.service;

import com.taotao.common.pojo.DateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * Created by lyf on 2016/12/7.
 */
public interface ContentService {

    DateGridResult queryContentByPage(long categoryId, int page, int rows);

    TaotaoResult insertContent(TbContent tbContent);

    TaotaoResult deleteContent(String ids);
}
