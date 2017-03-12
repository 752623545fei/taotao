package com.taotao.service;


import com.taotao.common.pojo.TreeNode;

import java.util.List;

/**
 * Created by lyf on 2016/12/4.
 */

public interface ItemCatService {

    public List<TreeNode> getItemCatList(long parentId);
}
