package com.taotao.controller;

import com.taotao.common.pojo.TreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lyf on 2016/12/4.
 */

@Controller
@RequestMapping(value = "/item/cat")
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;


    @RequestMapping(value = "/list")
    @ResponseBody
    public List<TreeNode> getItemCatList(@RequestParam(value="id", defaultValue="0")Long parentId){
        List<TreeNode> list = itemCatService.getItemCatList(parentId);
        return list;
    }


}
