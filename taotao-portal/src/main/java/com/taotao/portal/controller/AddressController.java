package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserAddress;
import com.taotao.portal.service.AddressService;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lyf on 2016/12/21.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String showAddAddressPage(){
        return "commons/addAddress";
    }

    @RequestMapping("/add/pop")
    @ResponseBody
    public ModelAndView showPopAddAddress(ModelAndView modelAndView){
        modelAndView.setViewName("commons/addAddress");
        return modelAndView;
    }

    @RequestMapping(value = "/select", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAreaName(@RequestParam(defaultValue = "0") String parent){
        try {
            Map area = addressService.getArea(parent);
            return JsonUtils.objectToJson(area);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/addAddress",method = RequestMethod.POST)
    @ResponseBody
    public String addAddress(TbUserAddress addressInfoParam, HttpServletRequest request){
        try {
            String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
            TbUser user = userService.getUserByToken(token);
            String id = user.getId() + "";
            addressInfoParam.setUserId(Integer.valueOf(id));
            TaotaoResult result = addressService.addAddress(addressInfoParam);
            return JsonUtils.objectToJson(result);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return JsonUtils.objectToJson(TaotaoResult.build(500, "error"));
        }
    }

    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    @ResponseBody
    public String deleteAddress(@RequestParam("addressId")String addressId){
        TaotaoResult result = addressService.deletAddress(addressId);
        return JsonUtils.objectToJson(result);
    }
}
