package cn.stylefeng.guns.sys.modular.system.mapper;


import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.sys.modular.system.entity.BaseCommunity;
import cn.stylefeng.guns.sys.modular.system.model.params.BaseCommunityParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BaseCommunityResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2019-12-09
 */
public interface BaseCommunityMapper extends BaseMapper<BaseCommunity> {

    List<ZTreeNode> communityTreeList();

    List<ZTreeNode>   communitySubTreeList(String parentCode);


    List<ZTreeNode> communityTreeListByRoleId(String[] strArray);

    BaseCommunity selectCommunityByUserId(Long userId);

    //查询所有的子集菜单
    List<String> selectAllChildren(String parentCode);


    List<BaseCommunity> selectByparent(String parentCode);
}
