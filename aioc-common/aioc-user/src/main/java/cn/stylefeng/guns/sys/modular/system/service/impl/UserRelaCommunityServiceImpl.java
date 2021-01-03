package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.sys.modular.system.entity.UserRelaCommunity;
import cn.stylefeng.guns.sys.modular.system.mapper.UserRelaCommunityMapper;
import cn.stylefeng.guns.sys.modular.system.service.IUserRelaCommunityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-12-10
 */
@Service
public class UserRelaCommunityServiceImpl extends ServiceImpl<UserRelaCommunityMapper, UserRelaCommunity> implements IUserRelaCommunityService {

    @Override
    @Transactional
    public void setArea(Long userId, String areaCode) {
        this.baseMapper.deleteByUserId(userId);
        UserRelaCommunity userRelaCommunity=new UserRelaCommunity();
        userRelaCommunity.setUserId(userId);
        userRelaCommunity.setCommunityCode(areaCode);
        this.baseMapper.insertUserReal(userRelaCommunity);
    }
}
