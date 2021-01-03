package cn.stylefeng.guns.sys.modular.system.service;


import cn.stylefeng.guns.sys.modular.system.entity.UserRelaCommunity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-12-10
 */
public interface IUserRelaCommunityService extends IService<UserRelaCommunity> {

    void setArea(Long userId, String areaCode);
}
