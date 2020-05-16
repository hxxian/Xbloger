/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : xbloger

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2020-05-16 09:51:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_info
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `type_id` int(11) NOT NULL COMMENT '文章类型ID',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '文章标题',
  `content` text NOT NULL COMMENT '文章内容',
  `digest` tinytext NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_type_id` (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_info
-- ----------------------------
INSERT INTO `article_info` VALUES ('1', '1', 'test title', 'test content', 'test digest', '2020-05-13 23:38:00', '2020-05-13 23:38:00');

-- ----------------------------
-- Table structure for article_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `article_snapshot`;
CREATE TABLE `article_snapshot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '快照ID',
  `aid` bigint(20) NOT NULL COMMENT '文章ID',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `digest` tinytext NOT NULL COMMENT '文章摘要',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `read_count` int(11) NOT NULL DEFAULT '0' COMMENT '阅读数',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `show_state` enum('1','0') NOT NULL DEFAULT '0' COMMENT '文章显示状态',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_snapshot
-- ----------------------------
INSERT INTO `article_snapshot` VALUES ('1', '1', 'test title', 'test digest', '2020-05-13 23:38:00', '0', '0', '0', '2020-05-13 23:38:08', '2020-05-13 23:38:08');

-- ----------------------------
-- Table structure for article_type
-- ----------------------------
DROP TABLE IF EXISTS `article_type`;
CREATE TABLE `article_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL COMMENT '类型名',
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_type
-- ----------------------------
INSERT INTO `article_type` VALUES ('1', '后端场景设计', '2020-05-15 00:03:17', '2020-05-15 00:03:17');
INSERT INTO `article_type` VALUES ('2', '读书修养', '2020-05-15 00:06:58', '2020-05-15 00:06:58');

-- ----------------------------
-- Table structure for avatar
-- ----------------------------
DROP TABLE IF EXISTS `avatar`;
CREATE TABLE `avatar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(1024) NOT NULL COMMENT '头像url',
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of avatar
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aid` bigint(20) NOT NULL,
  `avatar_url` varchar(1024) NOT NULL COMMENT 't头像URL',
  `nickname` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `website` varchar(1024) DEFAULT NULL COMMENT '评论者站点',
  `ip_addr` char(15) NOT NULL COMMENT '评论者ip',
  `comment` text NOT NULL COMMENT '评论内容',
  `show_state` enum('1','0') NOT NULL DEFAULT '0' COMMENT '显示状态',
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diary
-- ----------------------------

-- ----------------------------
-- Table structure for site_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `site_snapshot`;
CREATE TABLE `site_snapshot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_count` int(11) NOT NULL DEFAULT '0' COMMENT '总访问次数',
  `comment_count` int(11) NOT NULL DEFAULT '0' COMMENT '总评论次数',
  `article_count` int(11) NOT NULL DEFAULT '0' COMMENT '文章总数',
  `founding_time` datetime NOT NULL COMMENT '成立时间',
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(1024) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_update` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
