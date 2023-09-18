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

/*Data for the table `city` */

LOCK TABLES `city` WRITE;

insert  into `city`(`id`,`pid`,`code`,`name`) values (0,0,0,'test'),(1,1,1,'成都市'),(2,1,2,'雅安市'),(3,1,3,'绵阳市'),(4,1,4,'甘孜州'),(5,2,2,'林芝'),(6,1,5,'凉山州'),(7,1,6,'攀枝花'),(8,1,8,'阿坝州'),(9,3,5,'云南个旧市\r\n'),(10,1,9,'乐山'),(11,3,3,'云南昭通市\r\n'),(12,3,2,'云南楚雄州'),(13,3,6,'云南怒江'),(14,3,8,'云南保山市'),(15,3,9,'云南大理州'),(16,3,7,'云南文山市');

UNLOCK TABLES;

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

/*Data for the table `device` */

LOCK TABLES `device` WRITE;

insert  into `device`(`id`,`sid`,`clientid`,`name`,`addresscode`,`detail`,`state`,`machineno`,`chan1`,`chan2`,`chan3`,`chan4`,`chan5`,`chan6`,`chan7`,`chan8`,`chan9`,`chan10`,`chan11`,`chan12`,`chan13`,`chan14`,`chan15`,`chan16`,`key1`,`key2`,`key3`,`key4`,`key5`,`key6`,`key7`,`key8`,`key9`,`key10`,`key11`,`key12`,`key13`,`key14`,`key15`,`key16`,`zero1`,`zero2`,`zero3`,`zero4`,`zero5`,`zero6`,`zero7`,`zero8`,`zero9`,`zero10`,`zero11`,`zero12`,`zero13`,`zero14`,`zero15`,`zero16`,`lx1`,`lx2`,`lx3`,`lx4`,`lx5`,`lx6`,`lx7`,`lx8`,`lx9`,`lx10`,`lx11`,`lx12`,`lx13`,`lx14`,`lx15`,`lx16`) values (0,0,1,'test',2,'test',1,0,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,432.258,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-40,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(1,2,2,'四川梅塞尔气体产品有限公司乐山分公司\r\n',2,'test2',1,1,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,2589,432.258,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12910,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-40,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(2,1,1,'个旧市大屯镇现代石油液化气储备站\r\n',2,'test',1,2,2,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,20706.8,432.258,3,4,5,6,7,8,9,10,11,12,13,14,15,16,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-40,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(3,4,2,'大关县液化气有限责任公司\r\n',2,'test',1,3,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(4,5,2,'彝良县益民供气站\r\n',2,'test',1,4,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(5,6,2,'昭通市国泰燃气有限公司\r\n',2,'test',1,5,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(6,7,2,'镇雄县五谷液化气充装有限责任公司\r\n',2,'test',1,6,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(7,8,2,'盐津金晟燃气有限责任公司\r\n',2,'test',1,7,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(8,3,2,'威信县民生液化石油气充装站\r\n',2,'test',1,8,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(9,9,2,'威信县宏发石油液化气充装站\r\n',2,'test',1,9,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10,10,2,'昭通市绥江科光液化石油气充装站\r\n',2,'test',1,10,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(11,11,2,'永善县溪洛渡液化气储备充装站',2,'test',1,11,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(12,12,2,'彝良县月华气体有限责任公司',2,'test',1,12,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(13,13,2,'巧家新瑞城镇燃气有限责任公司',2,'test',1,13,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(14,14,2,'水富志宏燃气有限公司',2,'test',1,14,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.36,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(15,15,12,'南华县兰铁燃气有限公司',2,'南华县兰铁燃气有限公司',1,15,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(16,19,12,'恕江州泸鑫燃气有限公司',2,'恕江州泸鑫燃气有限公司',1,16,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(17,23,12,'楚雄龙图燃气有限公司',2,'楚雄龙图燃气有限公司',1,17,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(18,24,12,'牟定县龙图液化气储备加气站',2,'牟定县龙图液化气储备加气站',1,18,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10025,22,12,'云南楚雄州众恒石化燃料有限公司',2,'云南楚雄州众恒石化燃料有限公司',1,19,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10026,26,12,'云南保山交通运输集团有限责任公司腾冲交通石化分公司',2,'云南保山交通运输集团有限责任公司腾冲交通石化分公司',1,22,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10027,27,12,'福贡大众燃气有限公司',2,'福贡大众燃气有限公司',1,21,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10028,28,2,'大理州百联燃气有限公司',2,'大理州百联燃气有限公司',1,24,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10029,29,2,'云南国星能源有限公司砚山分公司',2,'云南国星能源有限公司砚山分公司',1,23,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10030,30,2,'广南广旺液化气经营有限公司',2,'广南广旺液化气经营有限公司',1,25,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,258.6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10031,31,2,'富宁日升燃气有限公司',2,'富宁日升燃气有限公司',1,26,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10032,32,2,'西畴县旭飞燃气销售有限公司',2,NULL,1,27,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,264,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10033,36,2,'西畴盛奥能源有限公司',2,NULL,1,28,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10034,33,2,'文山市桂燃液化气销售有限公司',2,NULL,1,29,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,256.2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10035,34,2,'文山州俊成燃气有限责任公司',2,NULL,1,30,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259.1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10036,35,2,'文山市静燃燃气销售有限公司',2,NULL,1,31,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.6,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10037,37,2,'丘北县燃气有限公司',2,'丘北县燃气有限公司',1,32,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,257.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10038,38,2,'丘北蓝焰综合气体有限责任公司',2,'丘北蓝焰综合气体有限责任公司',1,33,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,257,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10039,39,2,'富宁县解化中民清洁能源有限公司',2,'富宁县解化中民清洁能源有限公司',1,34,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,260.3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(10040,40,2,'砚山县平远丰盛液化气库',2,'砚山县平远丰盛液化气库',1,35,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20706.8,259,1,1,1,1,1,1,1,1,1,1,1,1,1,1,12990,12965,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-100,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

UNLOCK TABLES;

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` int(32) DEFAULT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `province` */

LOCK TABLES `province` WRITE;

insert  into `province`(`id`,`code`,`name`) values (0,0,'test'),(1,10,'四川省'),(2,11,'西藏'),(3,12,'云南');

UNLOCK TABLES;

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

/*Data for the table `station` */

LOCK TABLES `station` WRITE;

insert  into `station`(`id`,`cid`,`code`,`name`,`address`,`detail`,`zdbh`) values (0,0,'0','test','test','test','zdbh'),(1,9,'80','个旧市大屯镇现代石油液化气储备站\r\n','test','test','test'),(2,10,'5','四川梅塞尔气体产品有限公司乐山分公司\r\n','test','test','test'),(3,11,'1','威信县民生液化石油气充装站\r\n','test','test','test'),(4,11,'2','大关县液化气有限责任公司\r\n','test','test','test'),(5,11,'3','彝良县益民供气站\r\n','test','test','test'),(6,11,'4','昭通市国泰燃气有限公司\r\n','test','test','test'),(7,11,'5','镇雄县五谷液化气充装有限责任公司\r\n','test','test','test'),(8,11,'6','盐津金晟燃气有限责任公司\r\n','test','test','test'),(9,11,'7','威信县宏发石油液化气充装站\r\n','test','test','test'),(10,11,'8','昭通市绥江科光液化石油气充装站\r\n','test','test','test'),(11,11,'9','永善县溪洛渡液化气储备充装站','test','test','test'),(12,11,'10','彝良县月华气体有限责任公司',NULL,NULL,NULL),(13,11,'11','巧家新瑞城镇燃气有限责任公司',NULL,NULL,NULL),(14,11,'12','水富志宏燃气有限公司',NULL,'120312','1111'),(15,12,'6','南华县兰铁燃气有限公司','南华县兰铁燃气有限公司','120206','1111'),(19,13,'2','怒江州泸鑫燃气有限公司','恕江州泸鑫燃气有限公司','120602','1111'),(22,12,'5','云南楚雄州众恒石化燃料有限公司','云南楚雄州众恒石化燃料有限公司','120205','1111'),(23,12,'9','楚雄龙图燃气有限公司','楚雄龙图燃气有限公司','120209','1111'),(24,12,'1','牟定县龙图液化气储备加气站','牟定县龙图液化气储备加气站','120201','1111'),(26,14,'1','云南保山交通运输集团有限责任公司腾冲交通石化分公司',NULL,'120801',NULL),(27,13,'3','福贡大众燃气有限公司',NULL,'120603',NULL),(28,15,'1','大理州百联燃气有限公司','120901',NULL,NULL),(29,16,'2','云南国星能源有限公司砚山分公司','120702',NULL,NULL),(30,16,'3','广南广旺液化气经营有限公司','120703',NULL,NULL),(31,16,'4','富宁日升燃气有限公司','120704',NULL,NULL),(32,16,'5','西畴县旭飞燃气销售有限公司','120705',NULL,NULL),(33,16,'7','文山市桂燃液化气销售有限公司','120707',NULL,NULL),(34,16,'8','文山州俊成燃气有限责任公司','120708',NULL,NULL),(35,16,'9','文山市静燃燃气销售有限公司','120709',NULL,NULL),(36,16,'6','西畴盛奥能源有限公司','120706',NULL,NULL),(37,16,'10','丘北县燃气有限公司','120710',NULL,NULL),(38,16,'11','丘北蓝焰综合气体有限责任公司','120711',NULL,NULL),(39,16,'12','富宁县解化中民清洁能源有限公司','120712',NULL,NULL),(40,16,'13','砚山县平远丰盛液化气库','120713',NULL,NULL);

UNLOCK TABLES;

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

/*Data for the table `tblclient` */

LOCK TABLES `tblclient` WRITE;

insert  into `tblclient`(`id`,`name`,`tel`,`address`,`unit`,`post`,`detail`,`checkurl`,`posturl`,`state`) values (1,'aaa','1234','cd','sc','610001','科宏','','http://trace.zxwis.com/api/blade-filling/thrid/jbr/site/options',1),(2,'bbb','1234','cd','sc','610000','佳贝尔',NULL,'https://jblgas.cn/jbr/collect',1),(12,'ccc','123','cd','sc','11111','君则系统',NULL,'https://123.com',1);

UNLOCK TABLES;

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

/*Data for the table `tbldevicelog` */

LOCK TABLES `tbldevicelog` WRITE;

UNLOCK TABLES;

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

/*Data for the table `tblvalues` */

LOCK TABLES `tblvalues` WRITE;

insert  into `tblvalues`(`id`,`addresscode`,`temperature`,`pressure`,`detail`,`recordtime`) values (1,'1226','26.6','0.78','南华县兰铁燃气有限公司','2023-09-15 17:25:39'),(2,'1262','0','0','恕江州泸鑫燃气有限公司',NULL),(3,'1221','24.1','0.58','','2023-09-15 17:49:52'),(4,'1225','26.7','0.58','','2023-09-15 17:49:42'),(5,'1229','27.3','0.82','','2023-09-15 17:50:03'),(6,'1263','33.2','0.59','福贡大众燃气有限公司','2023-09-15 17:49:42'),(7,'1281','27.1','0.71','云南保山交通运输集团有限责任公司腾冲交通石化分公司','2023-09-15 17:26:22');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
