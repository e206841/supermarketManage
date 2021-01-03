package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class HealthRecordDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "序号");
        put("name", "姓名");
        put("sex", "性别");
        put("age", "年龄");
        put("address", "家庭住址");
        put("tjNo", "体检编号'");
        put("birthday", "出生日期");
        put("jdDate", "建档日期");
        put("holder", "户主名称");
        put("tjDate", "体检时间");
        put("gxyDate", "高血压时间");
        put("tnbDate", "糖尿病时间");
        put("nzzDate", "脑卒中");
        put("jsbDate", "精神病");
        put("ztcjDate", "肢体残疾");
        put("otherIllness", "其他病");
        put("isHardpeople", "是否困难人员");
        put("mobile", "本人电话");
        put("phone", "联系电话");
        put("doctor", "责任医生");
        put("number", "档案号");
        put("bz", "备注");
        put("isSign", "是否签约");
        put("idCard", "身份证号码");
        put("createDate", "创建时间");



    }
    @Override
    protected void initBeWrapped() {

    }
}