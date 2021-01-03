package com.stylefeng.sso.server.auth.service.impl;

//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.toolkit.IdWorker;
//import com.stylefeng.guns.core.util.MD5Util;
//import com.stylefeng.sso.plugin.model.LoginUser;
//import com.stylefeng.sso.plugin.service.AuthService;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.roses.core.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.stylefeng.sso.server.auth.service.SsoServerAuthService;
import com.stylefeng.sso.server.modular.entity.SysUser;
import com.stylefeng.sso.server.modular.service.AdapterCache;
import com.stylefeng.sso.server.modular.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * auth服务接口最简单实现
 *
 * @author fengshuonan
 * @date 2018-02-03 22:56
 */
@Service
public class SimpleAuthServiceImpl implements SsoServerAuthService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> cache = new AdapterCache();

    private Set<String> ssoClients = new HashSet<>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Integer checkUserLogin(String userName, String password) {

        //查询账号是否存在
        SysUser sysUser = null;
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("ACCOUNT", userName);
        List<SysUser> accounts=sysUserService.list(wrapper);
        if (accounts != null && accounts.size() > 0) {
            sysUser = accounts.get(0);
        } else {
            return null;
        }

        //校验账号密码是否正确
        String md5Hex = MD5Util.encrypt(password + sysUser.getSalt());
        if (md5Hex.equals(sysUser.getPassword())) {
            return sysUser.getUserId();
        } else {
            return null;
        }
    }

    @Override
    public String createToken(Integer userId) {
        String token = IdWorker.get32UUID();
        LoginUser loginUser = this.getLoginUserByUserId(userId);
        loginUser.setRoleIds(StringUtils.isBlank(loginUser.getRoleids())?"":loginUser.getRoleids());
        cache.put(token, loginUser);
        return token;
    }

    @Override
    public boolean checkToken(HttpServletRequest request, String token, String clientAddr) {
        if (cache.containsKey(token)) {
            // 目前不需要记录客户端,20190426
//            recordSSOClient(clientAddr);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public LoginUser getLoginUserByToken(String token) {
        return (LoginUser) cache.get(token);
    }

    @Override
    public LoginUser getLoginUserByUserId(Integer userId) {
        return sysUserService.getUserLoginInfo(userId);
    }

    @Override
    public void recordSSOClient(String clientAddr) {
        ssoClients.add(clientAddr);
    }

    @Override
    public Set<String> getAllSsoClientUrl() {
        return ssoClients;
    }

    @Override
    public Boolean removeCachedToken(String token) {
        try {
            cache.remove(token);
        }catch (Exception e){
            log.warn("退出的登录,清除redis中token失败",e);
            return false;
        }
        return true;
    }
}
