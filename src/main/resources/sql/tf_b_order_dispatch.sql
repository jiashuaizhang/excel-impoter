/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : ecsorder

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-07-12 11:02:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tf_b_order_dispatch
-- ----------------------------
DROP TABLE IF EXISTS `tf_b_order_dispatch`;
CREATE TABLE `tf_b_order_dispatch` (
  `order_id` bigint(20) NOT NULL,
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `business_type` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_delivery_intention` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '99' COMMENT '订单中心配送意向：07上门 10自提 99码上购派单/智能调度',
  `order_creat_time` datetime NOT NULL,
  `goods_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `province_code_post` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `city_code_post` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `district_code_post` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `post_addr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dispatch_cmd_cate` tinyint(2) NOT NULL COMMENT '调度指令状态分类：0.待智能调度 1.智能调度 2.转人工处理/待人工审核/待人工调度    3.人工发起派单/后台管理人员派单/人工调度（管理人员-> 营业厅/派送员）4.它厅转派（营业厅->营业厅）5.它人转派 (派送员->派送员)    6.转第三方物流待分配(eg.等待顺丰分配）7.第三方物流派单（eg.顺丰zop->派送员）8.调度指令结束-退回审单 9.开户完成 10 预约类订单退单    11.第三方物流转派（eg.顺丰zop 派送员->派送员）',
  `dispatch_state` tinyint(2) NOT NULL COMMENT '0 待派单 1.已派单 2.开户完成 3.退回审单-退回到新订单中心 (为0和1时，DISPATCH_OBJ_XX相关字段置为空；2、只有log表有，3.detail和log表存在)',
  `dispatch_obj_type` tinyint(2) DEFAULT NULL COMMENT '调度分配对象类型：1.营业厅  2.派送员',
  `dispatch_obj_subject` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '调度分配对象主体：xx营业厅编码  xx派送员编码',
  `dispatch_obj_tel` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '调度分配对象联系方式',
  `dispatch_obj_time` datetime DEFAULT NULL COMMENT '调度分配对象派送时间',
  `manual_reason_cate` tinyint(2) DEFAULT NULL COMMENT '1 无匹配网格  2 网格内没有符合条件的营业厅或派送员  3 数据/服务异常  4 派单员xxx回退（原因）  5.营业厅xxx回退（原因）  6 超时自动回退 7 管理人员撤回   8.其他原因 9 第三方发起回退',
  `manual_reason_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '进入人工调度的原因描述',
  `dispatch_recall_reason` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '撤回原因',
  `dispatch_tag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '派送状态: 1首次派单，2  再次派单',
  `auto_dispatch_grid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '网格id',
  `auto_dispatch_refer` tinyint(2) DEFAULT NULL COMMENT '智能调度参照值: 1 订单中心指定到营业厅 2.网格关键字匹配 ; 3.网格区域匹配',
  `auto_trace` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '智能匹配调度轨迹',
  `map_point` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货地址经纬度（百度地图）',
  `timeout_tag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:未超时 1 超时',
  `order_assign_subject` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单中心指定配送对象，为ORDER_DELIVERY_INTENTION =10时不为空',
  `change_reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT ' 变更原因',
  `dispatch_manager_tag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_staff` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '变更人：可能为后台管理人员、派单员编码、营业厅编码、ETM超时、AUTO_DISPATCH',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新操作时间',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `age_tag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `change_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `caller_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `broadband_order_id` bigint(20) DEFAULT NULL COMMENT '宽带订单号',
  `cross_city_tag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0表示本地订单 1表示跨地市订单 2跨省订单',
  `rsync_tag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '同步标记：1：默认(新订单中心同步) 2：预约类同步（张旭阳组同步预约单：如-后台电话购、投放预订单）',
  `lock_tag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '订单派单锁定标识。0：未锁定；1：锁定中',
  `sys_channel` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统来源 SS01 自有2I/SMA ; SS13:商城后台 SELECT * from td_b_commpara where PARAM_ATTR=9118;',
  `bus_channel` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务形态 BC06电话购 BC07预约上门 SELECT * from td_b_commpara where PARAM_ATTR=9119;',
  `product_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品ID',
  `lock_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '锁定或释放原因',
  `pool_tag` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1 一级调度池  2 二级调度池  默认为1',
  `com_code` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方物流公司编码 SELECT * FROM TF_F_MERCHANT_COMP_NAME ',
  `post_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户联系电话',
  `broadband_cust_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '宽带客户姓名',
  `broadband_pspt_type_code` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '宽带客户证件类别编码 ',
  `broadband_pspt_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '宽带客户证件号码',
  `appoint_date` datetime DEFAULT NULL COMMENT '预约安装时间 格式: YYYY-MM-DD HH24:MI:SS ',
  `appoint_date_segment` tinyint(2) DEFAULT NULL COMMENT '装机时间段 0-全天；1-上午；2-下午',
  `second_pool_limit_tag` tinyint(2) DEFAULT NULL COMMENT '配送方式：1自提 2上门  3上门自提均可',
  `post_addr_detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详细地址，不带省市区县的地址',
  `user_appoint_end_time` datetime DEFAULT NULL,
  `user_appoint_start_time` datetime DEFAULT NULL,
  `order_province_code` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单所属省份编码 2位',
  `order_city_code` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '订单所属地市编码 3位',
  `enter_msgo_time` datetime DEFAULT NULL COMMENT '进入码上购时间',
  `callout_times` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外呼次数',
  `contact_time` datetime DEFAULT NULL COMMENT '外呼时间 格式: YYYY-MM-DD HH24:MI:SS',
  `lock_expires_time` datetime DEFAULT NULL COMMENT '锁失效时间 格式: YYYY-MM-DD HH24:MI:SS',
  `dn` int(6) NOT NULL COMMENT '路由ID',
  `log_dn` int(6) NOT NULL COMMENT '路由ID',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `sync_tag` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0：未同步码上购， 1：已同步码上购',
  `link_install_res_tag` tinyint(2) DEFAULT NULL COMMENT '有无装机资源:0 无 1 有',
  `link_install_addr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '装机地址',
  `province_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省份名称',
  `city_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地市名称',
  `district_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区县名称',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
