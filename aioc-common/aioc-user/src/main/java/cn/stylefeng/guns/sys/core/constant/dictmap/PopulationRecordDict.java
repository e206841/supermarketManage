package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class PopulationRecordDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "序号");
        put("idCard", "证件号码");
        put("name", "姓名");
        put("sex", "性别");
        put("birthday", "出生日期");
        put("schoolLevel", "文化程度");
        put("registerAddress", "户籍所在地'");
        put("policeStation", "派出所");
        put("mobile", "电话");
        put("createDate", "创建时间");


    }
    @Override
    protected void initBeWrapped() {

    }
}