package com.stylefeng.sso.server.modular.dto;

import cn.stylefeng.guns.base.auth.model.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * creat by han on 2019/3/19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;          // 主键ID
    private String name;         // 姓名
    private String roleids;      //角色集
    private String account;      //角色集


    public static UserDto from(LoginUser sysUser) {

        return UserDto.builder()
                .id(Math.toIntExact(sysUser.getId()))
                .name(sysUser.getName())
                .roleids(sysUser.getRoleids())
                .account(sysUser.getAccount())
                .build();
    }

}
