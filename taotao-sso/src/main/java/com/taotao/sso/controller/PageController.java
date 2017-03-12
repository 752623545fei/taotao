package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * Created by lyf on 2016/12/14.
 */
@Controller
@RequestMapping("/user")
public class PageController {


    @RequestMapping("/showRegister")
    public String showRegister(){
        return "register";
    }

    @RequestMapping("/showLogin")
    public String showLogin(@RequestParam(value = "redirect",defaultValue = "http://www.hongde508.top") String redirect, Model model){
        try {
            redirect = new String(redirect.getBytes("iso8859-1"), "utf-8");
            model.addAttribute("redirect", redirect);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "login";
    }
}
