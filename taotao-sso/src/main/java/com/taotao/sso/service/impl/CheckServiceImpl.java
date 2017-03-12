package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyf on 2016/12/12.
 */

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    TbUserMapper userMapper;

    @Override
    public TaotaoResult checkData(String data, Integer type) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (type == 1){
            //username
            criteria.andUsernameEqualTo(data);
        }else if (type == 2){
            //phone
            criteria.andPhoneEqualTo(data);
        }else {
            //email
            criteria.andEmailEqualTo(data);
        }
        List<TbUser> users = userMapper.selectByExample(example);
        if (users==null || users.size()==0){
            return TaotaoResult.ok(true);
        }
        return TaotaoResult.ok(false);
    }
}
