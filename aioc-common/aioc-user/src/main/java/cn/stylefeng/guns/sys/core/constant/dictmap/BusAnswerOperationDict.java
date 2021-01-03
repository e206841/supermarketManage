package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class BusAnswerOperationDict extends AbstractDictMap {

    @Override
    public void init() {
        put("id", "序号");
        put("idcardNumber", "身份证号");
        put("name", "姓名");
        put("sex", "性别");
        put("birthday", "出生日期");
        put("education", "学历");
        put("phone", "手机号'");

        put("nativePlace", "籍贯");
        put("polictStation", "派出所");
        put("communityCode", "社区编码");
        put("contract", "联系方式");
        put("communityName", "社区名字");
        put("personbody", "身体状况");
        put("isOnlyHouse", "是否独居");
        put("monthlyIncome", "调查月收入");
        put("streetName", "街道名字");
        put("haveShebao", "是否有社保");
        put("haveWeijian", "是否有健康档案");
        put("isPoverty", "是否困难群众");
        put("isLivealone", "是否失独");
        put("monthlyIncomeShe", "养老待遇");
        put("zoneCode", "地区编码");
        put("zoneName", "地区名称");
        put("isGxy", "是否有高血压");
        put("isTnb", "是否有糖尿病");
        put("isNzz", "是否有脑中卒");
        put("isJsb", "是否有精神病");
        put("isZtcj", "是否肢体残疾");
        put("isIllness", "是否有其他疾病");
        put("streetCode", "街道编码");
        put("isDisabled", "是否残疾");



    }
    @Override
    protected void initBeWrapped() {

    }
}