package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * Created by lyf on 2016/12/7.
 */
public interface ContentService {

    List<TbContent> queryContentList(long contentCid);
}
