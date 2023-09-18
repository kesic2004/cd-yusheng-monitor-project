/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 8.0.18 : Database - monitor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`monitor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `monitor`;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `pid` int(11) DEFAULT NULL COMMENT '省id',
  `code` int(32) DEFAULT NULL COMMENT '编码',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `sid` int(32) DEFAULT NULL COMMENT 'station序号',
  `clientid` int(32) DEFAULT '0' COMMENT '数据上报client',
  `name` varchar(128) DEFAULT NULL COMMENT '设备名',
  `addresscode` int(32) DEFAULT NULL COMMENT '地址码',
  `detail` varchar(128) DEFAULT NULL COMMENT '备注',
  `state` int(32) DEFAULT NULL COMMENT '状态:1-有效，0-无效',
  `machineno` int(32) DEFAULT NULL COMMENT '机器码',
  `chan1` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan2` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan3` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan4` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan5` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan6` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan7` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan8` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan9` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan10` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan11` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan12` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan13` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan14` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan15` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `chan16` int(11) DEFAULT '0' COMMENT '1-温度，2-压力',
  `key1` float DEFAULT '1' COMMENT '系数',
  `key2` float DEFAULT '1' COMMENT '系数',
  `key3` float DEFAULT '1' COMMENT '系数',
  `key4` float DEFAULT '1' COMMENT '系数',
  `key5` float DEFAULT '1' COMMENT '系数',
  `key6` float DEFAULT '1' COMMENT '系数',
  `key7` float DEFAULT '1' COMMENT '系数',
  `key8` float DEFAULT '1' COMMENT '系数',
  `key9` float DEFAULT '1' COMMENT '系数',
  `key10` float DEFAULT '1' COMMENT '系数',
  `key11` float DEFAULT '1' COMMENT '系数',
  `key12` float DEFAULT '1' COMMENT '系数',
  `key13` float DEFAULT '1' COMMENT '系数',
  `key14` float DEFAULT '1' COMMENT '系数',
  `key15` float DEFAULT '1' COMMENT '系数',
  `key16` float DEFAULT '1' COMMENT '系数',
  `zero1` float DEFAULT '0' COMMENT '通道零点',
  `zero2` float DEFAULT '0' COMMENT '通道零点',
  `zero3` float DEFAULT '0' COMMENT '通道零点',
  `zero4` float DEFAULT '0' COMMENT '通道零点',
  `zero5` float DEFAULT '0' COMMENT '通道零点',
  `zero6` float DEFAULT '0' COMMENT '通道零点',
  `zero7` float DEFAULT '0' COMMENT '通道零点',
  `zero8` float DEFAULT '0' COMMENT '通道零点',
  `zero9` float DEFAULT '0' COMMENT '通道零点',
  `zero10` float DEFAULT '0' COMMENT '通道零点',
  `zero11` float DEFAULT '0' COMMENT '通道零点',
  `zero12` float DEFAULT '0' COMMENT '通道零点',
  `zero13` float DEFAULT '0' COMMENT '通道零点',
  `zero14` float DEFAULT '0' COMMENT '通道零点',
  `zero15` float DEFAULT '0' COMMENT '通道零点',
  `zero16` float DEFAULT '0' COMMENT '通道零点',
  `lx1` float DEFAULT '0' COMMENT '量程',
  `lx2` float DEFAULT '0' COMMENT '量程',
  `lx3` float DEFAULT '0' COMMENT '量程',
  `lx4` float DEFAULT '0' COMMENT '量程',
  `lx5` float DEFAULT '0' COMMENT '量程',
  `lx6` float DEFAULT '0' COMMENT '量程',
  `lx7` float DEFAULT '0' COMMENT '量程',
  `lx8` float DEFAULT '0' COMMENT '量程',
  `lx9` float DEFAULT '0' COMMENT '量程',
  `lx10` float DEFAULT '0' COMMENT '量程',
  `lx11` float DEFAULT '0' COMMENT '量程',
  `lx12` float DEFAULT '0' COMMENT '量程',
  `lx13` float DEFAULT '0' COMMENT '量程',
  `lx14` float DEFAULT '0' COMMENT '量程',
  `lx15` float DEFAULT '0' COMMENT '量程',
  `lx16` float DEFAULT '0' COMMENT '量程',
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10041 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` int(32) DEFAULT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `station` */

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cid` int(32) DEFAULT NULL COMMENT 'city序号',
  `code` varchar(32) DEFAULT NULL COMMENT '站点编号',
  `name` varchar(128) DEFAULT NULL COMMENT '站点名',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `detail` varchar(256) DEFAULT NULL COMMENT '备注',
  `zdbh` varchar(128) DEFAULT NULL COMMENT '自定义站点编号',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `station_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tblclient` */

DROP TABLE IF EXISTS `tblclient`;

CREATE TABLE `tblclient` (
  `id` int(32) NOT NULL COMMENT '序号',
  `name` varchar(128) DEFAULT NULL COMMENT '姓名',
  `tel` varchar(128) DEFAULT NULL COMMENT '电话',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `unit` varchar(128) DEFAULT NULL COMMENT '单位',
  `post` varchar(128) DEFAULT NULL COMMENT '邮编',
  `detail` varchar(128) DEFAULT NULL COMMENT '备注',
  `checkurl` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '审核链接',
  `posturl` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '提交数据链接',
  `state` int(32) DEFAULT NULL COMMENT '状态:1-有效，0-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tbldevicelog` */

DROP TABLE IF EXISTS `tbldevicelog`;

CREATE TABLE `tbldevicelog` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `addresscode` int(32) DEFAULT NULL COMMENT '地址码',
  `provincecode` int(32) DEFAULT NULL COMMENT '省份编码',
  `citycode` int(32) DEFAULT NULL COMMENT '城市编码',
  `stationcode` int(32) DEFAULT NULL COMMENT '气站编码',
  `qrcode` varchar(32) DEFAULT NULL COMMENT '二维码字符串',
  `recordtime` timestamp NULL DEFAULT NULL COMMENT '记录时间',
  `state` int(32) DEFAULT NULL COMMENT '1-超期码,2-非本站瓶码,85-停止灌装,170-正常起动',
  `machineno` int(32) DEFAULT NULL COMMENT '机器码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tblvalues` */

DROP TABLE IF EXISTS `tblvalues`;

CREATE TABLE `tblvalues` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addresscode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省码+市码+站点码',
  `temperature` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '温度值',
  `pressure` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '压力值',
  `detail` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `recordtime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
