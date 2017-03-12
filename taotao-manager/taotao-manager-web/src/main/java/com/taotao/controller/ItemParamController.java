package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyf on 2016/12/5.
 */

@Controller
@RequestMapping(value = "/item/param")
public class ItemParamController {

    @Autowired
    ItemParamService paramService;

    @RequestMapping(value = "/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
        TaotaoResult result = paramService.getItemParamByCid(itemCatId);
        return result;
    }



    @RequestMapping(value = "/save/{itemCatId}")
    @ResponseBody
    public TaotaoResult saveItemParam(@PathVariable Long itemCatId, String paramData){
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(itemCatId);
        itemParam.setParamData(paramData);
        TaotaoResult result = paramService.insertItemParam(itemParam);
        return result;


    }
}
