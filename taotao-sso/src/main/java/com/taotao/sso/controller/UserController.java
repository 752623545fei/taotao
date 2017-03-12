package com.taotao.sso.controller;


import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.CheckService;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lyf on 2016/12/12.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    CheckService checkService;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,
                                  @PathVariable Integer type,String callback){
        TaotaoResult result = null;
        if (StringUtils.isBlank(param)) {
            result = TaotaoResult.build(400, "校验内容不能为空");
        }
        if (type == null) {
            result = TaotaoResult.build(400, "校验内容类型不能为空");
        }
        if (type != 1 && type != 2 && type != 3 ) {
            result = TaotaoResult.build(400, "校验内容类型错误");
        }

        if (null != result) {
            if (null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            } else {
                return result;
            }
        }

        result = checkService.checkData(param, type);

        if (null != callback) {
            return getCallback(result, callback);
        } else {
            return result;
        }

    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult creatUser(TbUser user){

        try {
            TaotaoResult result = userService.creatUser(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username, String password){
        try {
            TaotaoResult result = userService.userLogin(request, response, username, password);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable("token") String token, String callback){
        TaotaoResult result;
        System.out.println("token:" + token);
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            result =  TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if (StringUtils.isBlank(callback)){
            return result;
        }else {
            return getCallback(result, callback);
        }
    }

    @RequestMapping("/logout/{token}")
    @ResponseBody
    public Object userLogout(@PathVariable("token") String token, String callback){
        TaotaoResult result = null;

        try {
            result = userService.userLogout(token);
        } catch (Exception e) {
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        if (StringUtils.isBlank(callback)){
            return result;
        }else {
            return getCallback(result, callback);
        }
    }

    //callback方法
    private MappingJacksonValue getCallback(TaotaoResult result, String callback){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }

}
