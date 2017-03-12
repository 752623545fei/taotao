package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbArea;
import com.taotao.pojo.TbUserAddress;

import java.util.List;

/**
 * Created by lyf on 2016/12/20.
 */
public interface AreaService {
    List<TbArea> getAreaByParent(String parentNum);

    TaotaoResult addAddress(TbUserAddress address);

    TaotaoResult getAddessListByUser(String userId);

    TaotaoResult deleteAddressById(String addressId);
}
