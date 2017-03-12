package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * Created by lyf on 2016/12/19.
 */
public interface UserService {
    TbUser getUserByToken(String token);
    TaotaoResult logoutByToken(String token);
}
