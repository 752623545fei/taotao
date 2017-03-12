package com.taotao.portal.controller;

import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyf on 2016/12/11.
 */

@Controller

public class ItemController {

    @Autowired
    ItemService itemService;


    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model){
        ItemInfo item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "item";
    }

    @RequestMapping(value="/item/desc/{itemId}", produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId){
        TbItemDesc desc = itemService.getItemDescById(itemId);
        return desc.getItemDesc();
    }

    @RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId) {
        String string = itemService.getItemParamById(itemId);
        return string;
    }



}
