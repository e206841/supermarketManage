package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.UserRelaCommunity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-12-10
 */
public interface UserRelaCommunityMapper extends BaseMapper<UserRelaCommunity> {

    void deleteByUserId(Long userId);

    void insertUserReal(UserRelaCommunity userRelaCommunity);

    String  getByUserId(@Param("userId") Long userId);
}
