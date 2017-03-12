package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lyf on 2016/12/12.
 */
public interface UserService {

    TaotaoResult creatUser(TbUser user);

    TaotaoResult userLogin(HttpServletRequest request, HttpServletResponse response, String username, String password);

    TaotaoResult getUserByToken(String token);

    TaotaoResult userLogout(String token);
}
