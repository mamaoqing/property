package com.shige.proper.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author mq
 * @description: TODO
 * @title: RedisUtil
 * @projectName proper
 * @date 2021/1/2811:31
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //设置key-value
    public void set(String key,Object value){
        String valueString = JSON.toJSONString(value);
        redisTemplate.opsForValue().set(key,valueString);
    }

    //设置key-value 加时长
    public void set(String key,Object value,int seconds){
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    //设置key-value 加时长
    public void set(String key,Object value,int seconds,TimeUnit tm){
        redisTemplate.opsForValue().set(key,value,seconds, tm);
    }

    //获取value
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //获取value转对象
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    public void expire(String key,int seconds) {
        redisTemplate.expire(key,seconds,TimeUnit.SECONDS);
    }
}
