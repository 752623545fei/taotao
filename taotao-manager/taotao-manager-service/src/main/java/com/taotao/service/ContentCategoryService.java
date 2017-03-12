package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbContentCategoryMapper;

import java.util.List;

/**
 * Created by lyf on 2016/12/6.
 */
public interface ContentCategoryService {


    List<TreeNode> getCategoryList(Long parentId);

    TaotaoResult insertContentCategory(Long parentId, String name);

    TaotaoResult removeCategory(Long parentId,Long id);

    TaotaoResult updateContentCategory(Long id, String name);

}
