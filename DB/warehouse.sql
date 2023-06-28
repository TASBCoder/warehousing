/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : warehouse

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2023-06-28 08:13:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `identify` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份识别',
  `name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` float(8,3) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for ledgers
-- ----------------------------
DROP TABLE IF EXISTS `ledgers`;
CREATE TABLE `ledgers` (
  `id` int(20) NOT NULL,
  `good_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `good_identify` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '商品唯一标识',
  `serial_numbe` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '流水号',
  `number` int(11) NOT NULL,
  `store` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `operator_id` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `operator` tinyint(4) NOT NULL COMMENT '0代表入库，1代表出库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(20) NOT NULL,
  `out_time` datetime NOT NULL,
  `content` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `message_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '"0 代表未读，1代表已读“',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
