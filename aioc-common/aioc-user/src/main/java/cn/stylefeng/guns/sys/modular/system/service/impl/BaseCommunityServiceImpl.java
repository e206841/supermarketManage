package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.node.ZTreeNode;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.BaseCommunity;
import cn.stylefeng.guns.sys.modular.system.mapper.BaseCommunityMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.BaseCommunityParam;
import cn.stylefeng.guns.sys.modular.system.model.result.BaseCommunityResult;
import cn.stylefeng.guns.sys.modular.system.service.IBaseCommunityService;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2019-12-09
 */
@Service
public class BaseCommunityServiceImpl extends ServiceImpl<BaseCommunityMapper, BaseCommunity> implements IBaseCommunityService {

    @Override
    @DataSource(name = "tianjin_yanglao")
    public List<ZTreeNode> communityTreeList() {
        return this.baseMapper.communityTreeList();
    }



    @Override
    @DataSource(name = "tianjin_yanglao")
    public List<ZTreeNode> communitySubTreeList(String parentCode) {
        return this.baseMapper.communitySubTreeList(parentCode);
    }



    @Override
    @DataSource(name = "tianjin_yanglao")
    public List<ZTreeNode> communityTreeListByRoleId(String[] strArray) {
        return this.baseMapper.communityTreeListByRoleId(strArray);
    }

    @Override
    public BaseCommunity selectCommunityByUserId(Long userId) {
        return this.baseMapper.selectCommunityByUserId(userId);
    }

    //查询所有的子集菜单
    @Override
    @DataSource(name = "tianjin_yanglao")
    public List<String> selectAllChildren(String parentCode){
        return this.baseMapper.selectAllChildren(parentCode);
    }

    //查询子项由父项code
    @Override
    @DataSource(name = "tianjin_yanglao")
    public List<BaseCommunity> selectByparent(String parentCode){
        return this.baseMapper.selectByparent(parentCode);
    }

}
