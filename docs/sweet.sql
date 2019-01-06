/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sweet

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-12-25 22:23:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `consultation_record`
-- ----------------------------
DROP TABLE IF EXISTS `consultation_record`;
CREATE TABLE `consultation_record` (
  `id` int(32) NOT NULL,
  `problem_description` text COMMENT '问题描述',
  `processing_method` text COMMENT '处理方法',
  `consult_department` varchar(100) DEFAULT NULL COMMENT '咨询部门',
  `consultant` varchar(100) DEFAULT NULL COMMENT '咨询人',
  `system_platform` varchar(50) DEFAULT NULL COMMENT '系统平台',
  `brand_model` varchar(20) DEFAULT NULL COMMENT '品牌机型',
  `consult_date` date DEFAULT NULL,
  `recorder` varchar(50) DEFAULT NULL COMMENT '记录人',
  `record_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of consultation_record
-- ----------------------------
INSERT INTO `consultation_record` VALUES ('1', '问题描述', '处理方法', '咨询部门', '咨询人', '系统平台', '品牌机型', '2018-12-25', '记录人', '2018-12-25');
