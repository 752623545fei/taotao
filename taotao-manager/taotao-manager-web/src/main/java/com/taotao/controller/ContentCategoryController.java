package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lyf on 2016/12/6.
 */

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<TreeNode> getContentCatList(@RequestParam(value = "id",
            defaultValue = "0") Long parentId){
        List<TreeNode> result = contentCategoryService.getCategoryList(parentId);
        return result;
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name){
        TaotaoResult result = contentCategoryService.insertContentCategory(parentId, name);
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(Long parentId, Long id){
        TaotaoResult result = contentCategoryService.removeCategory(parentId, id);
        return result;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(Long id, String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
        return result;

    }
}
