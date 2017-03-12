package com.taotao.portal.service;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.Order;

import java.util.List;

/**
 * Created by lyf on 2016/12/19.
 */
public interface OrderService {
    String createOrder(Order order);

    List<Order> queryOrderList(TbUser user);
}
