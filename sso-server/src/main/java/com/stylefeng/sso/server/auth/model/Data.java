package com.stylefeng.sso.server.auth.model;

@lombok.Data
public class Data {
    private String account;      // 账号
    private String name;         // 姓名
    private String roleids;
    private Integer userId;

//    public String getAccount() {
//        return account;
//    }
//
//    public void setAccount(String account) {
//        this.account = account;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getRoleids() {
//        return roleids;
//    }
//
//    public void setRoleids(String roleids) {
//        this.roleids = roleids;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }



    Data(String account, String name, String roleids, Integer userId){
        this.account=account;
        this.name = name;
        this.roleids = roleids;
        this.userId = userId;
    }
}
