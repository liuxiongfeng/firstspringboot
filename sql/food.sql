/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : food

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-07-25 15:13:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for keysbase
-- ----------------------------
DROP TABLE IF EXISTS `keysbase`;
CREATE TABLE `keysbase` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keysbase
-- ----------------------------
INSERT INTO `keysbase` VALUES ('3', 'd', 'sdsdsd');
INSERT INTO `keysbase` VALUES ('4', 'd', 'sdsdsd');
INSERT INTO `keysbase` VALUES ('6', 'd', 'sdsdsd');
INSERT INTO `keysbase` VALUES ('7', '填写预约申报', 'tianxieyuyueshenbao');
INSERT INTO `keysbase` VALUES ('8', 'd', 'sdsdsd');
INSERT INTO `keysbase` VALUES ('9', 'wwww', 'sdsdsd');
INSERT INTO `keysbase` VALUES ('10', '打电话给张三', 'dadianhuageizhangsan');
INSERT INTO `keysbase` VALUES ('11', '发微信给赵四', 'faweixingeizhosi');
INSERT INTO `keysbase` VALUES ('12', '查看资产大于50万的客户', 'chakanzicdayu50dekehu');
INSERT INTO `keysbase` VALUES ('13', '填写服务记录', 'tianxiefuwujilu');
INSERT INTO `keysbase` VALUES ('14', '打电话给赵四', 'ddhgzs');
INSERT INTO `keysbase` VALUES ('15', '赵四的服务记录', 'zsdfwjl');
INSERT INTO `keysbase` VALUES ('17', 'sdssd', null);
