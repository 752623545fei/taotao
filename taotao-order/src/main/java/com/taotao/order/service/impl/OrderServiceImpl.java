package com.taotao.order.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.pojo.CartItem;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by lyf on 2016/12/19.
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    TbOrderMapper orderMapper;

    @Autowired
    TbOrderItemMapper orderItemMapper;

    @Autowired
    TbOrderShippingMapper orderShippingMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    String ORDER_GEN_KEY;

    @Value("${ORDER_INIT_ID}")
    String ORDER_INIT_ID;

    @Value("${ORDER_DETAIL_GEN_KEY}")
    String ORDER_DETAIL_GEN_KEY;
    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        String key = jedisClient.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(key)){
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);

        order.setOrderId(orderId + "");
        order.setPostFee("0");
        order.setStatus(1);
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);


        order.setBuyerRate(0);
        //0：未评价 1：已评价
        orderMapper.insert(order);

        for (TbOrderItem orderItem : itemList){
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            orderItem.setId(orderDetailId + "");
            orderItem.setOrderId(orderId + "");
            orderItemMapper.insert(orderItem);
        }

        orderShipping.setOrderId(orderId + "");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId);

    }



    @Override
    public List<CartItem> getCartItemList(HttpServletRequest request) {
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null){
            return new ArrayList<>();
        }
        try {
            List<CartItem> cartItems = JsonUtils.jsonToList(cartJson, CartItem.class);
            return cartItems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Order> getOrderListByUser(String userId) {
        TbOrderExample example1 = new TbOrderExample();
        example1.createCriteria().andUserIdEqualTo(Long.valueOf(userId));

        List<TbOrder> tbOrders =  orderMapper.selectByExample(example1);
        List<Order> orders = new ArrayList<>();

        for (TbOrder tbOrder : tbOrders){

            String orderId = tbOrder.getOrderId();
            TbOrderItemExample example2 = new TbOrderItemExample();
            example2.createCriteria().andOrderIdEqualTo(orderId);
            List<TbOrderItem> orderItems = orderItemMapper.selectByExample(example2);
            TbOrderShipping shipping = orderShippingMapper.selectByPrimaryKey(orderId);
            Order order = setOrderFromFather(tbOrder);
            order.setOrderItems(orderItems);
            order.setOrderShipping(shipping);
            orders.add(order);
        }
        Collections.reverse(orders);
        return orders;
    }


    private Order setOrderFromFather(TbOrder tbOrder){
        Order order = new Order();
        order.setOrderId(tbOrder.getOrderId());
        order.setPayment(tbOrder.getPayment());
        order.setPaymentType(tbOrder.getPaymentType());
        order.setPostFee(tbOrder.getPostFee());
        order.setStatus(tbOrder.getStatus());
        order.setCreateTime(tbOrder.getCreateTime());
        order.setUpdateTime(tbOrder.getUpdateTime());
        order.setPaymentTime(tbOrder.getPaymentTime());
        order.setConsignTime(tbOrder.getConsignTime());
        order.setEndTime(tbOrder.getEndTime());
        order.setCloseTime(tbOrder.getCloseTime());
        order.setShippingName(tbOrder.getShippingName());
        order.setShippingCode(tbOrder.getShippingCode());
        order.setBuyerMessage(tbOrder.getBuyerMessage());
        order.setBuyerNick(tbOrder.getBuyerNick());
        order.setBuyerRate(tbOrder.getBuyerRate());
        SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日 HH时mm分");
        order.setTime(sdf.format(order.getCreateTime()));
        return order;
    }
}
