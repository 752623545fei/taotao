package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lyf on 2016/12/19.
 */

@Service
public class UserServiceImpl implements UserService {

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;

    @Value("${SSO_USER_TOKEN}")
    String SSO_USER_TOKEN;

    @Value("${SSO_PAGE_LOGIN}")
    public String SSO_PAGE_LOGIN;

    @Value("${SSO_LOGOUT_URL}")
    String SSO_LOGOUT_URL;

    @Override
    public TbUser getUserByToken(String token) {
        try {
            String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
            if (result.getStatus() == 200){
                TbUser user = (TbUser) result.getData();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TaotaoResult logoutByToken(String token) {
        String result = HttpClientUtil.doGet(SSO_BASE_URL + SSO_LOGOUT_URL + token);
        TaotaoResult taotaoResult = TaotaoResult.format(result);
        if (taotaoResult.getStatus() == 200){
            return TaotaoResult.ok();
        }
        return null;
    }
}
