package com.stylefeng.sso.server.auth.model;

//import com.stylefeng.sso.plugin.model.enums.ResponseStatus;

//import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.auth.model.enums.ResponseStatus;

/**
 * sso服务返回结果封装
 *
 * @author fengshuonan
 * @date 2018-02-04 11:58
 */
public class SsoResponse<T> {

    /**
     * 状态码(200成功,其他错误)
     */
    private Integer code;

    /**
     * 用户id
     */
    private Integer userId;

    private T data;

    public T getData() {
        return data;
    }

    public SsoResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 提示信息
     */
    private String message;


    public SsoResponse() {

    }

    public SsoResponse(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.message = responseStatus.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static SsoResponse success() {
        return new SsoResponse(ResponseStatus.SUCCESS);
    }

    public static SsoResponse success(LoginUser loginUser) {
        SsoResponse ssoResponse = new SsoResponse(ResponseStatus.SUCCESS);
        ssoResponse.setUserId(Math.toIntExact(loginUser.getId()));
        ssoResponse.setData(new Data(loginUser.getAccount(),loginUser.getName(),loginUser.getRoleids(), Math.toIntExact(loginUser.getId())));
        return ssoResponse;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
