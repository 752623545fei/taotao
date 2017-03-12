package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.pojo.TbArea;
import com.taotao.pojo.TbUserAddress;
import com.taotao.rest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lyf on 2016/12/20.
 */
@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    AreaService areaService;

    @RequestMapping("/select")
    @ResponseBody
    public TaotaoResult getArea(@RequestParam(defaultValue = "0")String parent){
        try {
            List<TbArea> list = areaService.getAreaByParent(parent);
            return TaotaoResult.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public TaotaoResult addAddress(@RequestBody TbUserAddress address){
        TaotaoResult result = areaService.addAddress(address);
        return result;
    }

    @RequestMapping(value = "/select/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public TaotaoResult selectUserAddress(@PathVariable String userId){
        TaotaoResult result = areaService.getAddessListByUser(userId);
        return result;
    }

    @RequestMapping(value = "/delete/address/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public TaotaoResult deleteAddress(@PathVariable String addressId){
        TaotaoResult result = areaService.deleteAddressById(addressId);
        return result;
    }

}
