package com.taotao.rest.service.impl;

import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lyf on 2016/12/6.
 */

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    TbItemCatMapper itemCatMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${INDEX_ITEMCAT_REDIS_KEY}")
    String INDEX_ITEMCAT_REDIS_KEY;

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();

        long start = new Date().getTime();
        //redis
        try {
            String s = jedisClient.get(INDEX_ITEMCAT_REDIS_KEY);
            if(!StringUtils.isBlank(s)) {
                CatResult result = JsonUtils.jsonToPojo(s, CatResult.class);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long redisEnd = new Date().getTime();

        System.out.println("redis:" + (redisEnd-start));
        catResult.setData(getCatList(0L));
        long end = new Date().getTime();
        System.out.println("mysql:" + (end-redisEnd));
        try {
            String cahce = JsonUtils.objectToJson(catResult);
            jedisClient.set(INDEX_ITEMCAT_REDIS_KEY,cahce);
        }catch (Exception e){
            e.printStackTrace();
        }

        return catResult;
    }

    private List<?> getCatList(Long parentId){
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        int count = 0;
        List result = new ArrayList<>();
        for (TbItemCat itemCat : list){
            CatNode catNode = new CatNode();
            if (itemCat.getIsParent()){
                if (itemCat.getParentId() == 0){
                    catNode.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
                }else {
                    catNode.setName(itemCat.getName());
                }
                catNode.setUrl("/products/"+itemCat.getId()+".html");
                catNode.setItem(getCatList(itemCat.getId()));

                result.add(catNode);
                count++;
                if (count >= 14 && itemCat.getParentId() == 0){
                    break;
                }
            }else {
                result.add("/products/"+itemCat.getId()+".html|" + itemCat.getName());
            }

        }
        return result;
    }
}
