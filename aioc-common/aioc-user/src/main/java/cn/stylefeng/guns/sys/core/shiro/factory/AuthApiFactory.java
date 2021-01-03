package cn.stylefeng.guns.sys.core.shiro.factory;

import cn.hutool.core.convert.Convert;
import cn.stylefeng.guns.base.auth.api.AuthApi;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.sys.core.constant.factory.ConstantFactory;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.mapper.MenuMapper;
import cn.stylefeng.guns.sys.modular.system.mapper.UserMapper;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class AuthApiFactory implements AuthApi {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    public static AuthApi me() {
        return SpringContextHolder.getBean(AuthApi.class);
    }

    @Override
    public LoginUser getLoginUser(Integer userId) {
        User user = userMapper.selectById(userId);
        return shiroUser(user);
    }

    private LoginUser shiroUser(User user) {
        LoginUser loginUser = new LoginUser();

        loginUser.setId(user.getUserId());
        loginUser.setAccount(user.getAccount());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptId()));
        loginUser.setName(user.getName());
        loginUser.setPassword(user.getPassword());

        Long[] roleArray = Convert.toLongArray(user.getRoleId());
        List<Long> roleList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        for (Long roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleTip(Math.toIntExact(roleId)));
        }
        loginUser.setRoleList(roleList);
        loginUser.setRoleNames(roleNameList);

        Set<String> permissionSet = new HashSet<>();

        for (Long roleId : roleList) {
            List<String> permissions = this.findPermissionsByRoleId(Math.toIntExact(roleId));
            if (permissions != null) {
                for (String permission : permissions) {
                    if (ToolUtil.isNotEmpty(permission)) {
                        permissionSet.add(permission);
                    }
                }
            }
        }
        loginUser.setPermissionSet(permissionSet);

        return loginUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return menuMapper.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

}
