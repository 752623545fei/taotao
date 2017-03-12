package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.ExceptionUtil;
import com.taotao.mapper.TbAreaMapper;
import com.taotao.mapper.TbUserAddressMapper;
import com.taotao.pojo.TbArea;
import com.taotao.pojo.TbAreaExample;
import com.taotao.pojo.TbUserAddress;
import com.taotao.pojo.TbUserAddressExample;
import com.taotao.rest.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/12/20.
 */

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    TbAreaMapper areaMapper;

    @Autowired
    TbUserAddressMapper addressMapper;

    @Override
    public List<TbArea> getAreaByParent(String parentNum) {
        TbAreaExample example = new TbAreaExample();
        example.createCriteria().andParentNoEqualTo(Integer.valueOf(parentNum));
        List<TbArea> areas = areaMapper.selectByExample(example);
        return areas;
    }

    @Override
    public TaotaoResult addAddress(TbUserAddress address) {
        if ("undefined".equals(address.getCountyId())){
            address.setCountyId(null);
        }
        addressMapper.insert(address);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult getAddessListByUser(String userId) {
        TbUserAddressExample example = new TbUserAddressExample();
        example.createCriteria().andUserIdEqualTo(Integer.valueOf(userId));
        List<TbUserAddress> list = addressMapper.selectByExample(example);

        for (TbUserAddress address : list){
            TbAreaExample example1 = new TbAreaExample();
            example1.createCriteria().andAreaNoEqualTo(Integer.valueOf(address.getProvinceId()));
            List<TbArea> area = areaMapper.selectByExample(example1);
            address.setProvinceId(area.get(0).getAreaName());

            TbAreaExample example2 = new TbAreaExample();
            example2.createCriteria().andAreaNoEqualTo(Integer.valueOf(address.getCityId()));
            area = areaMapper.selectByExample(example2);
            address.setCityId(area.get(0).getAreaName());

            if(address.getCountyId()!=null && !"".equals(address.getCountyId())){
                TbAreaExample example3 = new TbAreaExample();
                example3.createCriteria().andAreaNoEqualTo(Integer.valueOf(address.getCountyId()));
                area = areaMapper.selectByExample(example3);
                address.setCountyId(area.get(0).getAreaName());
            }else {
                address.setCountyId("");
            }
        }
        if (list!=null && list.size()>0){
            return TaotaoResult.ok(list);
        }
        return TaotaoResult.ok(new ArrayList<>());
    }

    @Override
    public TaotaoResult deleteAddressById(String addressId) {
        try {
            addressMapper.deleteByPrimaryKey(Integer.valueOf(addressId));
            return TaotaoResult.ok();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
