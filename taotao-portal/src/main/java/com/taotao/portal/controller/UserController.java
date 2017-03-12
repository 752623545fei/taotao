package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserAddress;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.AddressService;
import com.taotao.portal.service.ContentService;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by lyf on 2016/12/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ContentService contentService;

    @Autowired
    OrderService orderService;

    @Autowired
    AddressService addressService;

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, Model model){
        try {
            String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
            TaotaoResult result = userService.logoutByToken(token);

            return "redirect:http://www.hongde508.top";
        } catch (Exception e) {
            e.printStackTrace();
            String json = contentService.getContentList();
            model.addAttribute("ad1",json);
            return "redirect:http://www.hongde508.top";
        }
    }

    @RequestMapping("/myinfo")
    public String showMyInfo(HttpServletRequest request, Model model){
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        model.addAttribute("user", user);
        return "my-info";
    }

    @RequestMapping("/myhome")
    public String showHome(HttpServletRequest request, Model model){
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        List<Order> orders = orderService.queryOrderList(user);

        model.addAttribute("orderList", orders);
        return "my-home";
    }

    @RequestMapping("/myinfo/img")
    public String showMyInfoImg(){
        return "my-info-img";
    }

    @RequestMapping("/myinfo/more")
    public String showMyInfoMore(){
        return "my-info-more";
    }

    @RequestMapping("/myoreder/comment")
    public String showMyOrderComment(){
        return "my-order-comment";
    }

    @RequestMapping("/myaddress")
    public String showMyAddress(HttpServletRequest request, Model model){
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        List<TbUserAddress> list = addressService.selectAddressByUser(user);
        model.addAttribute("addressList", list);
        model.addAttribute("listSize", list.size());
        return "my-address";
    }





}
