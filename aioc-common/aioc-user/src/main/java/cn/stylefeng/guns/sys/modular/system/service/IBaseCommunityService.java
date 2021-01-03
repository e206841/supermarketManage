package cn.stylefeng.guns.sys.modular.system.service;


import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BaseCommunity;
import cn.stylefeng.guns.sys.modular.system.model.params.BaseCommunityParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BaseCommunityResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2019-12-09
 */
public interface IBaseCommunityService extends IService<BaseCommunity> {

    List<ZTreeNode> communityTreeList();

    List<ZTreeNode> communitySubTreeList(String parentCode);

    List<ZTreeNode> communityTreeListByRoleId(String[] strArray);

    BaseCommunity selectCommunityByUserId(Long userId);

    //查询所有的子集菜单
    List<String> selectAllChildren(String parentCode);

    List<BaseCommunity> selectByparent(String parentCode);
}
