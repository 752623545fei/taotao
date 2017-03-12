package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbArea;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserAddress;
import com.taotao.portal.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyf on 2016/12/21.
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Value("${AREA_SELECT_URL}")
    String AREA_SELECT_URL;

    @Value("${REST_BASE_URL}")
    String REST_BASE_URL;

    @Value("${ADDRESS_ADD_URL}")
    String ADDRESS_ADD_URL;

    @Value("${ADDRESS_GET_URL}")
    String ADDRESS_GET_URL;

    @Value("${ADDRESS_DELETE_URL}")
    String ADDRESS_DELETE_URL;

    @Override
    public Map getArea(String parent) {
        String s = HttpClientUtil.doGet(REST_BASE_URL + AREA_SELECT_URL + "?parent=" + parent);
        TaotaoResult result = TaotaoResult.formatToList(s, TbArea.class);
        List<TbArea> data = (List<TbArea>) result.getData();
        Map<Integer, String> map = new HashMap<>();
        for (TbArea area : data){
            map.put(area.getAreaNo(),area.getAreaName());
        }
        return map;
    }

    @Override
    public TaotaoResult addAddress(TbUserAddress address) {
        String json = JsonUtils.objectToJson(address);
        String s = HttpClientUtil.doPostJson(REST_BASE_URL + ADDRESS_ADD_URL, json);
        TaotaoResult result = TaotaoResult.format(s);
        return result;
    }

    @Override
    public List<TbUserAddress> selectAddressByUser(TbUser user) {
        String s = HttpClientUtil.doGet(REST_BASE_URL + ADDRESS_GET_URL + user.getId());
        if (!StringUtils.isBlank(s)){
            TaotaoResult result = TaotaoResult.formatToList(s, TbUserAddress.class);
            List<TbUserAddress> list = (List<TbUserAddress>) result.getData();
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public TaotaoResult deletAddress(String addressId) {
        String s = HttpClientUtil.doPost(REST_BASE_URL + ADDRESS_DELETE_URL + addressId);
        if (!StringUtils.isBlank(s)){
            TaotaoResult result = TaotaoResult.format(s);
            return result;
        }
        return TaotaoResult.build(500, "error");
    }
}
