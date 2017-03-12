package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.CartItem;
import com.taotao.order.pojo.Order;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lyf on 2016/12/16.
 */
public interface OrderService {

    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);

    List<CartItem> getCartItemList(HttpServletRequest request);

    List<Order> getOrderListByUser(String userId);

}
