/*
 Navicat Premium Data Transfer

 Source Server         : 本机数据库
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : tmall

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 21/08/2019 17:37:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pms_brand
-- ----------------------------
DROP TABLE IF EXISTS `pms_brand`;
CREATE TABLE `pms_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_brand
-- ----------------------------
INSERT INTO `pms_brand` VALUES (1, '小米');
INSERT INTO `pms_brand` VALUES (2, '华为');
INSERT INTO `pms_brand` VALUES (3, '三星');
INSERT INTO `pms_brand` VALUES (4, '苹果');

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片地址',
  `product_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '货号',
  `delete_status` int(1) NULL DEFAULT NULL COMMENT '删除状态：0->未删除；1->已删除',
  `publish_status` int(1) NULL DEFAULT NULL COMMENT '上架状态：0->下架；1->上架',
  `new_status` int(1) NULL DEFAULT NULL COMMENT '新品状态:0->不是新品；1->新品',
  `recommand_status` int(1) NULL DEFAULT NULL COMMENT '推荐状态；0->不推荐；1->推荐',
  `verify_status` int(1) NULL DEFAULT NULL COMMENT '审核状态：0->未审核；1->审核通过',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `sale` int(11) NULL DEFAULT NULL COMMENT '销量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `promotion_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '促销价格',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '副标题',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '市场价',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `low_stock` int(11) NULL DEFAULT NULL COMMENT '库存预警值',
  `unit` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品重量，默认为克',
  `preview_status` int(1) NULL DEFAULT NULL COMMENT '是否为预告商品：0->不是；1->是',
  `service_ids` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `album_pics` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '画册图片，连产品图片限制为5张，以逗号分割',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌id（外键）',
  `brand_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `product_category_id` bigint(20) NULL DEFAULT NULL COMMENT '产品分类id（外键）',
  `product_category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品分类名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_product_sn`(`product_sn`) USING BTREE,
  INDEX `index_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product
-- ----------------------------
INSERT INTO `pms_product` VALUES (1, '11', 'http://localhost:9092/tmall/upload/20190805175703baidu.png', '11111', NULL, 1, 1, 1, NULL, 111, NULL, 11.00, NULL, '111', '1111', 111.00, 11, NULL, '11', 111.00, 1, '1,2,3', '111', '1111', NULL, 4, NULL, NULL, NULL);
INSERT INTO `pms_product` VALUES (2, '111', 'http://localhost:9092/tmall/upload/20190805180601baidu.png', '1111', NULL, 1, 1, 1, NULL, 111, NULL, 111.00, NULL, '1111', '111', 111.00, 111, NULL, '11', 11.00, 1, '1,2,3', '11', '1111', NULL, 2, '华为', 7, '测试33');

-- ----------------------------
-- Table structure for pms_product_category
-- ----------------------------
DROP TABLE IF EXISTS `pms_product_category`;
CREATE TABLE `pms_product_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上机分类的编号：0表示一级分类',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `level` int(1) NULL DEFAULT NULL COMMENT '分类级别：0->1级；1->2级',
  `show_status` int(1) NULL DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pms_product_category
-- ----------------------------
INSERT INTO `pms_product_category` VALUES (5, 0, '测试11', 0, 1, 1, 'http://localhost:9092/tmall/upload/20190801161147baidu.png', '测试11测试11测试11', '测试11测试11测试11测试11测试11');
INSERT INTO `pms_product_category` VALUES (6, 0, '测试22', 0, 1, 2, 'http://localhost:9092/tmall/upload/20190805150708裤子.jpg', '测试22测试22', '测试22测试22测试22测试22测试22测试22');
INSERT INTO `pms_product_category` VALUES (7, 6, '测试33', 1, 0, 3, 'http://localhost:9092/tmall/upload/201908011615416.4加班.png', '测试33测试33', '测试33测试33测试33测试33测试33');
INSERT INTO `pms_product_category` VALUES (8, 0, '111', 0, 1, 111, 'http://localhost:9092/tmall/upload/20190812095232baidu.png', '11', '1221');
INSERT INTO `pms_product_category` VALUES (9, 0, '111111', 0, 1, 111, 'http://localhost:9092/tmall/upload/20190805150750souhu.jpg', '111', '1111');
INSERT INTO `pms_product_category` VALUES (10, 0, '11111', 0, 1, 111, 'http://localhost:9092/tmall/upload/20190814171601baidu.png', '11', '1111');

-- ----------------------------
-- Table structure for quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job`;
CREATE TABLE `quartz_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Spring Bean名称',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron 表达式',
  `is_pause` int(1) NULL DEFAULT NULL COMMENT '状态：1暂停、0启用',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '创建或更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quartz_job
-- ----------------------------
INSERT INTO `quartz_job` VALUES (4, 'testTask', '0/5 * * * * ? ', 1, '测试', 'run', '', '测试', '2019-08-21 02:53:04');

-- ----------------------------
-- Table structure for quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `quartz_job_log`;
CREATE TABLE `quartz_job_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Bean名称',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `is_success` int(1) NULL DEFAULT NULL COMMENT '状态，1失败、0成功',
  `exception_detail` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常详细',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '耗时（毫秒）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 909 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quartz_job_log
-- ----------------------------
INSERT INTO `quartz_job_log` VALUES (893, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 8, '2019-08-08 11:26:57');
INSERT INTO `quartz_job_log` VALUES (894, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 2, '2019-08-08 11:26:58');
INSERT INTO `quartz_job_log` VALUES (895, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 0, '2019-08-08 11:26:58');
INSERT INTO `quartz_job_log` VALUES (896, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 2, '2019-08-08 11:26:59');
INSERT INTO `quartz_job_log` VALUES (897, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 3, '2019-08-08 11:27:00');
INSERT INTO `quartz_job_log` VALUES (898, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 2, '2019-08-08 12:00:09');
INSERT INTO `quartz_job_log` VALUES (899, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 1, '2019-08-08 13:38:29');
INSERT INTO `quartz_job_log` VALUES (900, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 2, '2019-08-08 13:38:36');
INSERT INTO `quartz_job_log` VALUES (901, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 6, '2019-08-08 14:15:38');
INSERT INTO `quartz_job_log` VALUES (902, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 23, '2019-08-08 14:15:50');
INSERT INTO `quartz_job_log` VALUES (903, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 7, '2019-08-16 04:02:00');
INSERT INTO `quartz_job_log` VALUES (904, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 1, '2019-08-16 04:02:05');
INSERT INTO `quartz_job_log` VALUES (905, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 0, '2019-08-16 04:02:10');
INSERT INTO `quartz_job_log` VALUES (906, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 2, '2019-08-16 04:02:15');
INSERT INTO `quartz_job_log` VALUES (907, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 19, '2019-08-16 10:08:07');
INSERT INTO `quartz_job_log` VALUES (908, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 1, '2019-08-16 10:08:08');
INSERT INTO `quartz_job_log` VALUES (909, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 7, '2019-08-21 02:53:01');
INSERT INTO `quartz_job_log` VALUES (910, '测试', 'testTask', 'run', '', '0/5 * * * * ? ', 0, NULL, 1, '2019-08-21 02:53:02');

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `enabled` int(1) NULL DEFAULT NULL COMMENT '帐号启用状态：0->禁用；1->启用',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `ums_job_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位id',
  `ums_deptment_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES (1, 'admin', '$2a$10$4XqjamdaivwxoH6ovutK4OU15n6r.xE/JJ8d5jGDtAhJMVbRGI6be', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3357786243,3135716437&fm=26&gp=0.jpg', '系统管理员', '8888888@qq.com', NULL, '15825898888', 1, '2019-08-21 06:51:21', '2019-07-26 15:53:07', '2019-08-21 08:29:09', '系统管理员', 'admin', 1, 1);
INSERT INTO `ums_admin` VALUES (7, 'test', '$2a$10$BzwTgS537hCeI2iXdGDnOObLq9LrUF39Vc0JgnoEYmAXrAYleppPC', NULL, '普通管理员', '8888888@qq.com', NULL, '13520962818', 1, '2019-08-21 06:51:09', '2019-08-21 05:10:14', '2019-08-21 08:31:51', NULL, 'admin', 5, 2);

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES (64, 1, 1);
INSERT INTO `ums_admin_role_relation` VALUES (68, 7, 8);

-- ----------------------------
-- Table structure for ums_department
-- ----------------------------
DROP TABLE IF EXISTS `ums_department`;
CREATE TABLE `ums_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `enabled` int(255) NULL DEFAULT NULL COMMENT '状态，1正常；0停用',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序号',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'label',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_department
-- ----------------------------
INSERT INTO `ums_department` VALUES (1, 'tmall', 0, '2019-03-25 09:14:05', 1, 1, 'tmall');
INSERT INTO `ums_department` VALUES (2, '研发部', 7, '2019-03-25 09:15:32', 1, 1, '研发部');
INSERT INTO `ums_department` VALUES (5, '运维部', 7, '2019-03-25 09:20:44', 1, 1, '运维部');
INSERT INTO `ums_department` VALUES (6, '测试部', 8, '2019-03-25 09:52:18', 1, 1, '测试部');
INSERT INTO `ums_department` VALUES (7, '华南分部', 1, '2019-03-25 11:04:50', 1, 1, '华南分部');
INSERT INTO `ums_department` VALUES (8, '华北分部', 1, '2019-03-25 11:04:53', 1, 1, '华北分部');
INSERT INTO `ums_department` VALUES (9, '财务部', 7, '2019-03-25 11:05:34', 1, 1, '财务部');
INSERT INTO `ums_department` VALUES (10, '行政部', 8, '2019-03-25 11:05:58', 1, 1, '行政部');
INSERT INTO `ums_department` VALUES (11, '人事部', 8, '2019-03-25 11:07:58', 1, 1, '人事部');
INSERT INTO `ums_department` VALUES (12, '市场部', 7, '2019-03-25 11:10:24', 1, 1, '市场部');

-- ----------------------------
-- Table structure for ums_job
-- ----------------------------
DROP TABLE IF EXISTS `ums_job`;
CREATE TABLE `ums_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作名称',
  `enabled` int(255) NULL DEFAULT NULL COMMENT '状态，1正常；0停用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序字段',
  `ums_department_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_job
-- ----------------------------
INSERT INTO `ums_job` VALUES (1, '董事长秘书', 1, '2019-03-29 14:01:30', 2, 1);
INSERT INTO `ums_job` VALUES (2, '人事专员', 0, '2019-03-29 14:52:28', 1, 11);
INSERT INTO `ums_job` VALUES (3, '产品经理', 1, '2019-03-29 14:55:51', 4, 2);
INSERT INTO `ums_job` VALUES (4, '全栈开发', 1, '2019-03-31 13:39:30', 6, 2);
INSERT INTO `ums_job` VALUES (5, '软件测试', 1, '2019-03-31 13:39:43', 5, 2);
INSERT INTO `ums_job` VALUES (6, '董事长', 1, '2019-03-31 14:58:15', 1, 1);

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级菜单ID',
  `i_frame` int(255) NULL DEFAULT 1 COMMENT '是否外链，1正常；0外链',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `enabled` int(255) NULL DEFAULT 1 COMMENT '状态，1正常；0停用',
  `sort` int(255) NULL DEFAULT NULL COMMENT '排序字段',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `hidden` int(255) NULL DEFAULT 1 COMMENT '是否隐藏，1显示，0隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (1, '商品', 0, 1, NULL, 'icon', 'pms', 1, 2, '2019-08-09 13:01:11', 1);
INSERT INTO `ums_menu` VALUES (2, '商品列表', 1, 1, 'pms/product/index', 'index', 'product', 1, 2, '2019-08-09 13:04:05', 1);
INSERT INTO `ums_menu` VALUES (3, '添加商品', 1, 1, 'pms/product/add', 'icon', 'addProduct', 1, 3, '2019-08-09 13:04:50', 1);
INSERT INTO `ums_menu` VALUES (4, '商品分类', 1, 1, 'pms/productCate', 'icon', 'productCate', 1, 4, '2019-08-09 13:05:39', 1);
INSERT INTO `ums_menu` VALUES (5, '系统工具', 0, 1, '', 'icon', 'sys-tools', 1, 3, '2019-08-09 13:06:49', 1);
INSERT INTO `ums_menu` VALUES (6, '定时任务', 5, 1, 'tools/timing/index', 'icon', 'timing', 1, 2, '2019-08-09 13:07:40', 1);
INSERT INTO `ums_menu` VALUES (7, '项目地址', 0, 0, '', 'icon', 'https://github.com', 1, 1, '2019-08-09 13:08:55', 1);
INSERT INTO `ums_menu` VALUES (8, '添加商品分类', 1, 1, 'pms/productCate/add', 'icon', 'addProductCate', 1, 1, '2019-08-11 21:49:56', 0);
INSERT INTO `ums_menu` VALUES (9, '修改商品分类', 1, 1, 'pms/productCate/update', 'icon', 'updateProductCate', 1, 1, '2019-08-11 21:50:26', 0);
INSERT INTO `ums_menu` VALUES (10, '系统管理', 0, 1, NULL, 'system', 'system', 1, 4, '2019-08-11 22:17:02', 1);
INSERT INTO `ums_menu` VALUES (11, '用户管理', 10, 1, 'system/user/index', 'user', 'user', 1, 2, '2019-08-11 22:22:48', 1);
INSERT INTO `ums_menu` VALUES (12, '权限管理', 10, 1, 'system/permission/index', 'permission', 'permission', 1, 4, '2019-08-15 11:14:50', 1);
INSERT INTO `ums_menu` VALUES (13, '菜单管理', 10, 1, 'system/menu/index', 'menu', 'menu', 1, 5, '2019-08-15 15:32:28', 1);
INSERT INTO `ums_menu` VALUES (19, '部门管理', 10, 1, 'system/dept/index', 'icon', 'dept', 1, 6, '2019-08-16 10:14:42', 1);
INSERT INTO `ums_menu` VALUES (20, '岗位管理', 10, 1, 'system/job/index', 'icon', 'job', 1, 7, '2019-08-16 15:35:36', 1);
INSERT INTO `ums_menu` VALUES (24, '角色管理', 10, 1, 'system/role/index', 'icon', 'role', 1, 3, '2019-08-19 08:15:40', 1);

-- ----------------------------
-- Table structure for ums_permission
-- ----------------------------
DROP TABLE IF EXISTS `ums_permission`;
CREATE TABLE `ums_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `alias` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int(1) NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission` VALUES (1, 0, '超级管理员', 'ADMIN', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (2, 0, '用户管理', 'USER_ALL', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', '2019-08-21 09:11:12', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (3, 2, '用户查询', 'USER_SELECT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (4, 2, '用户创建', 'USER_CREATE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (5, 2, '用户编辑', 'USER_EDIT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (6, 2, '用户删除', 'USER_DELETE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (22, 0, '角色管理', 'ROLES_ALL', NULL, NULL, NULL, NULL, 2, '2019-08-15 11:24:48', '2019-08-21 09:11:19', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (23, 22, '角色查询', 'ROLES_SELECT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (24, 22, '角色创建', 'ROLES_CREATE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (25, 22, '角色编辑', 'ROLES_EDIT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (26, 22, '角色删除', 'ROLES_DELETE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (27, 0, '权限管理', 'PERMISSION_ALL', NULL, NULL, NULL, NULL, 3, '2019-08-15 11:24:48', '2019-08-21 09:11:25', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (28, 27, '权限查询', 'PERMISSION_SELECT', NULL, NULL, NULL, NULL, 2, '2019-08-15 11:24:48', '2019-08-21 09:06:47', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (29, 27, '权限创建', 'PERMISSION_CREATE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (30, 27, '权限编辑', 'PERMISSION_EDIT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (31, 27, '权限删除', 'PERMISSION_DELETE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (32, 0, '菜单管理', 'MENU_ALL', NULL, NULL, NULL, NULL, 4, '2019-08-15 11:24:48', '2019-08-21 09:11:47', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (33, 32, '菜单查询', 'MENU_SELECT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (34, 32, '菜单创建', 'MENU_CREATE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (35, 32, '菜单编辑', 'MENU_EDIT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (36, 32, '菜单删除', 'MENU_DELETE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (37, 0, '部门管理', 'DEPT_ALL', NULL, NULL, NULL, NULL, 5, '2019-08-15 11:24:48', '2019-08-21 09:11:56', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (38, 37, '部门查询', 'DEPT_SELECT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (39, 37, '部门创建', 'DEPT_CREATE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (40, 37, '部门编辑', 'DEPT_EDIT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (41, 37, '部门删除', 'DEPT_DELETE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (42, 0, '岗位管理', 'USERJOB_ALL', NULL, NULL, NULL, NULL, 6, '2019-08-15 11:24:48', '2019-08-21 09:12:02', NULL, 'admin');
INSERT INTO `ums_permission` VALUES (43, 42, '岗位查询', 'USERJOB_SELECT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (44, 42, '岗位创建', 'USERJOB_CREATE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (45, 42, '岗位编辑', 'USERJOB_EDIT', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);
INSERT INTO `ums_permission` VALUES (46, 42, '岗位删除', 'USERJOB_DELETE', NULL, NULL, NULL, NULL, 1, '2019-08-15 11:24:48', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(1) NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `data_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `level` int(255) NULL DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (1, '超级管理员', '超级管理员', 1, 0, '2019-08-09 12:54:25', '2019-08-21 02:04:07', 'SYS', NULL, '自定义', 1);
INSERT INTO `ums_role` VALUES (8, '普通管理员', '普通管理员', 1, 0, '2019-08-21 02:57:07', NULL, NULL, NULL, '自定义', 3);

-- ----------------------------
-- Table structure for ums_role_department_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_department_relation`;
CREATE TABLE `ums_role_department_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `department_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_department_relation
-- ----------------------------
INSERT INTO `ums_role_department_relation` VALUES (6, 1, 1);
INSERT INTO `ums_role_department_relation` VALUES (8, 8, 1);

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `ums_role_menu_relation` VALUES (1, 1, 1);
INSERT INTO `ums_role_menu_relation` VALUES (2, 1, 2);
INSERT INTO `ums_role_menu_relation` VALUES (3, 1, 3);
INSERT INTO `ums_role_menu_relation` VALUES (4, 1, 4);
INSERT INTO `ums_role_menu_relation` VALUES (5, 1, 5);
INSERT INTO `ums_role_menu_relation` VALUES (6, 1, 6);
INSERT INTO `ums_role_menu_relation` VALUES (7, 1, 7);
INSERT INTO `ums_role_menu_relation` VALUES (13, 1, 8);
INSERT INTO `ums_role_menu_relation` VALUES (14, 1, 9);
INSERT INTO `ums_role_menu_relation` VALUES (15, 1, 10);
INSERT INTO `ums_role_menu_relation` VALUES (16, 1, 11);
INSERT INTO `ums_role_menu_relation` VALUES (17, 1, 12);
INSERT INTO `ums_role_menu_relation` VALUES (18, 1, 13);
INSERT INTO `ums_role_menu_relation` VALUES (19, 1, 19);
INSERT INTO `ums_role_menu_relation` VALUES (20, 1, 20);
INSERT INTO `ums_role_menu_relation` VALUES (21, 1, 24);

-- ----------------------------
-- Table structure for ums_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_permission_relation`;
CREATE TABLE `ums_role_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `ums_role_permission_relation` VALUES (225, 1, 1);
INSERT INTO `ums_role_permission_relation` VALUES (226, 1, 2);
INSERT INTO `ums_role_permission_relation` VALUES (227, 1, 3);
INSERT INTO `ums_role_permission_relation` VALUES (228, 1, 4);
INSERT INTO `ums_role_permission_relation` VALUES (229, 1, 5);
INSERT INTO `ums_role_permission_relation` VALUES (230, 1, 6);
INSERT INTO `ums_role_permission_relation` VALUES (231, 1, 22);
INSERT INTO `ums_role_permission_relation` VALUES (232, 1, 23);
INSERT INTO `ums_role_permission_relation` VALUES (233, 1, 24);
INSERT INTO `ums_role_permission_relation` VALUES (234, 1, 25);
INSERT INTO `ums_role_permission_relation` VALUES (235, 1, 26);
INSERT INTO `ums_role_permission_relation` VALUES (236, 1, 27);
INSERT INTO `ums_role_permission_relation` VALUES (237, 1, 28);
INSERT INTO `ums_role_permission_relation` VALUES (238, 1, 29);
INSERT INTO `ums_role_permission_relation` VALUES (239, 1, 30);
INSERT INTO `ums_role_permission_relation` VALUES (240, 1, 31);
INSERT INTO `ums_role_permission_relation` VALUES (241, 1, 32);
INSERT INTO `ums_role_permission_relation` VALUES (242, 1, 33);
INSERT INTO `ums_role_permission_relation` VALUES (243, 1, 34);
INSERT INTO `ums_role_permission_relation` VALUES (244, 1, 35);
INSERT INTO `ums_role_permission_relation` VALUES (245, 1, 36);
INSERT INTO `ums_role_permission_relation` VALUES (246, 1, 37);
INSERT INTO `ums_role_permission_relation` VALUES (247, 1, 38);
INSERT INTO `ums_role_permission_relation` VALUES (248, 1, 39);
INSERT INTO `ums_role_permission_relation` VALUES (249, 1, 40);
INSERT INTO `ums_role_permission_relation` VALUES (250, 1, 41);
INSERT INTO `ums_role_permission_relation` VALUES (251, 1, 42);
INSERT INTO `ums_role_permission_relation` VALUES (252, 1, 43);
INSERT INTO `ums_role_permission_relation` VALUES (253, 1, 44);
INSERT INTO `ums_role_permission_relation` VALUES (254, 1, 45);
INSERT INTO `ums_role_permission_relation` VALUES (255, 1, 46);

SET FOREIGN_KEY_CHECKS = 1;
