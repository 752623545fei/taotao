package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lyf on 2016/12/6.
 */

@Controller
public class IndexController {

    @Autowired
    ContentService contentService;

    @RequestMapping(value = "/index")
    public String showIndex(Model model){
        String json = contentService.getContentList();
        model.addAttribute("ad1",json);
        return "index";
    }
}
