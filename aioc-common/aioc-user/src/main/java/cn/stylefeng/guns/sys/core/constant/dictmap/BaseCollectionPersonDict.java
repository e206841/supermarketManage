package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class BaseCollectionPersonDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "序号");
        put("name", "姓名");
        put("collectionDepartment", "采集公司(部门)");
        put("collectionType", "采集员类型");
        put("idcardNumber", "身份证号码");
        put("sex", "性别");
        put("collectionAddress", "住址");
        put("collectionMobile", "联系人电话");
        put("collectionGridName", "全科网格名称");
        put("collectionGridCode", "全科网格编码");
    }

    @Override
    protected void initBeWrapped() {

    }
}