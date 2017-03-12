package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.common.util.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/12/14.
 */

@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_BASE_URL}")
    String REST_BASE_URL;

    @Value("${ITEM_INFO_URL}")
    String ITEM_INFO_URL;

    @Override
    public TaotaoResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {

        CartItem cartItem = null;
        List<CartItem> cartList = getCartItemList(request);
        for (int i=0; i<cartList.size(); i++){
            if (cartList.get(i).getId() == itemId){
                cartItem = cartList.get(i);
                cartItem.setNum(cartList.get(i).getNum() + num);
                cartList.remove(i);
                break;
            }
        }

        if (cartItem == null) {
            cartItem = new CartItem();
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
            if (result.getStatus() == 200) {
                TbItem item = (TbItem) result.getData();

                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setNum(num);
                cartItem.setPrice(item.getPrice());
                cartItem.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
            }

        }
        cartList.add(cartItem);
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList), true);

        return TaotaoResult.ok();
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
    public List<CartItem> getCartItemList(HttpServletRequest request, String item) {
        String cartJson = CookieUtils.getCookieValue(request, "TT_CART", true);
        if (cartJson == null){
            return new ArrayList<>();
        }
        try {
            String[] items = item.split(",");
            List<CartItem> cartItems = JsonUtils.jsonToList(cartJson, CartItem.class);
            List<CartItem> newCartItems = new ArrayList<>();
            for (CartItem cartItem : cartItems){
                for (int i=0; i<items.length; i++){
                    String id = cartItem.getId() + "";
                    if (id.equals(items[i])){
                        newCartItems.add(cartItem);
                    }
                }
            }
            return newCartItems;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public TaotaoResult updateCartNum(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        String cart = CookieUtils.getCookieValue(request, "TT_CART", true);
        List<CartItem> cartList = JsonUtils.jsonToList(cart, CartItem.class);
        CartItem cartItem = new CartItem();
        for (int i=0; i<cartList.size(); i++){
            if (cartList.get(i).getId() == itemId){
                cartItem = cartList.get(i);
                cartItem.setNum(num);
                cartList.remove(i);
                break;
            }
        }
        cartList.add(cartItem);
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList), true);

        return TaotaoResult.ok();

    }

    @Override
    public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        String cart = CookieUtils.getCookieValue(request, "TT_CART", true);
        List<CartItem> cartList = JsonUtils.jsonToList(cart, CartItem.class);

        for (int i=0; i<cartList.size(); i++){
            if (cartList.get(i).getId() == itemId){
                cartList.remove(i);
                break;
            }
        }
        CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList), true);

        return TaotaoResult.ok();
    }
}
