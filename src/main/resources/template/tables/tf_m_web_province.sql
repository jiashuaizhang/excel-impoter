/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : ecsorder

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-07-12 11:04:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tf_m_web_province
-- ----------------------------
DROP TABLE IF EXISTS `tf_m_web_province`;
CREATE TABLE `tf_m_web_province` (
  `province_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '省份编码',
  `province_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省份名称',
  `ess_province_code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ESS省份编码',
  `order_number` int(4) DEFAULT NULL COMMENT '顺序号',
  `values1` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预留字段1',
  `values2` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '预留字段2',
  PRIMARY KEY (`province_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='省份WEB编码表';

-- ----------------------------
-- Records of tf_m_web_province
-- ----------------------------
INSERT INTO `tf_m_web_province` VALUES ('110000', '北京', '11', '1', null, null);
INSERT INTO `tf_m_web_province` VALUES ('120000', '天津', '13', '2', null, null);
INSERT INTO `tf_m_web_province` VALUES ('130000', '河北', '18', '3', null, null);
INSERT INTO `tf_m_web_province` VALUES ('140000', '山西', '19', '4', null, null);
INSERT INTO `tf_m_web_province` VALUES ('150000', '内蒙古', '10', '5', null, null);
INSERT INTO `tf_m_web_province` VALUES ('210000', '辽宁', '91', '6', null, null);
INSERT INTO `tf_m_web_province` VALUES ('220000', '吉林', '90', '7', null, null);
INSERT INTO `tf_m_web_province` VALUES ('230000', '黑龙江', '97', '8', null, null);
INSERT INTO `tf_m_web_province` VALUES ('310000', '上海', '31', '9', null, null);
INSERT INTO `tf_m_web_province` VALUES ('320000', '江苏', '34', '10', null, null);
INSERT INTO `tf_m_web_province` VALUES ('330000', '浙江', '36', '11', null, null);
INSERT INTO `tf_m_web_province` VALUES ('340000', '安徽', '30', '12', null, null);
INSERT INTO `tf_m_web_province` VALUES ('350000', '福建', '38', '13', null, null);
INSERT INTO `tf_m_web_province` VALUES ('360000', '江西', '75', '14', null, null);
INSERT INTO `tf_m_web_province` VALUES ('370000', '山东', '17', '15', null, null);
INSERT INTO `tf_m_web_province` VALUES ('410000', '河南', '76', '16', null, null);
INSERT INTO `tf_m_web_province` VALUES ('420000', '湖北', '71', '17', null, null);
INSERT INTO `tf_m_web_province` VALUES ('430000', '湖南', '74', '18', null, null);
INSERT INTO `tf_m_web_province` VALUES ('440000', '广东', '51', '19', null, null);
INSERT INTO `tf_m_web_province` VALUES ('450000', '广西', '59', '20', null, null);
INSERT INTO `tf_m_web_province` VALUES ('460000', '海南', '50', '21', null, null);
INSERT INTO `tf_m_web_province` VALUES ('500000', '重庆', '83', '22', null, null);
INSERT INTO `tf_m_web_province` VALUES ('510000', '四川', '81', '23', null, null);
INSERT INTO `tf_m_web_province` VALUES ('520000', '贵州', '85', '24', null, null);
INSERT INTO `tf_m_web_province` VALUES ('530000', '云南', '86', '25', null, null);
INSERT INTO `tf_m_web_province` VALUES ('540000', '西藏', '79', '26', null, null);
INSERT INTO `tf_m_web_province` VALUES ('610000', '陕西', '84', '27', null, null);
INSERT INTO `tf_m_web_province` VALUES ('620000', '甘肃', '87', '28', null, null);
INSERT INTO `tf_m_web_province` VALUES ('630000', '青海', '70', '29', null, null);
INSERT INTO `tf_m_web_province` VALUES ('640000', '宁夏', '88', '30', null, null);
INSERT INTO `tf_m_web_province` VALUES ('650000', '新疆', '89', '31', null, null);
