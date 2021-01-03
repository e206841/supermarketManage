package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class ShebRecordDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "序号");
        put("sbId", "id");
        put("areaCode", "所属区县");
        put("idCard", "身份证号");
        put("birthday", "出生日期");
        put("name", "姓名");
        put("retireDate", "退休时间'");
        put("unitCode", "单位代码");
        put("unitName", "单位名称");
        put("joinworkDate", "参加工作时间");
        put("workAges", "工龄（月）");
        put("pensionMoney", "养老待遇（元)");

    }
    @Override
    protected void initBeWrapped() {

    }
}