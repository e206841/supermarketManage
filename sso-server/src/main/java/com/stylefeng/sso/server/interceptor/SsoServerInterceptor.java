package com.stylefeng.sso.server.interceptor;

import cn.stylefeng.guns.base.auth.constants.SsoConstants;
import cn.stylefeng.guns.base.auth.properties.SsoProperties;
//import com.stylefeng.sso.plugin.constants.SsoConstants;
//import com.stylefeng.sso.plugin.properties.SsoProperties;
//import com.stylefeng.sso.plugin.service.AuthService;
//import com.stylefeng.sso.plugin.util.SsoSpringContextHolder;
import com.stylefeng.sso.server.auth.service.SsoServerAuthService;
import com.stylefeng.sso.server.common.SsoSpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
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
public class SsoServerInterceptor implements HandlerInterceptor {

    SsoProperties ssoProperties;

    public SsoServerInterceptor(SsoProperties ssoProperties) {
        this.ssoProperties = ssoProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tokenParam = null;
        String ss = request.getHeader("cookie");
        Cookie[] cookies = request.getCookies();


        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("gunsToken")) {
                    tokenParam = cookies[i].getValue();
                    break;
                }
            }
        }

        //携带token的
        String tokenParameter = request.getParameter(SsoConstants.TOKEN_PARAM_NAME);
        if (request.getRequestURL().indexOf(SsoConstants.AUTH_TOKEN_URL)>0 && StringUtils.isNotBlank(tokenParameter) ){
            return verifyTrue(request,response);
        } else
        if (StringUtils.isBlank(tokenParam)) {
             //如果没登录,跳转到登录页面
            return verifyFalse(request, response);
        } else {
            SsoServerAuthService authService = SsoSpringContextHolder.getBean(SsoServerAuthService.class);
            if (authService.checkToken(request,tokenParam,"")) {
                return verifyTrue(request, response);
            } else {
                return verifyFalse(request, response);
            }
        }

    }

    private boolean verifyTrue(HttpServletRequest request, HttpServletResponse response) {
        //当前用户已经登录,通过拦截器
        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
        try {
            response.sendRedirect(redirectUrl /*+ "?" + SsoConstants.TOKEN_PARAM_NAME + "=" + sessionAttribute.toString()*/);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean verifyFalse(HttpServletRequest request, HttpServletResponse response) {
        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
        request.setAttribute(SsoConstants.REDIRECT_PARAM_NAME, redirectUrl);
        try {
            request.getRequestDispatcher("/login").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
