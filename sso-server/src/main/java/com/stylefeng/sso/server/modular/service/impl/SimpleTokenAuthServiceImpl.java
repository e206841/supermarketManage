package com.stylefeng.sso.server.modular.service.impl;

import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.auth.service.TokenAuthService;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.MenuService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.util.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.stylefeng.sso.server.modular.entity.SysUser;
import com.stylefeng.sso.server.modular.service.AdapterCache;
import com.stylefeng.sso.server.modular.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
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
@Import({UserService.class, MenuService.class})
@Service
public class SimpleTokenAuthServiceImpl implements TokenAuthService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdapterCache cache ;

    private Set<String> ssoClients = new HashSet<>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserService userService;

    @Override
//    @DataSource(name="tianjin_aioc")
    public Integer checkUserLogin(String userName, String password) {

        //查询账号是否存在
//        SysUser sysUser = null;
//        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("ACCOUNT", userName);
//        List<SysUser> accounts = sysUserService.list(queryWrapper);
//        if (accounts != null && accounts.size() > 0) {
//            sysUser = accounts.get(0);
//        } else {
//            return null;
//        }
        User user = userService.getByAccount(userName);

        //校验账号密码是否正确
        String md5Hex = MD5Util.encrypt(password + user.getSalt());
        if (md5Hex.equals(user.getPassword())) {
            return Math.toIntExact(user.getUserId());
        } else {
            return null;
        }
    }

    @Override
    public String createToken(Integer userId) {
        String token = IdWorker.get32UUID();
        LoginUser loginUserByUserId = this.getLoginUserByUserId(userId);
        cache.put(token, loginUserByUserId);
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

        User userInfo = userService.getById(userId);
        LoginUser loginUser=new LoginUser();
        loginUser.setPassword(userInfo.getPassword());
        loginUser.setAccount(userInfo.getAccount());
        loginUser.setAvatar(userInfo.getAvatar());
        loginUser.setDeptId(userInfo.getDeptId());
        loginUser.setEmail(userInfo.getEmail());
        loginUser.setId(userInfo.getUserId());
        loginUser.setName(userInfo.getName());
        return loginUser;

//        return sysUserService.getUserLoginInfo(userId);
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
