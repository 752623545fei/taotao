package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserAddress;

import java.util.List;
import java.util.Map;

/**
 * Created by lyf on 2016/12/21.
 */
public interface AddressService {

    Map getArea(String parent);

    TaotaoResult addAddress(TbUserAddress address);

    List<TbUserAddress> selectAddressByUser(TbUser user) ;

    TaotaoResult deletAddress(String addressId);
}
