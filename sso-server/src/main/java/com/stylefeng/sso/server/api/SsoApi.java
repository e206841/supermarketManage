package com.stylefeng.sso.server.api;

import cn.stylefeng.guns.base.auth.cache.ClientCache;
import cn.stylefeng.guns.base.auth.constants.SsoConstants;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.auth.model.SsoResponse;
import cn.stylefeng.guns.base.auth.model.enums.ResponseStatus;
import cn.stylefeng.guns.base.auth.service.AuthService;
import cn.stylefeng.guns.base.auth.service.TokenAuthService;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import com.stylefeng.sso.server.common.SsoSpringContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 单点登录token校验API
 *
 * @author fengshuonan
 * @date 2018-02-04 11:56
 */
@RestController
public class SsoApi {

    /**
     * 验证token是否正确
     */
    @RequestMapping(SsoConstants.AUTH_TOKEN_URL)
    @ResponseBody
    public SsoResponse authToken(HttpServletRequest request) {
        TokenAuthService tokenAuthService = SpringContextHolder.getBean(TokenAuthService.class);
        String token = request.getParameter(SsoConstants.TOKEN_PARAM_NAME);
        String clientAddr = request.getParameter(SsoConstants.CLIENT_REQUEST_ADDR_PARAM_NAME);

        boolean flag = tokenAuthService.checkToken(request, token, clientAddr);

        if (flag) {
            LoginUser loginUser = tokenAuthService.getLoginUserByToken(token);
            SsoResponse ss = SsoResponse.success(Math.toIntExact(loginUser.getId()));
//            ss.getData();
            return ss;
        } else {
            return new SsoResponse(ResponseStatus.WRONG_TOKEN);
        }
    }

    /**
     * 清除客户端的token
     */
    @RequestMapping(SsoConstants.CLEAR_TOKEN_URL)
    public SsoResponse clearToken(HttpServletRequest request) {
        String token = request.getParameter(SsoConstants.TOKEN_PARAM_NAME);
        ClientCache clientCache = SsoSpringContextHolder.getBean(ClientCache.class);
        clientCache.addInvalidKey(token);
        return SsoResponse.success();
    }
}
