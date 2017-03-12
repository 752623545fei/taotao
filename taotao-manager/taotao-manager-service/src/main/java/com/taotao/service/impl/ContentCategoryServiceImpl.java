package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lyf on 2016/12/6.
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<TreeNode> getCategoryList(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> categoryList = contentCategoryMapper.selectByExample(example);
        List<TreeNode> resultList = new ArrayList<>();

        for(TbContentCategory category : categoryList){
            TreeNode treeNode = new TreeNode();
            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            treeNode.setState(category.getIsParent()?"closed":"open");
            resultList.add(treeNode);
        }
        return resultList;
    }

    @Override
    public TaotaoResult insertContentCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder();
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);
        contentCategory.getId();

        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parent.getIsParent()){
            parent.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return TaotaoResult.ok(contentCategory);
    }

    @Override
    public TaotaoResult removeCategory(Long parentId, Long id) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);

        contentCategoryMapper.deleteByPrimaryKey(id);
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(contentCategory.getParentId());
        List<TbContentCategory> categoryList = contentCategoryMapper.selectByExample(example);
        if (categoryList==null || categoryList.size()==0){
            TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
            parent.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContentCategory(Long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return TaotaoResult.ok();

    }
}
