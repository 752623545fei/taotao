package com.taotao.service.impl;

import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbItemCatMapper;

import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/12/4.
 */

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TreeNode> getItemCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List<TreeNode> resultList = new ArrayList<>();
        for(TbItemCat itemCat : list){
            TreeNode node = new TreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }
}
