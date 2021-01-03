package cn.stylefeng.guns.config.interceptor;

import cn.stylefeng.guns.base.auth.api.AuthApi;
import cn.stylefeng.guns.base.auth.cache.ClientCache;
import cn.stylefeng.guns.base.auth.constants.SsoConstants;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.auth.properties.SsoProperties;
import cn.stylefeng.guns.base.auth.util.HttpUtil;
import cn.stylefeng.guns.remote.RemoteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * sso客户端登录拦截器
 *
 * @author fengshuonan
 * @date 2018-02-03 20:35
 */
public class SsoClientInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private SsoProperties ssoProperties;

    private ClientCache clientCache;

    private RemoteService remoteService;

    private AuthApi authApi;

    public SsoClientInterceptor(SsoProperties ssoProperties, RemoteService remoteService,
                                ClientCache clientCache, AuthApi authApi) {
        this.ssoProperties = ssoProperties;
        this.clientCache = clientCache;
        this.remoteService = remoteService;
        this.authApi = authApi;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tokenParam = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(SsoConstants.TOKEN_PARAM_NAME)) {
                    tokenParam = cookies[i].getValue();
                    break;
                }
            }
        }

        //如果带有token参数,调用远程sso sever方法验证token是否正确
        if (StringUtils.isNotBlank(tokenParam)) {
            Integer userId = remoteService.validateToken(tokenParam, HttpUtil.getRequestContextPath(request));
            //userId不为空，token验证成功
            if (userId != null) {
                request.setAttribute(SsoConstants.SESSION_LOGIN_FLAG, tokenParam);
                LoginUser loginUser = authApi.getLoginUser(userId);
                request.setAttribute(SsoConstants.LOGIN_USER_SESSION, loginUser);
                //登录接口会根据session中是否存有改用户的标记来判断
                request.getSession().setAttribute(SsoConstants.SESSION_LOGIN_FLAG,tokenParam);
                request.getSession().setAttribute(SsoConstants.LOGIN_USER_SESSION,loginUser);
                return true;
            } else {
                //token验证失败
                redirectSsoServer(request, response);
                return false;
            }

        } else {
            //没有携带token参数
            redirectSsoServer(request, response);
            return false;
        }
    }

    /**
     * 跳转到sso服务器去认证
     */
    private void redirectSsoServer(HttpServletRequest request, HttpServletResponse response) {
        String redirectUrl = ssoProperties.getServerUrl() + "?" + SsoConstants.REDIRECT_PARAM_NAME + "=" + HttpUtil.encodeUrl(HttpUtil.getRequestFullPathNoParam(request));
        try {
            response.sendRedirect(redirectUrl);
        } catch (IOException e) {
            log.error("跳转到服务器出错!", e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
