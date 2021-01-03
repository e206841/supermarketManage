package com.stylefeng.sso.server.modular.service;

import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.auth.service.AuthService;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
//import com.stylefeng.sso.plugin.model.LoginUser;
import com.stylefeng.sso.server.modular.dto.UserDto;
import com.stylefeng.sso.server.modular.entity.SysUser;
import com.stylefeng.sso.server.modular.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-08-29
 */
@Service
@DependsOn("springContextHolder")
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AuthService authService;

    /**
     * 根据用户id获取用户信息
     */
//    @DataSource(name = "tianjin_aioc")
    public LoginUser getUserLoginInfo(Integer userId) {

        SysUser sysUser = baseMapper.selectById(userId);

        if (sysUser == null) {
            return null;
        }

        LoginUser loginUser = authService.user(sysUser.getAccount());
        return loginUser;
    }

    public SysUser getSysUser(Integer userId) {

        SysUser sysUser = baseMapper.selectById(userId);

        if (sysUser == null) {
            return null;
        }
        return sysUser;
    }



/*    public void insertLoginUserIntoRedisDto(SysUser sysUser,String token){
        redisTemplate.opsForHash().put("sso:userDto:", token, JSONObject.toJSONString(UserDto.from(sysUser)));
    }*/


    public UserDto findDtoByToken(String token){
        Object dtoJson = redisTemplate.opsForHash().get("sso:userDto:", token);
        if (dtoJson==null) {
            return null;
        }
        return JSON.parseObject(dtoJson.toString(), UserDto.class);
    }

}
