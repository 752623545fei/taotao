package com.taotao.controller;

import com.taotao.common.pojo.DateGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @RequestMapping(value = "/query/list")
    @ResponseBody
    public DateGridResult getContentByPage(@Param("page") Integer page ,
                                           @Param("rows") Integer rows,
                                           @Param("categoryId") Long categoryId){
        DateGridResult result = contentService.queryContentByPage(categoryId, page, rows);
        return result;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent){
        TaotaoResult result = contentService.insertContent(tbContent);
        return result;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public TaotaoResult delContent(@RequestParam("ids") String ids){
        TaotaoResult result = contentService.deleteContent(ids);
        return result;
    }
}
