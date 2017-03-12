package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lyf on 2016/12/7.
 */

@Controller
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @RequestMapping(value = "/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCategoryId){
        try {
            List<TbContent> list = contentService.queryContentList(contentCategoryId);
            return  TaotaoResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}