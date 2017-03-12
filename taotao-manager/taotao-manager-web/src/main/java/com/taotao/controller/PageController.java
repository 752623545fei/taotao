package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lyf on 2016/11/24.
 */

@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping(value = "/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
