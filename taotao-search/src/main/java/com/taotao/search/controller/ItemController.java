package com.taotao.search.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyf on 2016/12/10.
 */
@Controller

public class ItemController {

    @Autowired
    ItemService itemService;

    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAllItem(){
        TaotaoResult result = itemService.importAllItems();
        return result;
    }

    @RequestMapping("/deleteall")
    @ResponseBody
    public TaotaoResult deleteAllItem(){
        TaotaoResult result = itemService.deleteAllItems();
        return result;
    }

    @RequestMapping("/import")
    @ResponseBody
    public TaotaoResult importItem(@RequestParam("itemId")String itemId){
        TaotaoResult result = itemService.importItem(itemId);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItem(@RequestParam("itemId")String itemId){
        TaotaoResult result = itemService.deleteItem(itemId);
        return result;
    }
}
