package com.stylefeng.sso.server.modular.service;

import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.sso.server.common.SsoSpringContextHolder;
import com.stylefeng.sso.server.modular.dto.UserDto;
import com.stylefeng.sso.server.modular.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * creat by han on 2019/4/9
 */
@Component
@DependsOn("springContextHolder")
public class AdapterCache<K,V> extends ConcurrentHashMap<K,V> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public boolean containsKey(Object key){
        LoginUser s = (LoginUser)this.get(key);
        if(s!=null){
            return true;
        }
        return false;
    }

    @Override
    public V put(K token, V loginUser) {
        UserDto userDto = UserDto.from((LoginUser) loginUser);
        String s = JSONObject.toJSONString(userDto);
        redisTemplate.opsForHash().put("sso:userDto:", token, s);
//        gerRedisTemplate().opsForHash().put("sso:userDto:", token, JSONObject.toJSONString(UserDto.from((LoginUser)loginUser)));
        return loginUser;
    }

    @Override
    public V get(Object token) {
        Object dtoJson = gerRedisTemplate().opsForHash().get("sso:userDto:", token);
        if (dtoJson==null) {
            return null;
        }
        return (V)JSON.parseObject(dtoJson.toString(), LoginUser.class);
    }

    @Override
    public V remove(Object token){
        gerRedisTemplate().opsForHash().delete("sso:userDto:", token);
        return null;
    }



    private StringRedisTemplate gerRedisTemplate(){
        return SpringContextHolder.getBean(StringRedisTemplate.class);
//        return SsoSpringContextHolder.getBean(StringRedisTemplate.class);
    }


}
