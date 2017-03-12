package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyf on 2016/12/11.
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemMapper itemMapper;

    @Autowired
    TbItemDescMapper itemDescMapper;

    @Autowired
    TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ITEM_INFO_REDIS_KEY}")
    String ITEM_INFO_REDIS_KEY;

    @Value("${ITEM_INFO_REDIS_EXPIRE}")
    Integer ITEM_INFO_REDIS_EXPIRE;



    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {


        try {
            String itemJson = jedisClient.get(ITEM_INFO_REDIS_KEY + ":" + itemId + ":base");
            if (!StringUtils.isBlank(itemJson)){
                TbItem item = JsonUtils.jsonToPojo(itemJson, TbItem.class);
                return TaotaoResult.ok(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        try {
            String json = JsonUtils.objectToJson(item);
            jedisClient.set(ITEM_INFO_REDIS_KEY + ":" + itemId + ":base", json);
            jedisClient.expire(ITEM_INFO_REDIS_KEY + ":" + itemId + ":base",ITEM_INFO_REDIS_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }

        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemDescInfo(long itemId) {
        try {
            String itemJson = jedisClient.get(ITEM_INFO_REDIS_KEY + ":" + itemId + ":desc");
            if (!StringUtils.isBlank(itemJson)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(itemJson, TbItemDesc.class);
                if (!StringUtils.isBlank(itemDesc.getItemDesc())){
                    return TaotaoResult.ok(itemDesc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        try {
            String json = JsonUtils.objectToJson(itemDesc);
            jedisClient.set(ITEM_INFO_REDIS_KEY + ":" + itemId + ":desc", json);
            jedisClient.expire(ITEM_INFO_REDIS_KEY + ":" + itemId + ":desc",ITEM_INFO_REDIS_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult getItemParamInfo(long itemId) {

        try {
            String itemJson = jedisClient.get(ITEM_INFO_REDIS_KEY + ":" + itemId + ":param");
            if (!StringUtils.isBlank(itemJson)){
                TbItemParamItem paramItem = JsonUtils.jsonToPojo(itemJson, TbItemParamItem.class);
                return TaotaoResult.ok(paramItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list!=null && list.size()>0){
            TbItemParamItem paramItem = list.get(0);
            try {
                String json = JsonUtils.objectToJson(paramItem);
                jedisClient.set(ITEM_INFO_REDIS_KEY + ":" + itemId + ":param", json);
                jedisClient.expire(ITEM_INFO_REDIS_KEY + ":" + itemId + ":param",ITEM_INFO_REDIS_EXPIRE);
            }catch (Exception e){
                e.printStackTrace();
            }
            return TaotaoResult.ok(paramItem);
        }

        return TaotaoResult.build(400, "无此商品信息");
    }
}
