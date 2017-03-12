package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyf on 2016/12/6.
 */

@Controller
public class ItemParamItemController {

    @Autowired
    ItemParamItemService paramItemService;

    @RequestMapping(value = "/item/param/{itemId}")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId){
        String result = paramItemService.getItemParamByItemId(itemId);
        return result;
    }
}
