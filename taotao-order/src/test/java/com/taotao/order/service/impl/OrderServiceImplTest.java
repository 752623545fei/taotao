package com.taotao.order.service.impl;

import com.taotao.common.pojo.Location;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lyf on 2016/12/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-jedis.xml"})
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Test
    public void getOrderListByUser() throws Exception {
        List<Order> list = orderService.getOrderListByUser("38");
        System.out.println(list.size());
    }

    @Test
    public void getLocation(){
        String url = "http://easybuy.jd.com/address/getProvinces.action";
        String s = HttpClientUtil.doGet(url);
        List<Location> locations = JsonUtils.jsonToList(s, Location.class);
        System.out.println(locations.size());
    }



}