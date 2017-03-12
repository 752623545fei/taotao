package com.taotao.rest.controller;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyf on 2016/12/6.
 */

@Controller
public class ItemCatController {

    @Autowired
    ItemCatService itemCatService;

    @RequestMapping(value = "/itemCat/list",
            produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback){
        CatResult itemCatList = itemCatService.getItemCatList();
        String json = JsonUtils.objectToJson(itemCatList);
        String result = callback + "(" + json + ");";
        return result;
    }
}
