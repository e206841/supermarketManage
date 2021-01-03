package cn.stylefeng.guns.sys.modular.system.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @author linan144
 * @date 2020/1/1619:29
 */
@Data
public class UserExpDto {

    private Long userId;
    //账号
    @Excel(name = "账号")
    private String account;
    //姓名
    @Excel(name = "姓名")
    private String name;
    //生日
    @Excel(name = "生日")
    private String birthday;
    //性别
    @Excel(name = "性别")
    private String sex;
    //邮箱
    @Excel(name = "邮箱")
    private String email;
    //部门
    @Excel(name = "部门")
    private String deptName;
    //职位
    @Excel(name = "职位")
    private String positionName;
    //电话
    @Excel(name = "电话")
    private String phone;
    //创建时间
    @Excel(name = "创建时间")
    private String createTime;
    //状态
    @Excel(name = "状态")
    private String status;
    private Long deptId;

}

