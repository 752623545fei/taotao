package com.taotao.rest.service.impl;

import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbArea;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.AreaService;
import com.taotao.rest.service.ItemCatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyf on 2016/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-jedis.xml"})
public class ItemCatServiceImplTest {

    @Resource
    ItemCatService itemCatService;

    @Autowired
    AreaService areaService;

    @Test
    public void testCatService(){
        CatResult itemCatList = itemCatService.getItemCatList();
        String json = JsonUtils.objectToJson(itemCatList);
        System.out.println(json);
    }

    @Test
    public void testArea(){
        List<TbArea> areaByParent = areaService.getAreaByParent("0");
        System.out.println(areaByParent);
    }

}