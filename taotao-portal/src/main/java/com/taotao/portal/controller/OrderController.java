package com.taotao.portal.controller;

import com.taotao.common.util.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserAddress;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.AddressService;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lyf on 2016/12/19.
 */

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @RequestMapping("/order-cart")
    public String showOrderCart(@RequestParam("items")String items, HttpServletRequest request, Model model){
        List<CartItem> list = cartService.getCartItemList(request, items);
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        List<TbUserAddress> address = addressService.selectAddressByUser(user);
        model.addAttribute("cartList", list);
        model.addAttribute("address", address);
        return "order-cart";
    }

    @RequestMapping("/create")
    public String creatOrder(Order order, Model model){
        try {
            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId", orderId);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "创建订单出错，请重试！");
            return "/error/exception";
        }
    }

    @RequestMapping("/myorders")
    public String showMyOrders(HttpServletRequest request, Model model){
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        TbUser user = userService.getUserByToken(token);
        List<Order> orders = orderService.queryOrderList(user);

        model.addAttribute("orderList", orders);
        return "my-orders";
    }
}
