package com.taotao.sso.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.util.CookieUtils;
import com.taotao.common.util.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lyf on 2016/12/12.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserMapper userMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${MD5_SALT}")
    String MD5_SALT;

    @Value("${REDIS_USER_SESSION_KEY}")
    String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    Integer SSO_SESSION_EXPIRE;

    @Override
    public TaotaoResult creatUser(TbUser user) {
        user.setUpdated(new Date());
        user.setCreated(new Date());
        String md5 = DigestUtils.md5DigestAsHex((user.getPassword() + MD5_SALT).getBytes());
        user.setPassword(md5);
        userMapper.insert(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult userLogin(HttpServletRequest request, HttpServletResponse response,
                                  String username, String password) {

        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<TbUser> users = userMapper.selectByExample(example);
        if (users==null || users.size()==0){
            return TaotaoResult.build(400,"用户名不存在");
        }
        TbUser user = users.get(0);
        String md5 = DigestUtils.md5DigestAsHex((password + MD5_SALT).getBytes());
        if (!user.getPassword().equals(md5)){
            return TaotaoResult.build(400,"用户名或密码错误");
        }

        String token = UUID.randomUUID().toString();
        user.setPassword(null);

        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

        CookieUtils.setCookie(request, response, "TT_TOKEN", token);

        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(json)){
            return TaotaoResult.build(400, "过期");
        }

        jedisClient.expire(REDIS_USER_SESSION_KEY + ":"+ token, SSO_SESSION_EXPIRE);
        return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
    }

    @Override
    public TaotaoResult userLogout(String token) {
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        if (StringUtils.isBlank(json)){
            return TaotaoResult.build(400, "不存在");
        }
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        return TaotaoResult.ok();
    }
}
