/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : aioc

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/12/2020 22:50:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for database_info
-- ----------------------------
DROP TABLE IF EXISTS `database_info`;
CREATE TABLE `database_info` (
  `db_id` bigint NOT NULL COMMENT '主键id',
  `db_name` varchar(255) NOT NULL COMMENT '数据库名称（英文名称）',
  `jdbc_driver` varchar(255) NOT NULL COMMENT 'jdbc的驱动类型',
  `user_name` varchar(255) NOT NULL COMMENT '数据库连接的账号',
  `password` varchar(255) NOT NULL COMMENT '数据库连接密码',
  `jdbc_url` varchar(2000) NOT NULL COMMENT 'jdbc的url',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注，摘要',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`db_id`),
  UNIQUE KEY `NAME_UNIQUE` (`db_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库信息表';

-- ----------------------------
-- Records of database_info
-- ----------------------------
BEGIN;
INSERT INTO `database_info` VALUES (1344293359494107138, 'master', 'com.mysql.cj.jdbc.Driver', 'root', 'li206842', 'jdbc:mysql://localhost:3306/aioc?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT', '主数据源，项目启动数据源！', '2020-12-30 22:44:47', NULL);
COMMIT;

-- ----------------------------
-- Table structure for oauth_user_info
-- ----------------------------
DROP TABLE IF EXISTS `oauth_user_info`;
CREATE TABLE `oauth_user_info` (
  `oauth_id` bigint NOT NULL COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '用户主键id',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `blog` varchar(255) DEFAULT NULL COMMENT '用户网址',
  `company` varchar(255) DEFAULT NULL COMMENT '所在公司',
  `location` varchar(255) DEFAULT NULL COMMENT '位置',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) DEFAULT NULL COMMENT '用户备注（各平台中的用户个人介绍）',
  `gender` int DEFAULT NULL COMMENT '性别，1-男，0-女',
  `source` varchar(255) DEFAULT NULL COMMENT '用户来源',
  `token` varchar(255) DEFAULT NULL COMMENT '用户授权的token',
  `uuid` varchar(255) DEFAULT NULL COMMENT '第三方平台的用户唯一di',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`oauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户信息表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `code` varchar(200) NOT NULL COMMENT '属性编码标识',
  `dict_flag` char(1) NOT NULL COMMENT '是否是字典中的值',
  `dict_type_id` bigint DEFAULT NULL COMMENT '字典类型的编码',
  `value` varchar(200) NOT NULL COMMENT '属性值，如果是字典中的类型，则为dict的code',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1143324237579165697, '验证码开关', 'GUNS_KAPTCHA_OPEN', 'Y', 1106120265689055233, 'DISABLE', '是否开启验证码', NULL, '2019-06-24 12:46:43', 1, '2019-06-25 09:04:42', 1);
INSERT INTO `sys_config` VALUES (1143386834613694465, '阿里云短信的keyId', 'GUNS_SMS_ACCESSKEY_ID', 'N', NULL, 'xxxkey', '阿里云短信的密钥key', NULL, '2019-06-25 13:13:59', 1, '2019-06-25 13:19:21', 1);
INSERT INTO `sys_config` VALUES (1143386953933254657, '阿里云短信的secret', 'GUNS_SMS_ACCESSKEY_SECRET', 'N', NULL, 'xxxsecret', '阿里云短信的secret', NULL, '2019-06-25 13:14:28', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143387023449649154, '阿里云短信的签名', 'GUNS_SMS_SIGN_NAME', 'N', NULL, 'xxxsign', '阿里云短信的签名', NULL, '2019-06-25 13:14:44', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143387131109044225, '阿里云短信登录的模板号', 'GUNS_SMS_LOGIN_TEMPLATE_CODE', 'N', NULL, 'SMS_XXXXXX', '阿里云短信登录的模板号', NULL, '2019-06-25 13:15:10', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143387225019510785, '验证码短信失效时间', 'GUNS_SMS_INVALIDATE_MINUTES', 'N', NULL, '2', '验证码短信失效时间', NULL, '2019-06-25 13:15:32', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143468689664876546, '基于BS的超市管理信息系统', 'GUNS_SYSTEM_NAME', 'N', NULL, '基于BS的超市管理信息系统', '管理系统名称', NULL, '2019-06-25 18:39:15', 1, '2020-12-30 22:30:00', 1);
INSERT INTO `sys_config` VALUES (1143468867767607297, '默认系统密码', 'GUNS_DEFAULT_PASSWORD', 'N', NULL, '111111', '默认系统密码', NULL, '2019-06-25 18:39:57', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1143469008025133058, 'OAuth2登录用户的账号标识', 'GUNS_OAUTH2_PREFIX', 'N', NULL, 'oauth2', 'OAuth2登录用户的账号标识', NULL, '2019-06-25 18:40:31', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145207130463191041, '顶部导航条是否开启', 'GUNS_DEFAULT_ADVERT', 'Y', 1106120265689055233, 'ENABLE', '顶部Guns广告是否开启', NULL, '2019-06-30 13:47:11', 1, '2019-06-30 13:47:20', 1);
INSERT INTO `sys_config` VALUES (1145915627211370498, 'Guns发布的编号', 'GUNS_SYSTEM_RELEASE_VERSION', 'N', NULL, '20191029', '用于防止浏览器缓存相关的js和css', NULL, '2019-07-02 12:42:30', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145915627211370499, '文件上传路径', 'GUNS_FILE_UPLOAD_PATH', 'N', NULL, '/Users/stylefeng/tmp/gunsTempFiles/', '文件上传默认目录', NULL, '2019-08-30 09:10:40', 1, '2020-01-10 11:05:55', 1);
INSERT INTO `sys_config` VALUES (1145915627211370500, 'BPMN文件上传路径', 'GUNS_BPMN_FILE_UPLOAD_PATH', 'N', NULL, '/Users/stylefeng/tmp/gunsTempFiles/', '工作流文件上传默认目录', NULL, '2019-08-30 09:10:40', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145915627211370501, '获取系统地密钥过期时间', 'GUNS_JWT_SECRET_EXPIRE', 'N', NULL, '86400', '获取系统地密钥过期时间（单位：秒），默认1天', NULL, '2019-10-16 23:02:39', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145915627211370502, '获取token的header标识', 'GUNS_TOKEN_HEADER_NAME', 'N', NULL, 'Authorization', '获取token的header标识', NULL, '2019-10-16 23:02:39', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145915627211370503, '获取租户是否开启的标识', 'GUNS_TENANT_OPEN', 'Y', 1106120265689055233, 'DISABLE', '获取租户是否开启的标识，默认是关的', NULL, '2019-10-16 23:02:39', 1, NULL, NULL);
INSERT INTO `sys_config` VALUES (1145915627211370504, '市场监管委指标', 'MARKET_INDEX', 'Y', NULL, 'https://www.maicedata.com/#/?tk=iZR32ruvZhcT9znM2QQK5yRTxv33GtrKi9ZcS2S7dtOpcEal1CiQQCN9kV6u4f95', '市场监管委指标', NULL, '2020-04-07 00:00:00', 1, '2020-04-07 00:00:00', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort` int DEFAULT NULL COMMENT '排序',
  `pid` bigint DEFAULT NULL COMMENT '父部门id',
  `pids` varchar(255) DEFAULT NULL COMMENT '父级ids',
  `simple_name` varchar(45) DEFAULT NULL COMMENT '简称',
  `full_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `description` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `is_inspection_dept` int DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1322010123991855106 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort` int DEFAULT NULL COMMENT '排序',
  `parent_id` bigint DEFAULT NULL COMMENT '父级字典',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `tips` varchar(255) DEFAULT NULL COMMENT '提示',
  `code` varchar(255) DEFAULT NULL COMMENT '值',
  `dict_type_id` bigint DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1323881580120260611 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (50, 0, 0, '性别', NULL, 'sys_sex', 1106120208097067009, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (51, 1, 50, '男', NULL, '1', 1106120208097067009, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (52, 2, 50, '女', NULL, '2', 1106120208097067009, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (53, 0, 0, '状态', NULL, 'sys_state', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (54, 1, 53, '启用', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (55, 2, 53, '禁用', NULL, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (56, 0, 0, '账号状态', NULL, 'account_state', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (57, 1, 56, '启用', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (58, 2, 56, '冻结', NULL, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (59, 3, 56, '已删除', NULL, '3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (81, 0, 0, '业务申请类型', '', 'applyType', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1620, 1, 0, '系统管理', NULL, 'BASE_SYSTEM', 1149217131989069820, '[0]', 'ENABLE', '系统管理平台', '2019-07-11 15:27:38', '2019-07-11 20:27:14', 1, 1, NULL);
INSERT INTO `sys_dict` VALUES (1321359225259061250, NULL, 0, '未提交', NULL, 'NO_SUBMIT', 1321358954814533634, '[0]', 'ENABLE', '', '2020-10-28 15:52:44', NULL, 1, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1321359520454176770, NULL, 0, '已完成', NULL, 'COMPLETE', 1321358954814533634, '[0]', 'ENABLE', '', '2020-10-28 15:53:54', NULL, 1, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1321359655791783937, NULL, 0, '审批中', NULL, 'APPROVAL', 1321358954814533634, '[0]', 'ENABLE', '', '2020-10-28 15:54:26', NULL, 1, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1321359926454415362, NULL, 0, '已提交', NULL, 'IS_SUBMIT', 1321358954814533634, '[0]', 'ENABLE', '', '2020-10-28 15:55:31', NULL, 1, NULL, NULL);
INSERT INTO `sys_dict` VALUES (1323881580120260610, NULL, 0, '未通过', NULL, 'FAILED', 1321358954814533634, '[0]', 'ENABLE', '', '2020-11-04 14:55:40', NULL, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_type_id` bigint NOT NULL COMMENT '字典类型id',
  `code` varchar(255) NOT NULL COMMENT '字典类型编码',
  `name` varchar(255) NOT NULL COMMENT '字典类型名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '字典描述',
  `system_flag` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是否是系统字典，Y-是，N-否',
  `status` varchar(10) NOT NULL DEFAULT 'ENABLE' COMMENT '状态(字典)',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`dict_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES (1106120208097067009, 'SEX', '性别', '', 'N', 'ENABLE', 4, '2019-03-14 17:09:43', 1, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120322450571266, 'ACCOUNT_STATUS', '账号状态', '', 'N', 'ENABLE', 40, '2019-03-14 17:10:10', 1, '2019-08-11 20:46:38', 1, NULL);
INSERT INTO `sys_dict_type` VALUES (1106120388036902914, 'DEL_FLAG', '是否删除', '', 'N', 'ENABLE', 2, '2019-03-14 17:10:26', 1, '2019-03-27 16:26:31', 1, NULL);
INSERT INTO `sys_dict_type` VALUES (1149217131989069820, 'SYSTEM_TYPE', '系统分类', '系统所有分类的维护', 'N', 'ENABLE', 50, '2019-07-11 15:21:30', 1, '2019-08-11 20:46:47', 1, NULL);
INSERT INTO `sys_dict_type` VALUES (1215163175058497538, 'MENU_SYSTEM_TYPE', '菜单所属系统', '菜单所属系统', 'N', 'ENABLE', 1, '2020-01-09 14:47:32', 1, NULL, NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (1321358954814533634, 'PRO_STATUS', '项目状态', '济南先行区税收', 'N', 'ENABLE', 10, '2020-10-28 15:51:39', 1, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_expense
-- ----------------------------
DROP TABLE IF EXISTS `sys_expense`;
CREATE TABLE `sys_expense` (
  `id` int NOT NULL AUTO_INCREMENT,
  `money` decimal(20,2) DEFAULT NULL COMMENT '报销金额',
  `desc` varchar(255) DEFAULT '' COMMENT '描述',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int DEFAULT NULL COMMENT '状态: 1.待提交  2:待审核   3.审核通过 4:驳回',
  `userid` int DEFAULT NULL COMMENT '用户id',
  `processId` varchar(255) DEFAULT NULL COMMENT '流程定义id',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报销表';

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info` (
  `file_id` varchar(50) NOT NULL COMMENT '主键id',
  `file_bucket` varchar(100) DEFAULT NULL COMMENT '文件仓库（oss仓库）',
  `file_name` varchar(100) NOT NULL COMMENT '文件名称',
  `file_suffix` varchar(50) DEFAULT NULL COMMENT '文件后缀',
  `file_size_kb` bigint DEFAULT NULL COMMENT '文件大小kb',
  `final_name` varchar(100) NOT NULL COMMENT '文件唯一标识id',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '存储路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint DEFAULT NULL COMMENT '修改用户',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件信息表';

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------
BEGIN;
INSERT INTO `sys_file_info` VALUES ('1344292549049712642', NULL, 'u=1083439821,1572648728&fm=26&gp=0.jpg', 'jpg', 41, '1344292549049712642.jpg', '/var/folders/n4/kd13vy7d4tz1qlq14zgckbtr0000gn/T/1344292549049712642.jpg', '2020-12-30 22:41:34', NULL, 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `login_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_name` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint DEFAULT NULL COMMENT '管理员id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否执行成功',
  `message` text COMMENT '具体消息',
  `ip_address` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`login_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1344294422783725570 DEFAULT CHARSET=utf8 COMMENT='登录记录';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (1344292574412668929, '退出日志', 1, '2020-12-30 22:41:40', '成功', NULL, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `sys_login_log` VALUES (1344292595136724994, '退出日志', 1, '2020-12-30 22:41:45', '成功', NULL, '0:0:0:0:0:0:0:1', NULL);
INSERT INTO `sys_login_log` VALUES (1344294422783725569, '登录日志', 1, '2020-12-30 22:49:01', '成功', NULL, '0:0:0:0:0:0:0:1', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编号',
  `pcode` varchar(255) DEFAULT NULL COMMENT '菜单父编号',
  `pcodes` varchar(255) DEFAULT NULL COMMENT '当前菜单的所有父菜单编号',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `sort` int DEFAULT NULL COMMENT '菜单排序号',
  `levels` int DEFAULT NULL COMMENT '菜单层级',
  `menu_flag` varchar(32) DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` varchar(32) DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `open_flag` int DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `menutype` varchar(255) DEFAULT NULL,
  `new_page_flag` varchar(32) DEFAULT NULL,
  `system_type` varchar(100) DEFAULT NULL COMMENT '系统分类(字典)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `tenant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1321366440835870723 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (105, 'system', '0', '[0],', '系统管理', 'layui-icon-set', '#', 1, 1, 'Y', NULL, 'ENABLE', 1, '', NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (106, 'mgr', 'system', '[0],[system],', '用户管理', '', '/mgr', 1, 2, 'Y', NULL, 'ENABLE', 1, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (107, 'mgr_add', 'mgr', '[0],[system],[mgr],', '添加用户', NULL, '/mgr/add', 1, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (108, 'mgr_edit', 'mgr', '[0],[system],[mgr],', '修改用户', NULL, '/mgr/edit', 2, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (109, 'mgr_delete', 'mgr', '[0],[system],[mgr],', '删除用户', NULL, '/mgr/delete', 3, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (110, 'mgr_reset', 'mgr', '[0],[system],[mgr],', '重置密码', NULL, '/mgr/reset', 4, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (111, 'mgr_freeze', 'mgr', '[0],[system],[mgr],', '冻结用户', NULL, '/mgr/freeze', 5, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (112, 'mgr_unfreeze', 'mgr', '[0],[system],[mgr],', '解除冻结用户', NULL, '/mgr/unfreeze', 6, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:49', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (113, 'mgr_setRole', 'mgr', '[0],[system],[mgr],', '分配角色', NULL, '/mgr/setRole', 7, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (114, 'role', 'system', '[0],[system],', '角色管理', NULL, '/role', 2, 2, 'Y', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (115, 'role_add', 'role', '[0],[system],[role],', '添加角色', NULL, '/role/add', 1, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (116, 'role_edit', 'role', '[0],[system],[role],', '修改角色', NULL, '/role/edit', 2, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (117, 'role_remove', 'role', '[0],[system],[role],', '删除角色', NULL, '/role/remove', 3, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (118, 'role_setAuthority', 'role', '[0],[system],[role],', '配置权限', NULL, '/role/setAuthority', 4, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (119, 'menu', 'system', '[0],[system],', '菜单管理', NULL, '/menu', 4, 2, 'Y', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (120, 'menu_add', 'menu', '[0],[system],[menu],', '添加菜单', NULL, '/menu/add', 1, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (121, 'menu_edit', 'menu', '[0],[system],[menu],', '修改菜单', NULL, '/menu/edit', 2, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (122, 'menu_remove', 'menu', '[0],[system],[menu],', '删除菜单', NULL, '/menu/remove', 3, 3, 'N', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (128, 'log', 'system', '[0],[system],', '业务日志', NULL, '/log', 6, 2, 'Y', NULL, 'ENABLE', 0, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (130, 'druid', 'system', '[0],[system],', '监控管理', NULL, '/druid', 7, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (131, 'dept', 'system', '[0],[system],', '部门管理', NULL, '/dept', 3, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (132, 'dict', 'system', '[0],[system],', '字典管理', NULL, '/dictType', 4, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (133, 'loginLog', 'system', '[0],[system],', '登录日志', NULL, '/loginLog', 6, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (134, 'log_clean', 'log', '[0],[system],[log],', '清空日志', NULL, '/log/delLog', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (135, 'dept_add', 'dept', '[0],[system],[dept],', '添加部门', NULL, '/dept/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (136, 'dept_update', 'dept', '[0],[system],[dept],', '修改部门', NULL, '/dept/update', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (137, 'dept_delete', 'dept', '[0],[system],[dept],', '删除部门', NULL, '/dept/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (138, 'dict_add', 'dict', '[0],[system],[dict],', '添加字典', NULL, '/dictType/addItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (139, 'dict_update', 'dict', '[0],[system],[dict],', '修改字典', NULL, '/dictType/editItem', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (140, 'dict_delete', 'dict', '[0],[system],[dict],', '删除字典', NULL, '/dictType/delete', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (141, 'notice', 'system', '[0],[system],', '通知管理', NULL, '/notice', 9, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (142, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (143, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (144, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (145, 'hello', '0', '[0],', '通知', 'fa-rocket', '/notice/hello', 1, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (148, 'code', 'dev_tools', '[0],[dev_tools],', '代码生成', 'fa-code', '/gen', 2, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-09-17 14:56:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (149, 'api_mgr', '0', '[0],', '接口文档', 'fa-leaf', '/swagger-ui.html', 3, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (150, 'to_menu_edit', 'menu', '[0],[system],[menu],', '菜单编辑跳转', '', '/menu/menu_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (151, 'menu_list', 'menu', '[0],[system],[menu],', '菜单列表', '', '/menu/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (152, 'to_dept_update', 'dept', '[0],[system],[dept],', '修改部门跳转', '', '/dept/dept_update', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (153, 'dept_list', 'dept', '[0],[system],[dept],', '部门列表', '', '/dept/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (154, 'dept_detail', 'dept', '[0],[system],[dept],', '部门详情', '', '/dept/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (155, 'to_dict_edit', 'dict', '[0],[system],[dict],', '修改菜单跳转', '', '/dict/dict_edit', 4, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (156, 'dict_list', 'dict', '[0],[system],[dict],', '字典列表', '', '/dict/list', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (157, 'dict_detail', 'dict', '[0],[system],[dict],', '字典详情', '', '/dict/detail', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-06-30 13:49:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (158, 'log_list', 'log', '[0],[system],[log],', '日志列表', '', '/log/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (159, 'log_detail', 'log', '[0],[system],[log],', '日志详情', '', '/log/detail', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (160, 'del_login_log', 'loginLog', '[0],[system],[loginLog],', '清空登录日志', '', '/loginLog/delLoginLog', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (161, 'login_log_list', 'loginLog', '[0],[system],[loginLog],', '登录日志列表', '', '/loginLog/list', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (162, 'to_role_edit', 'role', '[0],[system],[role],', '修改角色跳转', '', '/role/role_edit', 5, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (163, 'to_role_assign', 'role', '[0],[system],[role],', '角色分配跳转', '', '/role/role_assign', 6, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (164, 'role_list', 'role', '[0],[system],[role],', '角色列表', '', '/role/list', 7, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (165, 'to_assign_role', 'mgr', '[0],[system],[mgr],', '分配角色跳转', '', '/mgr/role_assign', 8, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (166, 'to_user_edit', 'mgr', '[0],[system],[mgr],', '编辑用户跳转', '', '/mgr/user_edit', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (167, 'mgr_list', 'mgr', '[0],[system],[mgr],', '用户列表', '', '/mgr/list', 10, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (171, 'dev_tools', '0', '[0],', '开发管理', 'layui-icon layui-icon-code-circle', '#', 2, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-09-17 14:56:04', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (172, 'dashboard', '0', '[0],', '主控面板', 'layui-icon layui-icon-home', '#', 10, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2019-04-08 22:48:15', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1110777136265838594, 'demos_show', 'dev_tools', '[0],[dev_tools],', '模板页面', 'layui-icon layui-icon-template', '#', 40, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-03-27 13:34:41', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1110777366856089602, 'excel_import', 'demos_show', '[0],[dev_tools],[demos_show],', 'excel导入', '', '/excel/import', 10, 3, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-03-27 13:35:36', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1110777491464667137, 'excel_export', 'demos_show', '[0],[dev_tools],[demos_show],', 'excel导出', '', '/excel/export', 20, 3, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-03-27 13:36:06', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1110787391943098370, 'advanced_form', 'demos_show', '[0],[dev_tools],[demos_show],', '高级表单', '', '/egForm', 30, 3, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-03-27 14:15:26', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1110839216310329346, 'pdf_view', 'demos_show', '[0],[dev_tools],[demos_show],', '文档预览', '', '/loadPdfFile', 40, 3, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-03-27 17:41:22', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1111546189892870145, 'console2', 'dashboard', '[0],[dashboard],', '统计报表', '', '/system/console2', 20, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-03-29 16:30:38', '2019-04-08 22:49:48', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1139826657964593154, 'meta_data', 'dev_tools', '[0],[dev_tools],', '系统配置', '', '#', 10, 2, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-06-15 17:27:07', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1139827152854716418, 'data_source', 'meta_data', '[0],[dev_tools],[meta_data],', '数据源管理', '', '/databaseInfo', 10, 3, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', '2019-06-15 17:29:05', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1142957882422112257, 'SYS_CONFIG', 'meta_data', '[0],[dev_tools],[meta_data],', '参数配置', 'fa-star', '/sysConfig', 5, 3, 'Y', '', 'ENABLE', 0, NULL, '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1142957882422112258, 'SYS_CONFIG_ADD', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置添加', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', 0, NULL, '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1142957882422112259, 'SYS_CONFIG_EDIT', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置修改', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', 0, NULL, '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1142957882426306562, 'SYS_CONFIG_DELETE', 'SYS_CONFIG', '[0],[dev_tools],[meta_data],[SYS_CONFIG],', '参数配置删除', 'fa-star', '', 999, 4, 'N', '', 'ENABLE', 0, NULL, '', 'BASE_SYSTEM', '2019-06-24 08:49:28', '2020-09-17 14:56:04', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1144441072852684801, 'SYS_POSITION', 'system', '[0],[system],', '职位管理', 'fa-star', '/position', 35, 2, 'Y', '', 'ENABL', 0, NULL, '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1144441072852684802, 'SYS_POSITION_ADD', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表添加', 'fa-star', '', 999, 3, 'N', '', 'ENABL', 0, NULL, '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1144441072852684803, 'SYS_POSITION_EDIT', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表修改', 'fa-star', '', 999, 3, 'N', '', 'ENABL', 0, NULL, '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1144441072852684804, 'SYS_POSITION_DELETE', 'SYS_POSITION', '[0],[system],[SYS_POSITION],', '职位表删除', 'fa-star', '', 999, 3, 'N', '', 'ENABL', 0, NULL, '', 'BASE_SYSTEM', '2019-06-28 11:03:09', '2019-06-28 11:06:42', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1194163728795758599, 'mgr_setArea', 'mgr', '[0],[system],[mgr],', '分配地区', '', '/mgr/setArea', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1194163728795758600, 'to_area_assign', 'mgr', '[0],[system],[mgr],', '地区分配跳转', '', '/area/area_assign', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1194163728795758601, 'to_assign_area', 'mgr', '[0],[system],[mgr],', '分配地区跳转', '', '/mgr/area_assign', 9, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1216910462586761224, 'notice_add', 'notice', '[0],[system],[notice],', '添加通知', NULL, '/notice/add', 1, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1216910462586761225, 'notice_update', 'notice', '[0],[system],[notice],', '修改通知', NULL, '/notice/update', 2, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1216910462586761226, 'notice_delete', 'notice', '[0],[system],[notice],', '删除通知', NULL, '/notice/delete', 3, 3, 'N', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, '2020-01-08 07:38:50', NULL, 1, NULL);
INSERT INTO `sys_menu` VALUES (1216910462586761227, 'hello', '0', '[0],', '通知', 'fa-rocket', '/notice/hello', 1, 1, 'Y', NULL, 'ENABLE', NULL, NULL, NULL, 'BASE_SYSTEM', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (1227151198576009217, 'BUS_ANSWER_QUSETION', '0', '[0],', '问卷数据', 'layui-icon-website', '#', 3, 1, 'Y', '', 'ENABLE', 0, '', '', 'BASE_SYSTEM', '2020-02-11 16:43:39', '2020-09-17 14:53:18', 1, 1, NULL);
INSERT INTO `sys_menu` VALUES (1227151198576009218, 'BUS_ANSWER_QUSETION_ADD', 'BUS_ANSWER_QUSETION', '[0],[BUS_ANSWER_QUSETION],', '问卷调查列表', 'layui-icon-website', '/busAnswerQusetion', 999, 2, 'Y', '', 'ENABLE', 0, '', '', 'BASE_SYSTEM', '2020-02-11 16:43:39', '2020-09-17 14:53:18', 1, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` int DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='通知表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` VALUES (8, '你好', '<p>你好，欢迎访问基于BS的超市管理信息系统</p><p><br/></p>', '2017-05-10 19:28:57', 1, '2020-12-30 22:33:02', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `operation_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_type` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `log_name` varchar(255) DEFAULT NULL COMMENT '日志名称',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `class_name` varchar(255) DEFAULT NULL COMMENT '类名称',
  `method` text COMMENT '方法名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `succeed` varchar(255) DEFAULT NULL COMMENT '是否成功',
  `message` text COMMENT '备注',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1344292298314219523 DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_operation_log` VALUES (1344292276378009601, '业务日志', '清空业务日志', 1, 'cn.stylefeng.guns.sys.modular.system.controller.LogController', 'delLog', '2020-12-30 22:40:29', '成功', '主键id=null', NULL);
INSERT INTO `sys_operation_log` VALUES (1344292298314219522, '业务日志', '清空登录日志', 1, 'cn.stylefeng.guns.sys.modular.system.controller.LoginLogController', 'delLog', '2020-12-30 22:40:34', '成功', '主键id=null', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_position
-- ----------------------------
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
  `position_id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(50) NOT NULL COMMENT '职位名称',
  `code` varchar(64) NOT NULL COMMENT '职位编码',
  `sort` int NOT NULL COMMENT '顺序',
  `status` varchar(100) NOT NULL DEFAULT 'ENABLE' COMMENT '状态(字典)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建者',
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`position_id`),
  UNIQUE KEY `CODE_UNI` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位表';

-- ----------------------------
-- Records of sys_position
-- ----------------------------
BEGIN;
INSERT INTO `sys_position` VALUES (1, '董事长', 'President', 1, 'ENABLE', '1', '2019-06-27 18:14:43', 1, '2020-10-27 16:46:58', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_relation`;
CREATE TABLE `sys_relation` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '涓婚敭',
  `menu_id` bigint DEFAULT NULL COMMENT '鑿滃崟id',
  `role_id` bigint DEFAULT NULL COMMENT '瑙掕壊id',
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1344216172601487363 DEFAULT CHARSET=utf8 COMMENT='瑙掕壊鍜岃彍鍗曞叧鑱旇〃';

-- ----------------------------
-- Records of sys_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_relation` VALUES (1321705559396716546, NULL, 19);
INSERT INTO `sys_relation` VALUES (1344216161100705793, 105, 1);
INSERT INTO `sys_relation` VALUES (1344216161226534913, 106, 1);
INSERT INTO `sys_relation` VALUES (1344216161352364034, 107, 1);
INSERT INTO `sys_relation` VALUES (1344216161465610242, 108, 1);
INSERT INTO `sys_relation` VALUES (1344216161557884930, 109, 1);
INSERT INTO `sys_relation` VALUES (1344216161650159617, 110, 1);
INSERT INTO `sys_relation` VALUES (1344216161763405825, 111, 1);
INSERT INTO `sys_relation` VALUES (1344216161885040642, 112, 1);
INSERT INTO `sys_relation` VALUES (1344216161998286850, 113, 1);
INSERT INTO `sys_relation` VALUES (1344216162149281794, 165, 1);
INSERT INTO `sys_relation` VALUES (1344216162279305217, 166, 1);
INSERT INTO `sys_relation` VALUES (1344216162400940033, 167, 1);
INSERT INTO `sys_relation` VALUES (1344216162505797634, 1194163728795758599, 1);
INSERT INTO `sys_relation` VALUES (1344216162623238145, 1194163728795758600, 1);
INSERT INTO `sys_relation` VALUES (1344216162778427393, 1194163728795758601, 1);
INSERT INTO `sys_relation` VALUES (1344216162900062209, 114, 1);
INSERT INTO `sys_relation` VALUES (1344216162988142593, 115, 1);
INSERT INTO `sys_relation` VALUES (1344216163088805890, 116, 1);
INSERT INTO `sys_relation` VALUES (1344216163210440706, 117, 1);
INSERT INTO `sys_relation` VALUES (1344216163344658433, 118, 1);
INSERT INTO `sys_relation` VALUES (1344216163436933122, 162, 1);
INSERT INTO `sys_relation` VALUES (1344216163545985025, 163, 1);
INSERT INTO `sys_relation` VALUES (1344216163655036930, 164, 1);
INSERT INTO `sys_relation` VALUES (1344216163730534401, 119, 1);
INSERT INTO `sys_relation` VALUES (1344216163847974914, 120, 1);
INSERT INTO `sys_relation` VALUES (1344216163977998337, 121, 1);
INSERT INTO `sys_relation` VALUES (1344216164095438849, 122, 1);
INSERT INTO `sys_relation` VALUES (1344216164191907841, 150, 1);
INSERT INTO `sys_relation` VALUES (1344216164292571138, 151, 1);
INSERT INTO `sys_relation` VALUES (1344216164389040130, 128, 1);
INSERT INTO `sys_relation` VALUES (1344216164527452162, 134, 1);
INSERT INTO `sys_relation` VALUES (1344216164636504065, 158, 1);
INSERT INTO `sys_relation` VALUES (1344216164787499010, 159, 1);
INSERT INTO `sys_relation` VALUES (1344216164896550913, 130, 1);
INSERT INTO `sys_relation` VALUES (1344216164997214210, 131, 1);
INSERT INTO `sys_relation` VALUES (1344216165118849026, 135, 1);
INSERT INTO `sys_relation` VALUES (1344216165223706626, 136, 1);
INSERT INTO `sys_relation` VALUES (1344216165332758530, 137, 1);
INSERT INTO `sys_relation` VALUES (1344216165458587649, 152, 1);
INSERT INTO `sys_relation` VALUES (1344216165555056642, 153, 1);
INSERT INTO `sys_relation` VALUES (1344216165664108546, 154, 1);
INSERT INTO `sys_relation` VALUES (1344216165768966145, 132, 1);
INSERT INTO `sys_relation` VALUES (1344216165882212354, 138, 1);
INSERT INTO `sys_relation` VALUES (1344216166045790209, 139, 1);
INSERT INTO `sys_relation` VALUES (1344216166184202241, 140, 1);
INSERT INTO `sys_relation` VALUES (1344216166263894018, 155, 1);
INSERT INTO `sys_relation` VALUES (1344216166360363010, 156, 1);
INSERT INTO `sys_relation` VALUES (1344216166465220610, 157, 1);
INSERT INTO `sys_relation` VALUES (1344216166570078210, 133, 1);
INSERT INTO `sys_relation` VALUES (1344216166679130114, 160, 1);
INSERT INTO `sys_relation` VALUES (1344216166767210498, 161, 1);
INSERT INTO `sys_relation` VALUES (1344216166859485185, 141, 1);
INSERT INTO `sys_relation` VALUES (1344216166951759873, 142, 1);
INSERT INTO `sys_relation` VALUES (1344216167044034561, 143, 1);
INSERT INTO `sys_relation` VALUES (1344216167136309250, 144, 1);
INSERT INTO `sys_relation` VALUES (1344216167232778241, 1216910462586761224, 1);
INSERT INTO `sys_relation` VALUES (1344216167337635842, 1216910462586761225, 1);
INSERT INTO `sys_relation` VALUES (1344216167438299138, 1216910462586761226, 1);
INSERT INTO `sys_relation` VALUES (1344216167564128258, 1144441072852684801, 1);
INSERT INTO `sys_relation` VALUES (1344216167668985858, 1144441072852684802, 1);
INSERT INTO `sys_relation` VALUES (1344216167765454850, 1144441072852684803, 1);
INSERT INTO `sys_relation` VALUES (1344216167861923841, 1144441072852684804, 1);
INSERT INTO `sys_relation` VALUES (1344216167962587138, 171, 1);
INSERT INTO `sys_relation` VALUES (1344216168063250434, 148, 1);
INSERT INTO `sys_relation` VALUES (1344216168151330818, 1110777136265838594, 1);
INSERT INTO `sys_relation` VALUES (1344216168247799810, 1110777366856089602, 1);
INSERT INTO `sys_relation` VALUES (1344216168310714369, 1110777491464667137, 1);
INSERT INTO `sys_relation` VALUES (1344216168474292226, 1110787391943098370, 1);
INSERT INTO `sys_relation` VALUES (1344216168591732738, 1110839216310329346, 1);
INSERT INTO `sys_relation` VALUES (1344216168679813121, 1139826657964593154, 1);
INSERT INTO `sys_relation` VALUES (1344216168784670722, 1139827152854716418, 1);
INSERT INTO `sys_relation` VALUES (1344216168881139714, 1142957882422112257, 1);
INSERT INTO `sys_relation` VALUES (1344216169065689089, 1142957882422112258, 1);
INSERT INTO `sys_relation` VALUES (1344216169183129602, 1142957882422112259, 1);
INSERT INTO `sys_relation` VALUES (1344216169287987201, 1142957882426306562, 1);
INSERT INTO `sys_relation` VALUES (1344216169384456193, 172, 1);
INSERT INTO `sys_relation` VALUES (1344216169501896705, 1111546189892870145, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort` int DEFAULT NULL COMMENT '序号',
  `pid` bigint DEFAULT NULL COMMENT '父角色id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `deptid` bigint DEFAULT NULL COMMENT '部门名称',
  `description` varchar(255) DEFAULT NULL COMMENT '提示',
  `version` int DEFAULT NULL COMMENT '保留字段(暂时没用）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `tenant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 10, 0, '超级管理员', 24, 'administrator', 1, NULL, '2020-10-29 14:45:44', NULL, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `account` varchar(45) DEFAULT NULL COMMENT '账号',
  `password` varchar(45) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `name` varchar(45) DEFAULT NULL COMMENT '名字',
  `birthday` varchar(100) DEFAULT NULL COMMENT '生日',
  `sex` varchar(11) DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id(2 代表普通用户，3 代表管理员)',
  `dept_id` bigint DEFAULT NULL COMMENT '部门id',
  `status` varchar(11) DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `version` int DEFAULT NULL COMMENT '保留字段',
  `sub_system` int DEFAULT NULL COMMENT '所属子系统 1：aioc 2：M端',
  `position` varchar(255) DEFAULT NULL,
  `ent_id` bigint DEFAULT NULL COMMENT '所属企业id',
  `ent_name` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL COMMENT '用户类型1：个人 2：企业 3：政府',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `tenant` varchar(255) DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=416 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, '1344292549049712642', 'admin', 'ff7abf6c3f90e733b40a4280d8e0d2e9', '8pgby', '李楠', '1992-10-26 00:00:00.0', 'M', '1003132050@qq.com', '18701020379', '1', 0, 'ENABLE', 25, 0, '', NULL, '', '0', '2016-01-29 08:49:53', NULL, '2020-12-30 22:41:34', 1, '');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_pos
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_pos`;
CREATE TABLE `sys_user_pos` (
  `user_pos_id` bigint NOT NULL COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `pos_id` bigint NOT NULL COMMENT '职位id',
  `tenant` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`user_pos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户职位关联表';

-- ----------------------------
-- Records of sys_user_pos
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_pos` VALUES (1306560630759030786, 361, 1, NULL);
INSERT INTO `sys_user_pos` VALUES (1341635085965262849, 397, 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
