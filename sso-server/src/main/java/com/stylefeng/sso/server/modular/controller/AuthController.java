package com.stylefeng.sso.server.modular.controller;

import cn.stylefeng.guns.base.auth.constants.SsoConstants;
import cn.stylefeng.guns.base.auth.service.TokenAuthService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.stylefeng.sso.server.modular.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 登录验证控制器
 *
 * @author stylefeng
 * @Date 2018/2/3 22:23
 */
@Controller
//@Slf4j
public class AuthController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String LOGIN_TIPS = "tips";

    @Autowired
    TokenAuthService tokenAuthService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) {
//        String userName = request.getParameter("userName");
        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
        request.setAttribute(SsoConstants.REDIRECT_PARAM_NAME,redirectUrl);
        return "/login.html";
    }

    @Value ("${spring.profiles.active}")
    private String profile;

    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse response, Model model) {

        String tokenParam = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("gunsToken")) {
                    tokenParam = cookies[i].getValue();
                    break;
                }
            }
        }

        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
        // 如果cookie中能取到token,则认为从其他页面登录,不继续登录流程,跳回原地址
//        if (StringUtils.isNotBlank(tokenParam) && StringUtils.isNotBlank(redirectUrl)){
//            log.info("用户已经处于登录状态,不继续登录流程,跳回原地址: {}", redirectUrl);
//            try {
//                response.sendRedirect(redirectUrl);
//                return null;
//            } catch (IOException e) {
//                log.warn("已经登录,跳回原地址失败", e);
//                model.addAttribute(LOGIN_TIPS, "网络异常!");
//                return "/login.html";
//            }
//        }

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
//        redirectUrl=StringUtils.isBlank(redirectUrl)? (String) request.getSession().getAttribute(userName+SsoConstants.REDIRECT_PARAM_NAME) :redirectUrl;

        // 登录失败是记录redirectUrl
//        model.addAttribute(SsoConstants.REDIRECT_PARAM_NAME, redirectUrl);
        if (ToolUtil.isEmpty(userName) || ToolUtil.isEmpty(password) || ToolUtil.isEmpty(redirectUrl)) {
            model.addAttribute(LOGIN_TIPS, "请求信息不完整!");
            return "/login.html";
        } else {

            /**
             * 判断用户账号密码是否正确
             */
            Integer userId = tokenAuthService.checkUserLogin(userName, password);
            if (userId != null) {

                //如果账号密码正确,跳转回业务系统的url
                String token = "";
                try {
                /*SysUser sysUser = sysUserService.getSysUser(userId);
                sysUserService.insertLoginUserIntoRedisDto(sysUser, token);*/
                    token = tokenAuthService.createToken(userId);
                } catch (Exception e) {
//                    log.warn("createToken失败",e);
                    model.addAttribute(LOGIN_TIPS, "登录失败,请稍后再试!");
                    log.info(e.getMessage());
                    return "/login.html";
                }

                String domainStrSub=getDomainStr(redirectUrl);

                if (profile.equals("dev")) {
                    Cookie localhost = new Cookie(SsoConstants.TOKEN_PARAM_NAME, token);
                    localhost.setPath("/");
//                    localhost.setDomain("jdcloud.com");
                    localhost.setDomain(domainStrSub);
                    localhost.setMaxAge(36000);
                    response.addCookie(localhost);
                } else {
                    Cookie cookie = new Cookie(SsoConstants.TOKEN_PARAM_NAME, token);
                    cookie.setPath("/");
                    cookie.setDomain(domainStrSub);
                    response.addCookie(cookie);
                }
                request.getSession().setAttribute(SsoConstants.SESSION_LOGIN_FLAG, token);
                try {
                    response.sendRedirect(redirectUrl);
                    return null;
                } catch (IOException e) {
                    model.addAttribute(LOGIN_TIPS, "网络异常!");
                    return "/login.html";
                }
            } else {
                //如果账号密码错误
                model.addAttribute(LOGIN_TIPS, "账号或密码错误!");
                request.setAttribute(SsoConstants.REDIRECT_PARAM_NAME,redirectUrl);
                return "/login.html";
            }
        }
    }

    @ResponseBody
    @RequestMapping ("/hello")
    public String token() {
        return "暂未登录";
    }

    @RequestMapping (SsoConstants.LOGOUT_URL)
//    @CrossOrigin
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        String tokenParam = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("gunsToken")) {
                    tokenParam = cookies[i].getValue();
                    break;
                }
            }
        }
        String redirectUrl = request.getParameter(SsoConstants.REDIRECT_PARAM_NAME);
        if (StringUtils.isNotBlank(tokenParam)){
            // 删除redis中保存的token,如果失败,不允许退出登录,跳回源地址
            if (!tokenAuthService.removeCachedToken(tokenParam)) {
                try {
                    redirectUrl = redirectUrl + "?status="+SsoConstants.LOGIN_FAILED_FLAG;
                    response.sendRedirect(redirectUrl);
                    return null;
                } catch (Exception e) {
//                    log.error("重定向失败", e);
                    return "/404.html";
                }
            }

        }

        String domainStrSub=getDomainStr(redirectUrl);
        // 删除cookie
        // 开发环境为了方便前端本地测试配置域名hosts,cookie选择种到二级域下;线上环境域名一致,cookie种到默认的domain下
        if (profile.equals("dev")) {
            Cookie localhost = new Cookie(SsoConstants.TOKEN_PARAM_NAME, null);
            localhost.setPath("/");
            localhost.setDomain(domainStrSub);
            localhost.setMaxAge(0);
            response.addCookie(localhost);
        } else {
            Cookie newCookie = new Cookie(SsoConstants.TOKEN_PARAM_NAME, null); //假如要删除名称为username的Cookie
            newCookie.setMaxAge(0); //立即删除型
            newCookie.setDomain(domainStrSub);
            newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
            response.addCookie(newCookie); //重新写入，将覆盖之前的
        }

        //跳转到登录页面
        model.addAttribute(SsoConstants.REDIRECT_PARAM_NAME, redirectUrl);
        return "/login.html";
    }

    /**
     * 根据redirectUrl 获取重定向的域名
     * @param redirectUrl
     * @return
     */
    private static String getDomainStr(String redirectUrl){
        int endIndex;
        int httpStartIndex = "http://".length();
        if (!redirectUrl.substring(httpStartIndex).contains(":")){
            //默认端口号，则以第三个/为终点截取
            endIndex = redirectUrl.indexOf("/", httpStartIndex);
        }else {
            endIndex = redirectUrl.indexOf(":",httpStartIndex);
        }
        //从‘http://’之后的第一个位置开始截取
        String domainStrSub = redirectUrl.substring(httpStartIndex, endIndex);
//        System.out.println("domainStrSub："+domainStrSub);
        return domainStrSub;
    }

//    public static void main(String[] args) {
//        getDomainStr("http://mysql-cn-north-1-4fda8775bfcc4f32.public.jcloud.com/sso");
//    }
}