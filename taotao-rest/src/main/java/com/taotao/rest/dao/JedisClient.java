package com.taotao.rest.dao;

/**
 * Created by lyf on 2016/12/9.
 */
public interface JedisClient {
    String set(String key, String value);
    String get(String key);
    Long hset(String hkey, String key, String value);
    String hget(String hkey, String key);
    long incr(String key);
    long expire(String key, int second);
    long ttl(String key);
    long del(String key);
    long hdel(String hkey, String key);

}
