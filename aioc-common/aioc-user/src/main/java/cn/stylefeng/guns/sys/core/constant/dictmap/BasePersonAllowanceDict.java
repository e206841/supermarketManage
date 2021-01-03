package cn.stylefeng.guns.sys.core.constant.dictmap;

import cn.stylefeng.guns.base.dict.AbstractDictMap;

public class BasePersonAllowanceDict extends AbstractDictMap {

        @Override
        public void init() {
            put("id", "序号");
            put("idcardNumber", "身份证号码");
            put("name", "姓名");
            put("sex", "性别");
            put("birthday", "出生日期");
            put("phone", "联系方式");
            put("allowanceProd", "补贴标准");
            put("allowanceType", "补贴类别");
            put("assessmentLevels", "评估等级");
            put("allowancePolicy", "补贴政策");
            put("liveStation", "居住地");
            put("communityName", "所属社区");

    }

    @Override
    protected void initBeWrapped() {

    }
}