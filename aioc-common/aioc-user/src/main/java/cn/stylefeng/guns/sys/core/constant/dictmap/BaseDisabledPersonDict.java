package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class BaseDisabledPersonDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "序号");
        put("name", "姓名");
        put("idcardNumber", "身份证号码");
        put("sex", "性别");
        put("birthday", "出生日期");
        put("age", "年龄");
        put("disabledAccount", "户口性质");
        put("disabledNo", "残疾证号");
        put("disabledType", "残疾类别");
        put("disabledLevel", "残疾等级");
        put("disabledExplain", "残疾等级说明");
        put("disabledStatuse", "持证状态");
        put("disabledPhone", "固定电话");
        put("disabledMobile", "移动电话");
        put("disabledState", "所在地区");
        put("disabledAccountAddress", "户籍地址");
        put("disabledAddress", "详细住址");
        put("emergencyPerson", "联系人姓名");
        put("disabledReleation", "与其关系");
        put("disabledReleation", "联系人固定电话");
        put("emergencyMobile", "联系人移动电话");

    }

    @Override
    protected void initBeWrapped() {

    }
}