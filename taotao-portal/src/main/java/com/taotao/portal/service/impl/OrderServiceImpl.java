package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/12/19.
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    String ORDER_BASE_URL;

    @Value("${ORDER_CREATE_URL}")
    String ORDER_CREATE_URL;

    @Value("${ORDER_SELECT_URL}")
    String ORDER_SELECT_URL;

    @Override
    public String createOrder(Order order) {
        String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
        TaotaoResult result = TaotaoResult.format(json);
        if (result.getStatus() == 200){
            Integer orderId = (Integer) result.getData();
            return orderId.toString();
        }
        return "";
    }

    @Override
    public List<Order> queryOrderList(TbUser user) {
        String s = HttpClientUtil.doGet(ORDER_BASE_URL + ORDER_SELECT_URL + user.getId());
        if (!StringUtils.isBlank(s)){
            TaotaoResult result = TaotaoResult.formatToList(s, Order.class);
            List<Order> data = (List<Order>) result.getData();
            return data;
        }
        return new ArrayList<>();
    }
}
