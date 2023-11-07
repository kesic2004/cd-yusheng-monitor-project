CREATE DATABASE `gases`;

USE `gases`;

/*Table structure for table `province` */

DROP TABLE IF EXISTS `province`;

CREATE TABLE `province` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `code` int(32) DEFAULT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `station` */

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cid` int(32) DEFAULT NULL COMMENT 'city序号',
  `code` varchar(32) DEFAULT NULL COMMENT '站点编号',
  `name` varchar(128) DEFAULT NULL COMMENT '站点名',
  `address` varchar(128) DEFAULT NULL COMMENT '地址',
  `detail` varchar(256) DEFAULT NULL COMMENT '备注',
  `stype` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `station_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10033 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fj_dw` */

DROP TABLE IF EXISTS `fj_dw`;

CREATE TABLE `fj_dw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationname` varchar(128) DEFAULT NULL,
  `platformurl` varchar(256) DEFAULT NULL,
  `token` varchar(128) DEFAULT NULL,
  `jblstationcode` varchar(128) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `sysuser` */

DROP TABLE IF EXISTS `sysuser`;

CREATE TABLE `sysuser` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Photo` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Synopsis` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Gender` int(11) DEFAULT NULL,
  `SigninedTime` datetime(6) NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `code` int(11) NOT NULL DEFAULT '0',
  `NetCalcMode` int(11) NOT NULL,
  `DegrossMode` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=383 DEFAULT CHARSET=utf8;

/*Table structure for table `printreceipt` */

DROP TABLE IF EXISTS `printreceipt`;

CREATE TABLE `printreceipt` (
  `prid` int(11) NOT NULL AUTO_INCREMENT,
  `condKey` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `condJson` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `clientid` int(11) NOT NULL,
  `clientName` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `discount` double NOT NULL,
  `price` double NOT NULL,
  `userId` int(11) NOT NULL,
  `username` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `stationId` int(11) NOT NULL,
  `stationName` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `created` datetime(6) NOT NULL,
  `netCalcMode` int(11) NOT NULL,
  `receiptCode` varchar(30) DEFAULT NULL,
  `printedCount` int(11) NOT NULL,
  `totalCount` int(11) NOT NULL COMMENT '打印总次数',
  `totalWeight` int(11) NOT NULL COMMENT '打印总重量',
  `invalid` int(11) NOT NULL COMMENT '是否作废, 1表示作废',
  `invalidReason` varchar(200) DEFAULT NULL COMMENT '作废理由',
  `amounts` double NOT NULL COMMENT '打印总金额',
  `actualAmounts` double NOT NULL,
  PRIMARY KEY (`prid`)
) ENGINE=InnoDB AUTO_INCREMENT=381 DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Table structure for table `security` */

DROP TABLE IF EXISTS `security`;

CREATE TABLE `security` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Icon` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Target` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Type` int(11) DEFAULT NULL,
  `Authorize` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Sequence` int(11) NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `ParentId` int(11) DEFAULT NULL,
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_Security_ParentId` (`ParentId`),
  CONSTRAINT `FK_Security_Security_ParentId` FOREIGN KEY (`ParentId`) REFERENCES `security` (`Id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=40032 DEFAULT CHARSET=utf8;

/*Table structure for table `systemdatacategory` */

DROP TABLE IF EXISTS `systemdatacategory`;

CREATE TABLE `systemdatacategory` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Sequence` int(11) NOT NULL,
  `HigherId` int(11) DEFAULT NULL,
  `Enabled` tinyint(1) NOT NULL,
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `stationid` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_SystemDataCategory_HigherId` (`HigherId`),
  CONSTRAINT `FK_SystemDataCategory_SystemDataCategory_HigherId` FOREIGN KEY (`HigherId`) REFERENCES `systemdatacategory` (`Id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

/*Table structure for table `xk_dw` */

DROP TABLE IF EXISTS `xk_dw`;

CREATE TABLE `xk_dw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stationCode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位编号',
  `name` varchar(64) DEFAULT NULL COMMENT '单位名称',
  `userCode` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户编号',
  `userPass` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户口令',
  `bz` varchar(128) DEFAULT NULL COMMENT '备注',
  `updateAtonce` int(11) DEFAULT '0' COMMENT '立即更新（1-立即更新，0-不立即更新）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `__efmigrationshistory` */

DROP TABLE IF EXISTS `__efmigrationshistory`;

CREATE TABLE `__efmigrationshistory` (
  `MigrationId` varchar(95) NOT NULL,
  `ProductVersion` varchar(32) NOT NULL,
  PRIMARY KEY (`MigrationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `allocdelivery` */

DROP TABLE IF EXISTS `allocdelivery`;

CREATE TABLE `allocdelivery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `storageid` int(11) NOT NULL COMMENT '调拨出库单id',
  `deliveryno` varchar(20) NOT NULL COMMENT '调拨配送单单号',
  `outtotals` int(11) NOT NULL COMMENT '配送气瓶总数量',
  `intotals` int(11) NOT NULL COMMENT '回收气瓶总数量',
  `clientid` int(11) DEFAULT NULL COMMENT '主要调出去向用户',
  `clientname` varchar(100) DEFAULT NULL COMMENT '主要调出去向客户名称',
  `clientagent` int(11) NOT NULL DEFAULT '0' COMMENT '或为中间商1或为终端客户0',
  `outqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单去向表',
  `inqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单回收表',
  `address` varchar(200) DEFAULT NULL COMMENT '交易地址',
  `longitude` varchar(30) DEFAULT NULL COMMENT '配送地经度',
  `latitude` varchar(30) DEFAULT NULL COMMENT '配送地纬度',
  `signature` text COMMENT '客户签名',
  `imgarchiveid` varchar(100) DEFAULT NULL COMMENT '配送现场照片',
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) NOT NULL,
  `modified` datetime DEFAULT NULL COMMENT '配送签收时间',
  `modifierid` int(11) DEFAULT NULL COMMENT '配送签收人员id',
  `modifiername` varchar(20) DEFAULT NULL COMMENT '配送签收人员姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `deliveryno` (`deliveryno`)
) ENGINE=InnoDB AUTO_INCREMENT=16239 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `allocdirection` */

DROP TABLE IF EXISTS `allocdirection`;

CREATE TABLE `allocdirection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `storageid` int(11) NOT NULL COMMENT '出库单Id',
  `deliveryid` int(11) NOT NULL DEFAULT '0' COMMENT '调拨单配送Id',
  `endclientid` int(11) NOT NULL DEFAULT '0' COMMENT '调拨单配送，配送到端Id',
  `gastankid` int(11) NOT NULL,
  `productno` varchar(20) NOT NULL,
  `qrcode` varchar(20) NOT NULL,
  `norms` varchar(20) NOT NULL COMMENT '气瓶型号',
  `specs` float(6,2) DEFAULT '0.00' COMMENT '气瓶规格',
  `deliverstate` int(11) NOT NULL COMMENT '气瓶交付状态查看程序',
  `agentid` int(11) NOT NULL DEFAULT '0' COMMENT '代理商id',
  `agentname` varchar(20) DEFAULT NULL COMMENT '代理商名称',
  `clientid` int(11) NOT NULL DEFAULT '0' COMMENT '去向或来自客户Id',
  `clientname` varchar(100) DEFAULT NULL COMMENT '去向或来自客户名称',
  `clientagent` int(11) NOT NULL DEFAULT '0',
  `deliveried` datetime DEFAULT NULL COMMENT '配送完成时间',
  `deliverierid` int(11) DEFAULT NULL COMMENT '配送人员',
  `deliveriername` varchar(20) DEFAULT NULL COMMENT '配送人员名称',
  `assembled` datetime DEFAULT NULL COMMENT '配送到端时间',
  `assemblerid` int(11) DEFAULT NULL COMMENT '送气工id',
  `assemblername` varchar(20) DEFAULT NULL COMMENT '配送人员名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=288170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `allocendclient` */

DROP TABLE IF EXISTS `allocendclient`;

CREATE TABLE `allocendclient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `agentid` int(11) NOT NULL,
  `endclientno` varchar(20) NOT NULL COMMENT '调拨到端单单号',
  `outtotals` int(11) NOT NULL COMMENT '配送到终端用户总数量',
  `intotals` int(11) NOT NULL COMMENT '回收终端用户气瓶总数量',
  `clientid` int(11) DEFAULT NULL COMMENT '主要调出去向最终客户',
  `clientname` varchar(100) DEFAULT NULL COMMENT '主要调出去向终端客户名称',
  `contactman` varchar(20) NOT NULL COMMENT '客户联系人',
  `contacttel` varchar(20) NOT NULL COMMENT '客户联系人电话',
  `outqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单去向表',
  `inqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单回收表',
  `address` varchar(200) DEFAULT NULL COMMENT '交易地址',
  `longitude` varchar(30) DEFAULT NULL COMMENT '配送地经度',
  `latitude` varchar(30) DEFAULT NULL COMMENT '配送地纬度',
  `signature` text COMMENT '客户签名',
  `imgarchiveid` varchar(100) DEFAULT NULL COMMENT '配送现场照片',
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `endclientno` (`endclientno`)
) ENGINE=InnoDB AUTO_INCREMENT=54551 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `allocstorage` */

DROP TABLE IF EXISTS `allocstorage`;

CREATE TABLE `allocstorage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `storageno` varchar(20) NOT NULL COMMENT '调拨出库单单号',
  `deliverierid` int(11) NOT NULL COMMENT '调拨配送人员Id',
  `deliveriername` varchar(20) NOT NULL COMMENT '调拨配送人员姓名',
  `planouts` int(11) NOT NULL COMMENT '计划出库数量',
  `outtotals` int(11) NOT NULL DEFAULT '0' COMMENT '实际配送气瓶总数量',
  `intotals` int(11) NOT NULL DEFAULT '0' COMMENT '实际回收气瓶总数量',
  `vehicleid` int(11) DEFAULT NULL COMMENT '主要运输车辆Id',
  `vehiclenum` varchar(20) DEFAULT NULL COMMENT '主要运输车辆车牌号',
  `drivername` varchar(20) DEFAULT NULL COMMENT '默认司机',
  `drivertel` varchar(20) DEFAULT NULL COMMENT '司机电话',
  `planqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单气瓶',
  `outqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单气瓶',
  `inqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单回收气瓶',
  `undoqrcodes` text COMMENT '冗余字段用于记录气瓶的条码，对比调拨单未配送出去的气瓶',
  `clientid` int(11) DEFAULT NULL COMMENT '主要调出去向用户',
  `clientname` varchar(100) DEFAULT NULL COMMENT '主要调出去向客户名称',
  `contactman` varchar(20) NOT NULL COMMENT '客户联系人',
  `contacttel` varchar(20) NOT NULL COMMENT '客户联系人电话',
  `confirmtype` int(11) NOT NULL DEFAULT '0' COMMENT '门市部确认签收方式',
  `signature` text COMMENT '客户签名',
  `imgarchiveid` varchar(100) DEFAULT NULL COMMENT '配送现场照片',
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) NOT NULL,
  `modified` datetime DEFAULT NULL COMMENT '配送签收时间',
  `modifierid` int(11) DEFAULT NULL COMMENT '配送签收人员id',
  `modifiername` varchar(20) DEFAULT NULL COMMENT '配送签收人员姓名',
  `receipted` datetime DEFAULT NULL COMMENT '空瓶配送回执签收时间',
  `receiptid` int(11) DEFAULT NULL COMMENT '空瓶配送回执签收人员id',
  `receiptname` varchar(20) DEFAULT NULL COMMENT '空瓶配送回执签收人员姓名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `storageno` (`storageno`)
) ENGINE=InnoDB AUTO_INCREMENT=13840 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `apipendingtask` */

DROP TABLE IF EXISTS `apipendingtask`;

CREATE TABLE `apipendingtask` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objectid` int(11) NOT NULL COMMENT '等待办理的内容的Id',
  `provincecode` int(11) NOT NULL COMMENT '省编码',
  `citycode` int(11) NOT NULL COMMENT '市编码',
  `stationid` int(11) NOT NULL,
  `status` int(11) NOT NULL COMMENT '1表示完成，2-10表示需要人工协办，其他状态根据业务自定义',
  `postdata` text COMMENT '等待POST发送的内容',
  `eventcode` int(11) NOT NULL DEFAULT '0' COMMENT 'api对接事件code',
  `apicode1` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展1',
  `apicode2` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展2',
  `apicode3` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展3',
  `apicode4` longtext COMMENT 'api对接关键字扩展4，或存储回复信息',
  `apicode5` longtext COMMENT 'api对接关键字扩展5，或存储已读照片的信息',
  `created` datetime NOT NULL,
  `qrcode` varchar(32) DEFAULT NULL COMMENT '气瓶二维码',
  `productno` varchar(32) DEFAULT NULL COMMENT '气瓶钢瓶号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9828710 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `businessinfo` */

DROP TABLE IF EXISTS `businessinfo`;

CREATE TABLE `businessinfo` (
  `bsnid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `stationcode` int(11) NOT NULL,
  `provincecode` int(11) NOT NULL,
  `citycode` int(11) NOT NULL,
  `corpname` varchar(300) NOT NULL,
  `creditcode` varchar(32) NOT NULL,
  `legalname` varchar(20) NOT NULL,
  `legalcardno` varchar(20) DEFAULT NULL,
  `contactno` varchar(20) DEFAULT NULL,
  `provincename` varchar(20) NOT NULL,
  `cityname` varchar(20) NOT NULL,
  `districtname` varchar(20) NOT NULL,
  `address` varchar(500) NOT NULL,
  `gps` varchar(100) DEFAULT NULL,
  `imgarchiveid` varchar(100) DEFAULT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  `apistatus` int(11) DEFAULT '0',
  PRIMARY KEY (`bsnid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `clientuser` */

DROP TABLE IF EXISTS `clientuser`;

CREATE TABLE `clientuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '上级经销商，只能由agent=1创建agent=0的终端客户',
  `clientcode` int(11) NOT NULL COMMENT '客户编码',
  `clientname` varchar(100) NOT NULL COMMENT '客户名称',
  `clienttype` varchar(100) NOT NULL COMMENT '客户类型字典表',
  `clientagent` int(11) NOT NULL DEFAULT '0' COMMENT '客户是否为经销商门店或终端用户,1门店、0终端或最终用户',
  `fullname` varchar(200) DEFAULT NULL COMMENT '客户全称',
  `discount` decimal(10,2) NOT NULL COMMENT '折扣金额具体的金额',
  `contactman` varchar(20) NOT NULL COMMENT '客户联系人',
  `contacttel` varchar(20) NOT NULL COMMENT '客户联系人电话',
  `legalname` varchar(20) DEFAULT NULL COMMENT '客户法人',
  `legaltel` varchar(20) DEFAULT NULL COMMENT '法人电话',
  `licensename` varchar(100) DEFAULT NULL COMMENT '证书名称',
  `licenseno` varchar(100) DEFAULT NULL COMMENT '证书编号',
  `licenseexpired` datetime DEFAULT NULL COMMENT '证书有效期',
  `address` varchar(200) DEFAULT NULL COMMENT '机构地址',
  `province` varchar(30) DEFAULT NULL COMMENT '所属省',
  `city` varchar(30) DEFAULT NULL COMMENT '所属市/州',
  `county` varchar(30) DEFAULT NULL COMMENT '所属县',
  `longitude` varchar(30) DEFAULT NULL COMMENT '所在经度',
  `latitude` varchar(30) DEFAULT NULL COMMENT '所在维度',
  `certificateno` varchar(30) DEFAULT NULL COMMENT '营业执照或用户身份证号',
  `imgarchiveid` varchar(100) DEFAULT NULL COMMENT '证件照片',
  `userid` int(11) NOT NULL DEFAULT '0' COMMENT '送气工对应ID',
  `realname` varchar(20) DEFAULT NULL COMMENT '送气工真实姓名',
  `idcardno` varchar(20) DEFAULT NULL COMMENT '送气工身份证',
  `telnumber` varchar(20) DEFAULT NULL COMMENT '送气工联系方式',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `currentuser` */

DROP TABLE IF EXISTS `currentuser`;

CREATE TABLE `currentuser` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `province` int(32) DEFAULT NULL,
  `city` int(32) DEFAULT NULL,
  `station` int(32) DEFAULT NULL,
  `address` int(32) DEFAULT NULL,
  `qrcode` varchar(32) DEFAULT NULL,
  `recordtime` timestamp NULL DEFAULT NULL,
  `machineno` int(32) DEFAULT NULL COMMENT '机器码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9832027 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `deliverierclient` */

DROP TABLE IF EXISTS `deliverierclient`;

CREATE TABLE `deliverierclient` (
  `stationid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `clientid` int(11) NOT NULL,
  `realname` varchar(20) NOT NULL COMMENT '送气工真实姓名',
  `telnumber` varchar(20) DEFAULT NULL COMMENT '送气工联系方式',
  `idcardno` varchar(18) DEFAULT NULL COMMENT '送气工身份证',
  `position` int(11) NOT NULL DEFAULT '0' COMMENT '0表示门市送气工，1表示门市经理',
  `remark` varchar(200) DEFAULT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`stationid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `qrtype` int(32) DEFAULT '0' COMMENT '码类型0-二维码, 1-NFC',
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000481 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `errinfo` */

DROP TABLE IF EXISTS `errinfo`;

CREATE TABLE `errinfo` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `filestorage` */

DROP TABLE IF EXISTS `filestorage`;

CREATE TABLE `filestorage` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `GastankId` int(11) NOT NULL COMMENT '气瓶档案ID',
  `ArchiveId` int(11) NOT NULL COMMENT '附件ID',
  `StationId` int(11) NOT NULL COMMENT '站点',
  `GastankType` int(11) NOT NULL COMMENT '上传气瓶档案的类型',
  `FilePath` varchar(200) NOT NULL COMMENT '上传文件的路径',
  `FileName` varchar(200) NOT NULL COMMENT '返回上传的文件名',
  `FileUrl` varchar(200) NOT NULL COMMENT '返回上传的文件路径',
  `CreatedTime` datetime DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12532 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fillingcheckitem` */

DROP TABLE IF EXISTS `fillingcheckitem`;

CREATE TABLE `fillingcheckitem` (
  `fcitemid` int(11) NOT NULL AUTO_INCREMENT,
  `fillingcheckid` int(11) NOT NULL,
  `stationid` int(11) NOT NULL,
  `gastankid` int(11) NOT NULL,
  `checkop` int(11) NOT NULL,
  `checkitemid` int(11) NOT NULL,
  `checkitemname` varchar(20) NOT NULL,
  `itemqualified` int(11) NOT NULL,
  `checkdesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`fcitemid`),
  KEY `fillingcheckitem_fchecked` (`fillingcheckid`)
) ENGINE=InnoDB AUTO_INCREMENT=14264 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fillingcheckrecord` */

DROP TABLE IF EXISTS `fillingcheckrecord`;

CREATE TABLE `fillingcheckrecord` (
  `fcheckid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `gastankid` int(11) NOT NULL,
  `productno` varchar(20) NOT NULL,
  `qrcode` varchar(32) NOT NULL,
  `befqualified` int(11) NOT NULL DEFAULT (1),
  `befchecked` datetime NOT NULL,
  `befbatch` varchar(32) NOT NULL COMMENT '前检检查批次号',
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) NOT NULL,
  `aftqualified` int(11) NOT NULL DEFAULT (0),
  `aftchecked` datetime DEFAULT NULL,
  `aftbatch` varchar(32) DEFAULT NULL COMMENT '后检检查批次号',
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  `tbldataid` int(11) NOT NULL DEFAULT '0' COMMENT '充装Id，0表示没有进行充装，大于0表示充装编号',
  `curtime` datetime DEFAULT NULL COMMENT '气瓶充装时间',
  `recordtime` datetime DEFAULT NULL COMMENT '充装记录时间',
  `endweight` int(11) DEFAULT NULL COMMENT '结束重量',
  `grossweight` int(11) DEFAULT NULL COMMENT '皮重',
  `settingweight` int(11) DEFAULT NULL COMMENT '设重',
  `usedtime` int(11) DEFAULT NULL COMMENT '充装用时',
  `opername` varchar(20) DEFAULT NULL COMMENT '充装人员',
  `norms` varchar(20) DEFAULT NULL,
  `specs` float(6,2) DEFAULT '0.00' COMMENT '气瓶规格',
  `selfweight` float(6,2) DEFAULT '0.00' COMMENT '气瓶自重(KG)',
  `unitselfno` varchar(20) DEFAULT NULL,
  `bepressure` float(6,2) DEFAULT '0.00' COMMENT '充装开始压力',
  `betemperature` float(6,2) DEFAULT '0.00' COMMENT '充装开始温度',
  `endpressure` float(6,2) DEFAULT '0.00' COMMENT '充装结束压力',
  `endtemperature` float(6,2) DEFAULT '0.00' COMMENT '充装结束温度',
  `machineno` varchar(32) DEFAULT NULL,
  `befopername` varchar(20) DEFAULT NULL COMMENT '前检人名',
  `aftopername` varchar(20) DEFAULT NULL COMMENT '后检人名',
  `apistatus` int(11) DEFAULT '0' COMMENT '上报状态',
  `fillingauditid` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`fcheckid`),
  KEY `idx_fillingcheckrecord_qrcode` (`qrcode`),
  KEY `fillingcheckrecord_query` (`stationid`,`gastankid`,`befchecked`,`aftqualified`),
  KEY `fillingcheckrecord_post` (`stationid`,`aftqualified`,`apistatus`,`befchecked`)
) ENGINE=InnoDB AUTO_INCREMENT=1015983 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fillingcheckrecord_b2023` */

DROP TABLE IF EXISTS `fillingcheckrecord_b2023`;

CREATE TABLE `fillingcheckrecord_b2023` (
  `fcheckid` int(11) NOT NULL DEFAULT '0',
  `stationid` int(11) NOT NULL,
  `gastankid` int(11) NOT NULL,
  `productno` varchar(20) NOT NULL,
  `qrcode` varchar(32) NOT NULL,
  `befqualified` int(11) NOT NULL DEFAULT (1),
  `befchecked` datetime NOT NULL,
  `befbatch` varchar(32) NOT NULL COMMENT '前检检查批次号',
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) NOT NULL,
  `aftqualified` int(11) NOT NULL DEFAULT (0),
  `aftchecked` datetime DEFAULT NULL,
  `aftbatch` varchar(32) DEFAULT NULL COMMENT '后检检查批次号',
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  `tbldataid` int(11) NOT NULL DEFAULT '0' COMMENT '充装Id，0表示没有进行充装，大于0表示充装编号',
  `curtime` datetime DEFAULT NULL COMMENT '气瓶充装时间',
  `recordtime` datetime DEFAULT NULL COMMENT '充装记录时间',
  `endweight` int(11) DEFAULT NULL COMMENT '结束重量',
  `grossweight` int(11) DEFAULT NULL COMMENT '皮重',
  `settingweight` int(11) DEFAULT NULL COMMENT '设重',
  `usedtime` int(11) DEFAULT NULL COMMENT '充装用时',
  `opername` varchar(20) DEFAULT NULL COMMENT '充装人员',
  `norms` varchar(20) DEFAULT NULL,
  `specs` float(6,2) DEFAULT '0.00' COMMENT '气瓶规格',
  `selfweight` float(6,2) DEFAULT '0.00' COMMENT '气瓶自重(KG)',
  `unitselfno` varchar(20) DEFAULT NULL,
  `bepressure` float(6,2) DEFAULT '0.00' COMMENT '充装开始压力',
  `betemperature` float(6,2) DEFAULT '0.00' COMMENT '充装开始温度',
  `endpressure` float(6,2) DEFAULT '0.00' COMMENT '充装结束压力',
  `endtemperature` float(6,2) DEFAULT '0.00' COMMENT '充装结束温度',
  `machineno` varchar(32) DEFAULT NULL,
  `befopername` varchar(20) DEFAULT NULL COMMENT '前检人名',
  `aftopername` varchar(20) DEFAULT NULL COMMENT '后检人名',
  `apistatus` int(11) DEFAULT '0' COMMENT '上报状态',
  `fillingauditid` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fillingcheckuser` */

DROP TABLE IF EXISTS `fillingcheckuser`;

CREATE TABLE `fillingcheckuser` (
  `fcuserid` int(11) NOT NULL AUTO_INCREMENT,
  `fillingcheckid` int(11) NOT NULL,
  `stationid` int(11) NOT NULL,
  `gastankid` int(11) NOT NULL,
  `checkop` int(11) NOT NULL,
  `checkerid` int(11) NOT NULL,
  `checkername` varchar(20) NOT NULL,
  PRIMARY KEY (`fcuserid`),
  KEY `fcuser_leftjoin_idx` (`fillingcheckid`,`checkop`),
  KEY `fillingcheckuser_fchecked` (`fillingcheckid`)
) ENGINE=InnoDB AUTO_INCREMENT=2003715 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fj_bottle` */

DROP TABLE IF EXISTS `fj_bottle`;

CREATE TABLE `fj_bottle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(128) DEFAULT NULL,
  `printno` varchar(128) DEFAULT NULL,
  `style` varchar(128) DEFAULT NULL,
  `valve` int(11) DEFAULT NULL,
  `productdate` varchar(128) DEFAULT NULL,
  `discarddate` varchar(128) DEFAULT NULL,
  `checkdate` varchar(128) DEFAULT NULL,
  `nextcheckdate` varchar(128) DEFAULT NULL,
  `updatedate` varchar(128) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `systemid` int(11) DEFAULT NULL,
  `factoryid` int(11) DEFAULT NULL,
  `factoryname` varchar(128) DEFAULT NULL,
  `did` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `fj_bottle_ibfk_1` FOREIGN KEY (`did`) REFERENCES `fj_dw` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35746 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fj_bottle_bak` */

DROP TABLE IF EXISTS `fj_bottle_bak`;

CREATE TABLE `fj_bottle_bak` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(128) DEFAULT NULL,
  `printno` varchar(128) DEFAULT NULL,
  `style` varchar(128) DEFAULT NULL,
  `valve` int(11) DEFAULT NULL,
  `productdate` varchar(128) DEFAULT NULL,
  `discarddate` varchar(128) DEFAULT NULL,
  `checkdate` varchar(128) DEFAULT NULL,
  `nextcheckdate` varchar(128) DEFAULT NULL,
  `updatedate` varchar(128) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `systemid` int(11) DEFAULT NULL,
  `factoryid` int(11) DEFAULT NULL,
  `factoryname` varchar(128) DEFAULT NULL,
  `did` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `fj_bottle_bak_ibfk_1` FOREIGN KEY (`did`) REFERENCES `fj_dw` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5526 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fj_log` */

DROP TABLE IF EXISTS `fj_log`;

CREATE TABLE `fj_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(128) DEFAULT NULL,
  `detail` varchar(128) DEFAULT NULL,
  `rq` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86815 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `fj_worker` */

DROP TABLE IF EXISTS `fj_worker`;

CREATE TABLE `fj_worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operatorid` int(11) NOT NULL,
  `operatorname` varchar(128) NOT NULL,
  `stationcode` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `gasaddress` */

DROP TABLE IF EXISTS `gasaddress`;

CREATE TABLE `gasaddress` (
  `gbid` int(11) NOT NULL AUTO_INCREMENT,
  `StationId` int(11) NOT NULL,
  `Provincecode` int(11) NOT NULL,
  `Citycode` int(11) NOT NULL,
  `Stationcode` int(11) NOT NULL,
  `Addresscode` int(11) NOT NULL,
  `Addressname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DegrossMode` int(11) NOT NULL,
  PRIMARY KEY (`gbid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `gastankinfo` */

DROP TABLE IF EXISTS `gastankinfo`;

CREATE TABLE `gastankinfo` (
  `gastankid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `businessid` int(11) DEFAULT NULL,
  `businessname` varchar(300) DEFAULT NULL,
  `producter` varchar(100) NOT NULL,
  `productno` varchar(20) NOT NULL,
  `qrcode` varchar(20) NOT NULL,
  `producted` date NOT NULL,
  `lasttested` date NOT NULL,
  `nexttested` date NOT NULL,
  `maxuseyears` int(11) NOT NULL,
  `testcount` int(11) NOT NULL,
  `norms` varchar(20) NOT NULL,
  `registno` varchar(20) DEFAULT NULL,
  `unitselfno` varchar(20) DEFAULT NULL,
  `specs` float(6,2) DEFAULT '0.00' COMMENT '气瓶规格',
  `maxcapacity` float(6,2) DEFAULT '0.00' COMMENT '最大充装量',
  `selfweight` float(6,2) DEFAULT '0.00' COMMENT '气瓶自重(KG)',
  `thickness` float(6,2) DEFAULT '0.00' COMMENT '设计壁厚(mm)',
  `volume` float(6,2) DEFAULT '0.00' COMMENT '公称容积(L)',
  `waterpress` float(6,2) DEFAULT '0.00' COMMENT '水压试验压力',
  `netpress` float(6,2) DEFAULT '0.00' COMMENT '公称压力',
  `onlyused` int(11) NOT NULL,
  `mediumname` varchar(20) NOT NULL,
  `usedstatus` int(11) NOT NULL DEFAULT (1),
  `expired` date NOT NULL,
  `lastused` datetime NOT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  `stationcode` int(11) NOT NULL COMMENT '站点编号，方便政府和检测站使用',
  `cityid` int(11) NOT NULL COMMENT '城市Id，方便政府和检测站使用',
  `citycode` int(11) NOT NULL COMMENT '城市编号，方便政府和检测站使用',
  `provinceid` int(11) NOT NULL COMMENT '省Id，方便政府和检测站使用',
  `provincecode` int(11) NOT NULL COMMENT '省编号，方便政府和检测站使用',
  `usedlicenseid` int(11) DEFAULT NULL COMMENT '使用登记证号Id',
  `usedlicenseno` varchar(32) DEFAULT NULL COMMENT '使用登记证号',
  `licimgarcharveid` varchar(32) DEFAULT NULL COMMENT '使用登记证文书号',
  `hotway` varchar(100) DEFAULT NULL COMMENT '热处理方式',
  `bodystuff` varchar(100) DEFAULT NULL COMMENT '瓶体材料',
  `valvetype` varchar(100) DEFAULT NULL COMMENT '阀门类型',
  `valvefactory` varchar(100) DEFAULT NULL COMMENT '阀门生产厂家',
  `elabel` varchar(32) DEFAULT NULL COMMENT '电子标签编号',
  `qualifiedno` varchar(32) DEFAULT NULL COMMENT '检验合格环号',
  `imgarchiveid` varchar(100) DEFAULT NULL COMMENT '气瓶被要求拍照',
  `apistatus` int(11) NOT NULL DEFAULT '0' COMMENT '对接平台状态0表示未上传，1表示上传',
  `apitaskid` int(11) NOT NULL DEFAULT '0' COMMENT '对接任务Id,0表示未上报',
  `checkresult` varchar(20) DEFAULT NULL COMMENT '安全评定结果-YunNan',
  `checkdate` datetime DEFAULT NULL COMMENT '安全评定日期-YunNan',
  `isdel` int(11) DEFAULT NULL COMMENT '是否删除',
  `oldqrcodes` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`gastankid`),
  UNIQUE KEY `idx_gastankinfo_qrcode` (`qrcode`),
  KEY `idx_gastankinfo_stationid` (`stationid`)
) ENGINE=InnoDB AUTO_INCREMENT=248814 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `imgarchive` */

DROP TABLE IF EXISTS `imgarchive`;

CREATE TABLE `imgarchive` (
  `imgaid` int(11) NOT NULL AUTO_INCREMENT,
  `archiveid` varchar(100) NOT NULL,
  `imgname` varchar(100) NOT NULL,
  `imgpath` varchar(300) NOT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`imgaid`),
  KEY `imgarchive_archiveid_idx` (`archiveid`)
) ENGINE=InnoDB AUTO_INCREMENT=382786 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `license` */

DROP TABLE IF EXISTS `license`;

CREATE TABLE `license` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `counter` varchar(128) DEFAULT NULL,
  `doomday` varchar(128) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `logapi` */

DROP TABLE IF EXISTS `logapi`;

CREATE TABLE `logapi` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `LogStatus` int(11) NOT NULL,
  `Url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `QueryParams` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `FormParams` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `IpAddress` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `IpLocation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Browser` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `OS` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `RequestTime` datetime(6) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `ExecutionTime` int(11) NOT NULL,
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_LogApi_UserId` (`UserId`),
  CONSTRAINT `FK_LogApi_User_UserId` FOREIGN KEY (`UserId`) REFERENCES `sysuser` (`Id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `loglogin` */

DROP TABLE IF EXISTS `loglogin`;

CREATE TABLE `loglogin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `LogStatus` int(11) NOT NULL,
  `IpAddress` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `IpLocation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Browser` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `OS` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `LoginTime` datetime(6) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_LogLogin_UserId` (`UserId`),
  CONSTRAINT `FK_LogLogin_User_UserId` FOREIGN KEY (`UserId`) REFERENCES `sysuser` (`Id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `logoperate` */

DROP TABLE IF EXISTS `logoperate`;

CREATE TABLE `logoperate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `LogStatus` int(11) NOT NULL,
  `Url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `IpAddress` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `IpLocation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Browser` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `OS` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `Remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `OperateTime` datetime(6) NOT NULL,
  `UserId` int(11) DEFAULT NULL,
  `ExecutionTime` int(11) NOT NULL,
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IX_LogOperate_UserId` (`UserId`),
  CONSTRAINT `FK_LogOperate_User_UserId` FOREIGN KEY (`UserId`) REFERENCES `sysuser` (`Id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `my_table` */

DROP TABLE IF EXISTS `my_table`;

CREATE TABLE `my_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
/*!50100 PARTITION BY RANGE (year(`date`))
(PARTITION p0 VALUES LESS THAN (2010) ENGINE = InnoDB,
 PARTITION p1 VALUES LESS THAN (2011) ENGINE = InnoDB,
 PARTITION p2 VALUES LESS THAN (2012) ENGINE = InnoDB,
 PARTITION p3 VALUES LESS THAN (2013) ENGINE = InnoDB,
 PARTITION p4 VALUES LESS THAN (2014) ENGINE = InnoDB,
 PARTITION p5 VALUES LESS THAN (2015) ENGINE = InnoDB,
 PARTITION p6 VALUES LESS THAN (2016) ENGINE = InnoDB,
 PARTITION p7 VALUES LESS THAN (2017) ENGINE = InnoDB,
 PARTITION p8 VALUES LESS THAN (2018) ENGINE = InnoDB,
 PARTITION p9 VALUES LESS THAN (2019) ENGINE = InnoDB,
 PARTITION p10 VALUES LESS THAN MAXVALUE ENGINE = InnoDB) */;

/*Table structure for table `operator` */

DROP TABLE IF EXISTS `operator`;

CREATE TABLE `operator` (
  `operatorid` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `photo` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `enabled` int(11) NOT NULL,
  `Stationid` int(11) DEFAULT NULL,
  PRIMARY KEY (`operatorid`),
  KEY `IX_operator_Stationid` (`Stationid`),
  CONSTRAINT `FK_operator_station_Stationid` FOREIGN KEY (`Stationid`) REFERENCES `station` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

/*Table structure for table `printdata` */

DROP TABLE IF EXISTS `printdata`;

CREATE TABLE `printdata` (
  `pdid` int(11) NOT NULL AUTO_INCREMENT,
  `receiptId` int(11) NOT NULL,
  `typeCode` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `settingweight` int(11) NOT NULL,
  `totalCount` int(11) NOT NULL,
  `totalWeight` int(11) NOT NULL,
  `netweight` int(11) NOT NULL,
  `settleweight` int(11) NOT NULL,
  `actualWeight` int(11) DEFAULT NULL,
  PRIMARY KEY (`pdid`),
  KEY `IX_PrintData_receiptId` (`receiptId`),
  CONSTRAINT `FK_PrintData_PrintReceipt_receiptId` FOREIGN KEY (`receiptId`) REFERENCES `printreceipt` (`prid`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=817 DEFAULT CHARSET=utf8;

/*Table structure for table `printedrecord` */

DROP TABLE IF EXISTS `printedrecord`;

CREATE TABLE `printedrecord` (
  `precordId` int(11) NOT NULL AUTO_INCREMENT COMMENT '打印记录的编号',
  `printId` int(11) NOT NULL COMMENT '打印编号',
  `userid` int(11) NOT NULL,
  `userName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `created` datetime(6) NOT NULL,
  PRIMARY KEY (`precordId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Table structure for table `rolesecurity` */

DROP TABLE IF EXISTS `rolesecurity`;

CREATE TABLE `rolesecurity` (
  `RoleId` int(11) NOT NULL,
  `SecurityId` int(11) NOT NULL,
  PRIMARY KEY (`RoleId`,`SecurityId`),
  KEY `IX_RoleSecurity_SecurityId` (`SecurityId`),
  CONSTRAINT `FK_RoleSecurity_Role_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`Id`) ON DELETE CASCADE,
  CONSTRAINT `FK_RoleSecurity_Security_SecurityId` FOREIGN KEY (`SecurityId`) REFERENCES `security` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `special` */

DROP TABLE IF EXISTS `special`;

CREATE TABLE `special` (
  `stationcode` varchar(32) NOT NULL,
  `barcode` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`stationcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stationconfig` */

DROP TABLE IF EXISTS `stationconfig`;

CREATE TABLE `stationconfig` (
  `sconfigid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `maxuseyears` int(11) DEFAULT NULL,
  `testinterval` int(11) DEFAULT NULL,
  `hasfillingcheck` int(11) NOT NULL DEFAULT (1),
  `expired` datetime DEFAULT NULL,
  `maxusercount` int(11) NOT NULL,
  `hasusercount` int(11) NOT NULL,
  `allowednums` varchar(300) DEFAULT NULL COMMENT '站点允许的编号，格式如0-100,900-999',
  `maxusefullife` int(11) NOT NULL DEFAULT '8' COMMENT '气瓶最大使用年限，默认为0，表示系统默认8年',
  `eventcode` int(11) NOT NULL DEFAULT '0' COMMENT 'api对接事件code',
  `apicode1` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展1',
  `apicode2` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展2',
  `apicode3` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展3',
  `apicode4` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展4',
  `apicode5` varchar(100) DEFAULT NULL COMMENT 'api对接关键字扩展5',
  `manpostfilling` int(11) NOT NULL DEFAULT '0' COMMENT '是否手动上报充检，0表示自动，1表示手动',
  `manrecordfilling` int(11) NOT NULL DEFAULT '0' COMMENT '是否手动记录氢气充装，0表示自动，1表示手动',
  `gascode` int(11) NOT NULL DEFAULT '1' COMMENT '主要充装气体类型码：1、天然气；2、氢气',
  `manpostinfo` int(11) NOT NULL DEFAULT '0' COMMENT '是否手动上报气瓶档案，0表示自动，1表示手动',
  PRIMARY KEY (`sconfigid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stationemployee` */

DROP TABLE IF EXISTS `stationemployee`;

CREATE TABLE `stationemployee` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `emname` varchar(20) NOT NULL,
  `cardno` varchar(18) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `positions` varchar(500) DEFAULT NULL,
  `jobno` varchar(20) NOT NULL,
  `imgarchiveid` varchar(100) DEFAULT NULL,
  `licensename` varchar(100) DEFAULT NULL COMMENT '从业人员证书名称',
  `licenseno` varchar(100) DEFAULT NULL COMMENT '从业人员证书编号',
  `licbegin` datetime DEFAULT NULL COMMENT '从业人员证书有效期开始',
  `licend` datetime DEFAULT NULL COMMENT '从业人员证书有效期结束',
  `licissued` varchar(100) DEFAULT NULL COMMENT '从业人员证书发证机关',
  `remark` varchar(500) DEFAULT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stationgastype` */

DROP TABLE IF EXISTS `stationgastype`;

CREATE TABLE `stationgastype` (
  `gastypeid` int(11) NOT NULL AUTO_INCREMENT,
  `licid` int(11) NOT NULL,
  `stationid` int(11) NOT NULL,
  `gascatname` varchar(60) NOT NULL,
  `gastypename` varchar(60) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`gastypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stationlicense` */

DROP TABLE IF EXISTS `stationlicense`;

CREATE TABLE `stationlicense` (
  `licid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `licenseno` varchar(60) NOT NULL,
  `permitted` date NOT NULL,
  `expired` date NOT NULL,
  `imgarchiveid` varchar(100) DEFAULT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`licid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stationprice` */

DROP TABLE IF EXISTS `stationprice`;

CREATE TABLE `stationprice` (
  `spid` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `clienttype` int(11) NOT NULL,
  `price` double NOT NULL,
  `created` datetime(6) NOT NULL,
  PRIMARY KEY (`spid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `stationvehicle` */

DROP TABLE IF EXISTS `stationvehicle`;

CREATE TABLE `stationvehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `carnumber` varchar(20) NOT NULL COMMENT '车牌号',
  `drivername` varchar(20) DEFAULT NULL COMMENT '默认司机',
  `drivertel` varchar(20) DEFAULT NULL COMMENT '默认司机电话',
  `brand` varchar(100) DEFAULT NULL COMMENT '车辆品牌',
  `motortype` varchar(100) DEFAULT NULL COMMENT '车型',
  `enginenum` varchar(20) DEFAULT NULL COMMENT '发动机编号',
  `vehiclenum` varchar(20) DEFAULT NULL COMMENT '车架号',
  `bought` datetime DEFAULT NULL COMMENT '购车时间',
  `imgarchiveid` varchar(100) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) NOT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `stbldatarowno` */

DROP TABLE IF EXISTS `stbldatarowno`;

CREATE TABLE `stbldatarowno` (
  `tbldataid` int(11) NOT NULL,
  `provincecode` int(11) NOT NULL,
  `citycode` int(11) NOT NULL,
  `stationcode` int(11) NOT NULL,
  `recordtime` datetime(6) NOT NULL,
  `rownotype` int(11) NOT NULL,
  `rownoval` int(11) NOT NULL,
  PRIMARY KEY (`provincecode`,`citycode`,`stationcode`,`rownotype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*Table structure for table `systemdata` */

DROP TABLE IF EXISTS `systemdata`;

CREATE TABLE `systemdata` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Sequence` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `Enabled` int(11) NOT NULL DEFAULT '1',
  `CreatedTime` datetime(6) NOT NULL,
  `UpdatedTime` datetime(6) DEFAULT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `stationid` int(11) NOT NULL DEFAULT (0),
  PRIMARY KEY (`Id`),
  KEY `IX_SystemData_CategoryId` (`CategoryId`),
  CONSTRAINT `FK_SystemData_SystemDataCategory_CategoryId` FOREIGN KEY (`CategoryId`) REFERENCES `systemdatacategory` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4042 DEFAULT CHARSET=utf8;

/*Table structure for table `sysusersetting` */

DROP TABLE IF EXISTS `sysusersetting`;

CREATE TABLE `sysusersetting` (
  `userId` int(11) NOT NULL,
  `standardType` int(11) NOT NULL,
  `standardValue` double NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `FK_sysusersetting_sysuser_userId` FOREIGN KEY (`userId`) REFERENCES `sysuser` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `p1` varchar(256) DEFAULT NULL COMMENT '参数1',
  `p2` varchar(256) DEFAULT NULL COMMENT '参数2',
  `p3` varchar(256) DEFAULT NULL COMMENT '参数3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tbldata` */

DROP TABLE IF EXISTS `tbldata`;

CREATE TABLE `tbldata` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `provincecode` int(32) NOT NULL,
  `citycode` int(32) NOT NULL,
  `stationcode` int(32) NOT NULL,
  `addresscode` int(32) NOT NULL,
  `command` int(32) DEFAULT NULL,
  `nextnumber` int(32) DEFAULT NULL,
  `cardnum1` int(32) DEFAULT NULL,
  `cardnum2` int(32) DEFAULT NULL,
  `cardnum3` int(32) DEFAULT NULL,
  `cardnum4` int(32) DEFAULT NULL,
  `stamp` int(32) DEFAULT NULL,
  `endweight` int(32) DEFAULT NULL,
  `grossweight` int(32) DEFAULT NULL,
  `settingweight` int(32) DEFAULT NULL,
  `clientid` int(32) DEFAULT NULL,
  `operatorid` int(32) DEFAULT NULL,
  `gxcode` int(32) DEFAULT NULL,
  `fscode` int(32) DEFAULT NULL,
  `frameid` int(32) DEFAULT NULL,
  `curtime` timestamp NULL DEFAULT NULL,
  `usedtime` int(32) DEFAULT NULL,
  `ticket` int(32) DEFAULT NULL,
  `qrcode` varchar(32) DEFAULT NULL,
  `recordtime` timestamp NOT NULL,
  `machineno` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tbldata_qrcode` (`qrcode`),
  KEY `idx_tbldata_query` (`provincecode`,`citycode`,`stationcode`,`recordtime`,`gxcode`,`grossweight`,`endweight`,`settingweight`)
  KEY `idx_tbldata_unique_check` (`provincecode`, `citycode`, `stationcode`, `machineno`, `curtime`)
) ENGINE=InnoDB AUTO_INCREMENT=9675748 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
alter table
/*Table structure for table `tbldataprint` */

DROP TABLE IF EXISTS `tbldataprint`;

CREATE TABLE `tbldataprint` (
  `tblid` int(11) NOT NULL,
  `receiptId` int(11) NOT NULL,
  `printed` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`tblid`,`receiptId`),
  KEY `tbldataprint_tblid` (`tblid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tbldevicelog` */

DROP TABLE IF EXISTS `tbldevicelog`;

CREATE TABLE `tbldevicelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `addresscode` int(11) DEFAULT NULL COMMENT '地址码',
  `provincecode` int(11) DEFAULT NULL COMMENT '省份编码',
  `citycode` int(11) DEFAULT NULL COMMENT '城市编码',
  `stationcode` int(11) DEFAULT NULL COMMENT '气站编码',
  `qrcode` varchar(32) DEFAULT NULL COMMENT '二维码字符串',
  `recordtime` timestamp NULL DEFAULT NULL COMMENT '记录时间',
  `state` int(11) DEFAULT NULL COMMENT '1-超期码,2-非本站瓶码,85-停止灌装,170-正常起动',
  `machineno` int(11) DEFAULT NULL COMMENT '机器码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9464001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `tbltoken` */

DROP TABLE IF EXISTS `tbltoken`;

CREATE TABLE `tbltoken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `devicecode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stationcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `token` varchar(128) NOT NULL,
  `recordtime` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4742 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `testorginfo` */

DROP TABLE IF EXISTS `testorginfo`;

CREATE TABLE `testorginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationid` int(11) NOT NULL,
  `testorgname` varchar(20) NOT NULL COMMENT '检测站名称',
  `testorgcode` varchar(18) DEFAULT NULL COMMENT '检测站编码',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '检测站地址',
  `remark` varchar(500) DEFAULT NULL COMMENT '检测站备注',
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `toqprecvback` */

DROP TABLE IF EXISTS `toqprecvback`;

CREATE TABLE `toqprecvback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '气瓶接收退还Id',
  `testorgid` int(11) NOT NULL COMMENT '检测机构Id',
  `testorgname` varchar(300) DEFAULT NULL COMMENT '检测站名称',
  `stationid` int(11) NOT NULL COMMENT '气站Id',
  `stationname` varchar(300) DEFAULT NULL COMMENT '气站名称',
  `qpcount` int(11) NOT NULL COMMENT '接收送还的数量',
  `postname` varchar(20) DEFAULT NULL COMMENT '送检人',
  `posttel` varchar(11) DEFAULT NULL COMMENT '送检人联系方式',
  `srbstatus` int(11) NOT NULL COMMENT '送检状态，1表示送检，2表示接收，3表示取回',
  `received` datetime DEFAULT NULL COMMENT '接收时间',
  `receivername` varchar(20) DEFAULT NULL COMMENT '气瓶接收人',
  `testbatchno` varchar(32) DEFAULT NULL COMMENT '检测批次号',
  `testedcount` int(11) NOT NULL DEFAULT '0' COMMENT '已检测瓶数',
  `checkbatchno` varchar(32) DEFAULT NULL COMMENT '审核批次号',
  `checkedcount` int(11) NOT NULL DEFAULT '0' COMMENT '已审核瓶数',
  `returned` datetime DEFAULT NULL COMMENT '取回时间',
  `fetchername` varchar(20) DEFAULT NULL COMMENT '取回气瓶人',
  `fetchertel` varchar(11) DEFAULT NULL COMMENT '取回气瓶人联系方式',
  `returnname` varchar(20) DEFAULT NULL COMMENT '归还气瓶人',
  `created` datetime NOT NULL COMMENT '配送时间',
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL COMMENT '最新修改时间',
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `totestqpdetail` */

DROP TABLE IF EXISTS `totestqpdetail`;

CREATE TABLE `totestqpdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '气瓶检测详情ID',
  `testflowid` int(11) NOT NULL COMMENT '综合检测ID',
  `testorgid` int(11) NOT NULL COMMENT '检测机构Id',
  `stationid` int(11) NOT NULL COMMENT '气站ID',
  `gastankid` int(11) NOT NULL COMMENT '气瓶Id',
  `qrcode` varchar(20) NOT NULL COMMENT '气瓶条码',
  `tested` datetime DEFAULT NULL COMMENT '检测时间',
  `testbatchno` varchar(32) DEFAULT NULL COMMENT '检测批次号',
  `testername` varchar(20) DEFAULT NULL COMMENT '检测员',
  `gastanktype` varchar(20) DEFAULT NULL COMMENT '设备类别',
  `testtype` varchar(20) DEFAULT NULL COMMENT '检验类别',
  `nexttested` date DEFAULT NULL COMMENT '下检时间',
  `safeyear` date DEFAULT NULL COMMENT '安全评估年限',
  `testresult` int(11) NOT NULL COMMENT '0表示未检测，1表示合格，2表示不合格，3表示废弃',
  `testremark` varchar(300) DEFAULT NULL COMMENT '检测说明',
  `checkbatchno` varchar(32) DEFAULT NULL COMMENT '审核批次号',
  `qpchecked` datetime DEFAULT NULL COMMENT '审核时间',
  `checkstatus` int(11) NOT NULL COMMENT '审核状态，0表示未审核，1表示审核通过，2表示审核不通过',
  `checkremark` varchar(300) DEFAULT NULL COMMENT '审核意见',
  `checkername` varchar(20) DEFAULT NULL COMMENT '审核人员',
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `totestqpflow` */

DROP TABLE IF EXISTS `totestqpflow`;

CREATE TABLE `totestqpflow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '气瓶检测Id',
  `testorgid` int(11) NOT NULL COMMENT '检测机构Id',
  `stationid` int(11) NOT NULL COMMENT '气站Id',
  `stationname` varchar(300) DEFAULT NULL COMMENT '气站名称',
  `flowstatus` int(11) NOT NULL COMMENT '气瓶检测状态：0表示送检，1表示接收，2表示检测完成，3表示审核重新检测，4表示审核作废，5表示审核通过，10表示出报告，11表示打印报告，12表示交接完成，20表示气瓶处理',
  `gastankid` int(11) NOT NULL COMMENT '气瓶Id',
  `qrcode` varchar(20) NOT NULL COMMENT '气瓶条码',
  `productno` varchar(20) NOT NULL COMMENT '气瓶钢印号',
  `producted` date NOT NULL COMMENT '生产时间',
  `lasttested` date NOT NULL COMMENT '上检时间',
  `thistested` date NOT NULL COMMENT '本次应检时间',
  `testcount` int(11) NOT NULL COMMENT '检测次数',
  `norms` varchar(20) NOT NULL COMMENT '气瓶型号',
  `specs` float(6,2) DEFAULT '0.00' COMMENT '气瓶规格',
  `registno` varchar(20) DEFAULT NULL COMMENT '使用登记证编号',
  `unitselfno` varchar(20) DEFAULT NULL COMMENT '充装单位自编号',
  `recvbackid` int(11) NOT NULL COMMENT '气瓶接收退还Id',
  `received` datetime DEFAULT NULL COMMENT '接收时间',
  `qptested` datetime DEFAULT NULL COMMENT '检测时间',
  `testbatchno` varchar(32) DEFAULT NULL COMMENT '检测批次号',
  `testername` varchar(20) DEFAULT NULL COMMENT '检测员',
  `gastanktype` varchar(20) DEFAULT NULL COMMENT '设备类别',
  `testtype` varchar(20) DEFAULT NULL COMMENT '检验类别',
  `nexttested` date DEFAULT NULL COMMENT '下检时间',
  `safeyear` date DEFAULT NULL COMMENT '安全评估年限',
  `testresult` int(11) NOT NULL COMMENT '0表示未检测，1表示合格，2表示不合格，3表示废弃',
  `testremark` varchar(300) DEFAULT NULL COMMENT '检测',
  `checkbatchno` varchar(32) DEFAULT NULL COMMENT '审核批次号',
  `qpchecked` datetime DEFAULT NULL COMMENT '审核时间',
  `checkresult` int(11) NOT NULL COMMENT '审核状态，0表示未审核，1表示审核通过，2表示审核不通过',
  `checkremark` varchar(300) DEFAULT NULL COMMENT '审核意见',
  `checkername` varchar(20) DEFAULT NULL COMMENT '审核人员',
  `concluded` datetime DEFAULT NULL COMMENT '出报告时间',
  `printed` datetime DEFAULT NULL COMMENT '最近打印时间',
  `printcount` int(11) NOT NULL COMMENT '打印次数',
  `returned` datetime DEFAULT NULL COMMENT '返还时间',
  `created` datetime NOT NULL,
  `createrid` int(11) NOT NULL,
  `creatername` varchar(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `modifierid` int(11) DEFAULT NULL,
  `modifiername` varchar(20) DEFAULT NULL,
  `apistatus` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idxtotestqpflowtocheck` (`testorgid`,`flowstatus`,`testbatchno`,`stationid`)
) ENGINE=InnoDB AUTO_INCREMENT=4546 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户账户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '口令',
  `realname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户真实名称',
  `sid` int(32) DEFAULT NULL COMMENT 'city(province\\station)id',
  `sidflag` int(32) DEFAULT NULL COMMENT '0-station,1-city,2-province',
  `qrcode` varchar(32) DEFAULT NULL COMMENT '二维码字符串',
  `starttime` timestamp NULL DEFAULT NULL COMMENT '有效开始时间',
  `endtime` timestamp NULL DEFAULT NULL COMMENT '有效结束时间',
  `state` int(32) DEFAULT NULL COMMENT '状态：1-有效，0-无效',
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `userrole` */

DROP TABLE IF EXISTS `userrole`;

CREATE TABLE `userrole` (
  `UserId` int(11) NOT NULL,
  `RoleId` int(11) NOT NULL,
  PRIMARY KEY (`UserId`,`RoleId`),
  KEY `IX_UserRole_RoleId` (`RoleId`),
  CONSTRAINT `FK_UserRole_Role_RoleId` FOREIGN KEY (`RoleId`) REFERENCES `role` (`Id`) ON DELETE CASCADE,
  CONSTRAINT `FK_UserRole_User_UserId` FOREIGN KEY (`UserId`) REFERENCES `sysuser` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `userstation` */

DROP TABLE IF EXISTS `userstation`;

CREATE TABLE `userstation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `stationid` int(11) NOT NULL,
  `cityid` int(11) NOT NULL,
  `provinceid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IX_userstation_stationid` (`stationid`)
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=utf8;

/*Table structure for table `xk_assocstation` */

DROP TABLE IF EXISTS `xk_assocstation`;

CREATE TABLE `xk_assocstation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stationCode` varchar(128) DEFAULT NULL COMMENT '充装单位编码',
  `barcodePrefix` varchar(128) DEFAULT NULL COMMENT '气瓶条码前缀',
  `associateCode` varchar(128) DEFAULT NULL COMMENT '联营充装单位编码',
  `associateName` varchar(128) DEFAULT NULL COMMENT '联营充装单位名称',
  `did` int(11) NOT NULL COMMENT '单位id',
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `xk_assocstation_ibfk_1` FOREIGN KEY (`did`) REFERENCES `xk_dw` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2707 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_cylinderrec` */

DROP TABLE IF EXISTS `xk_cylinderrec`;

CREATE TABLE `xk_cylinderrec` (
  `id` int(11) NOT NULL,
  `stationCode` varchar(128) DEFAULT NULL COMMENT '充装单位编码',
  `code` varchar(128) DEFAULT NULL COMMENT '气瓶档案在当前充装单位的唯一编号',
  `factoryCode` varchar(128) DEFAULT NULL COMMENT '制造厂商编码',
  `factoryName` varchar(128) DEFAULT NULL COMMENT '制造厂商名称',
  `factoryNumber` varchar(128) DEFAULT NULL COMMENT '气瓶出厂编号',
  `cylinderTypeCode` varchar(128) DEFAULT NULL COMMENT '气瓶种类编码',
  `cylinderTypeName` varchar(128) DEFAULT NULL COMMENT '气瓶种类名称',
  `mediumCode` varchar(128) DEFAULT NULL COMMENT '充装介质编码',
  `mediumName` varchar(128) DEFAULT NULL COMMENT '充装介质名称',
  `mediumInspectPeriod` int(11) DEFAULT NULL COMMENT '充装介质检验周期（年）',
  `mediumColor` varchar(128) DEFAULT NULL,
  `mediumScrappedPeriod` int(11) DEFAULT NULL COMMENT '充装介质报废周期（年）',
  `factoryDate` varchar(128) DEFAULT NULL COMMENT '气瓶出厂年月（格式：yyyy-mm）',
  `lastInspectDate` varchar(128) DEFAULT NULL COMMENT '末次检验年月（格式：yyyy-mm）',
  `nextInspectDate` varchar(128) DEFAULT NULL COMMENT '下次检验年月（格式：yyyy-mm）',
  `scrappedDate` varchar(128) DEFAULT NULL COMMENT '气瓶报废年月（格式：yyyy-mm）',
  `registerCode` varchar(128) DEFAULT NULL COMMENT '注册编号',
  `nominalPressure` float DEFAULT NULL COMMENT '公称压力（保留 1 位小数）',
  `cylinderVolume` float DEFAULT NULL COMMENT '气瓶容积（保留 1 位小数）',
  `designThickness` float DEFAULT NULL COMMENT '设计壁厚（保留 1 位小数）',
  `status` varchar(128) DEFAULT NULL COMMENT '气瓶状态（正常、注销、删除、报废、停用、转出、退',
  `barcode` varchar(128) DEFAULT NULL COMMENT '气瓶条码',
  `memo` varchar(128) DEFAULT NULL COMMENT '备注',
  `proofMaterial` varchar(128) DEFAULT NULL COMMENT '证明材料（默认 0000）',
  `ownNumber` varchar(128) DEFAULT NULL COMMENT '自有编号',
  `actualMediumName` varchar(128) DEFAULT NULL COMMENT '实际充装介质名称',
  `enterDate` varchar(128) DEFAULT NULL COMMENT '入站日期（格式：yyyy-mm-dd）',
  `recordDate` varchar(128) DEFAULT NULL COMMENT '登记日期（格式：yyyy-mm-dd）',
  `specification` varchar(128) DEFAULT NULL COMMENT '气瓶型号规格',
  `recordStatus` int(11) DEFAULT NULL COMMENT '记录状态位（0：无变化；1：新增；2：修改）',
  `lastInspectNumber` varchar(128) DEFAULT NULL COMMENT '最新检验报告编号',
  `lastRegisterNumber` varchar(128) DEFAULT NULL COMMENT '最新使用登记证编号',
  `data01` varchar(128) DEFAULT NULL COMMENT '原始重量（溶解乙炔：皮重；车用气瓶：安装许可证号）',
  `data02` varchar(128) DEFAULT NULL COMMENT '水试验压力（溶解乙炔：试验压力；车用气瓶：车牌号',
  `data03` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：限定充装压力；车用气瓶：车辆行驶证号）',
  `data04` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：填充空隙率；车用气瓶：车辆种类）',
  `data05` varchar(128) DEFAULT NULL COMMENT '(溶解乙炔:丙酮充装量；车用气瓶：隶属单位/个人）',
  `data06` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：水容积；车用气瓶：组织机构代码/车主身',
  `data07` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：限定充装量；车用气瓶：联系方式）',
  `data08` varchar(128) DEFAULT NULL COMMENT '（车用气瓶：支架号）',
  `data09` varchar(128) DEFAULT NULL COMMENT '（车用气瓶：证书资质）',
  `data10` varchar(128) DEFAULT NULL COMMENT '（车用气瓶：发动机号）',
  `isNewCylinder` int(11) DEFAULT NULL COMMENT '是否新购瓶（0：否；1：是）',
  `auditAgency` varchar(128) DEFAULT NULL COMMENT '气瓶档案审核单位名称',
  `auditPerson` varchar(128) DEFAULT NULL COMMENT '气瓶档案审核人',
  `auditDate` varchar(128) DEFAULT NULL COMMENT '气瓶档案审核日期（格式：yyyy-mm-dd）',
  `delayIssuing` int(11) DEFAULT NULL COMMENT '是否延缓发证（0：不延缓；1、延缓）',
  `recordFlag` varchar(128) DEFAULT NULL COMMENT '气瓶档案状态标记（默认 0000000000）',
  `senderCode` varchar(128) DEFAULT NULL COMMENT '气瓶档案建档单位编码',
  `virtualUser` varchar(128) DEFAULT NULL COMMENT '气瓶档案建档虚拟用户代码',
  `backReason` varchar(128) DEFAULT NULL COMMENT '气瓶档案退回原因',
  `recordTime` varchar(32) DEFAULT NULL COMMENT '气瓶档案录入时间（格式：yyyy-mm-dd hh:nn:ss）',
  `lastUpdateTime` varchar(32) DEFAULT NULL COMMENT '气瓶档案修改时间（格式：yyyy-mm-dd hh:nn:ss）',
  `internalState` int(11) DEFAULT NULL COMMENT '记录状态（0：无效；1：有效）',
  `internalTime` timestamp(3) NULL DEFAULT NULL COMMENT '记录时间戳（格式：yyyy-mm-dd hh:nn:ss.zzz）',
  `did` int(11) NOT NULL COMMENT '单位id',
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `xk_cylinderrec_ibfk_1` FOREIGN KEY (`did`) REFERENCES `xk_dw` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_cylinderrecbak` */

DROP TABLE IF EXISTS `xk_cylinderrecbak`;

CREATE TABLE `xk_cylinderrecbak` (
  `id` int(11) NOT NULL,
  `stationCode` varchar(128) DEFAULT NULL COMMENT '充装单位编码',
  `code` varchar(128) DEFAULT NULL COMMENT '气瓶档案在当前充装单位的唯一编号',
  `factoryCode` varchar(128) DEFAULT NULL COMMENT '制造厂商编码',
  `factoryName` varchar(128) DEFAULT NULL COMMENT '制造厂商名称',
  `factoryNumber` varchar(128) DEFAULT NULL COMMENT '气瓶出厂编号',
  `cylinderTypeCode` varchar(128) DEFAULT NULL COMMENT '气瓶种类编码',
  `cylinderTypeName` varchar(128) DEFAULT NULL COMMENT '气瓶种类名称',
  `mediumCode` varchar(128) DEFAULT NULL COMMENT '充装介质编码',
  `mediumName` varchar(128) DEFAULT NULL COMMENT '充装介质名称',
  `mediumInspectPeriod` int(11) DEFAULT NULL COMMENT '充装介质检验周期（年）',
  `mediumColor` varchar(128) DEFAULT NULL,
  `mediumScrappedPeriod` int(11) DEFAULT NULL COMMENT '充装介质报废周期（年）',
  `factoryDate` varchar(128) DEFAULT NULL COMMENT '气瓶出厂年月（格式：yyyy-mm）',
  `lastInspectDate` varchar(128) DEFAULT NULL COMMENT '末次检验年月（格式：yyyy-mm）',
  `nextInspectDate` varchar(128) DEFAULT NULL COMMENT '下次检验年月（格式：yyyy-mm）',
  `scrappedDate` varchar(128) DEFAULT NULL COMMENT '气瓶报废年月（格式：yyyy-mm）',
  `registerCode` varchar(128) DEFAULT NULL COMMENT '注册编号',
  `nominalPressure` float DEFAULT NULL COMMENT '公称压力（保留 1 位小数）',
  `cylinderVolume` float DEFAULT NULL COMMENT '气瓶容积（保留 1 位小数）',
  `designThickness` float DEFAULT NULL COMMENT '设计壁厚（保留 1 位小数）',
  `status` varchar(128) DEFAULT NULL COMMENT '气瓶状态（正常、注销、删除、报废、停用、转出、退',
  `barcode` varchar(128) DEFAULT NULL COMMENT '气瓶条码',
  `memo` varchar(128) DEFAULT NULL COMMENT '备注',
  `proofMaterial` varchar(128) DEFAULT NULL COMMENT '证明材料（默认 0000）',
  `ownNumber` varchar(128) DEFAULT NULL COMMENT '自有编号',
  `actualMediumName` varchar(128) DEFAULT NULL COMMENT '实际充装介质名称',
  `enterDate` varchar(128) DEFAULT NULL COMMENT '入站日期（格式：yyyy-mm-dd）',
  `recordDate` varchar(128) DEFAULT NULL COMMENT '登记日期（格式：yyyy-mm-dd）',
  `specification` varchar(128) DEFAULT NULL COMMENT '气瓶型号规格',
  `recordStatus` int(11) DEFAULT NULL COMMENT '记录状态位（0：无变化；1：新增；2：修改）',
  `lastInspectNumber` varchar(128) DEFAULT NULL COMMENT '最新检验报告编号',
  `lastRegisterNumber` varchar(128) DEFAULT NULL COMMENT '最新使用登记证编号',
  `data01` varchar(128) DEFAULT NULL COMMENT '原始重量（溶解乙炔：皮重；车用气瓶：安装许可证号）',
  `data02` varchar(128) DEFAULT NULL COMMENT '水试验压力（溶解乙炔：试验压力；车用气瓶：车牌号',
  `data03` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：限定充装压力；车用气瓶：车辆行驶证号）',
  `data04` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：填充空隙率；车用气瓶：车辆种类）',
  `data05` varchar(128) DEFAULT NULL COMMENT '(溶解乙炔:丙酮充装量；车用气瓶：隶属单位/个人）',
  `data06` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：水容积；车用气瓶：组织机构代码/车主身',
  `data07` varchar(128) DEFAULT NULL COMMENT '（溶解乙炔：限定充装量；车用气瓶：联系方式）',
  `data08` varchar(128) DEFAULT NULL COMMENT '（车用气瓶：支架号）',
  `data09` varchar(128) DEFAULT NULL COMMENT '（车用气瓶：证书资质）',
  `data10` varchar(128) DEFAULT NULL COMMENT '（车用气瓶：发动机号）',
  `isNewCylinder` int(11) DEFAULT NULL COMMENT '是否新购瓶（0：否；1：是）',
  `auditAgency` varchar(128) DEFAULT NULL COMMENT '气瓶档案审核单位名称',
  `auditPerson` varchar(128) DEFAULT NULL COMMENT '气瓶档案审核人',
  `auditDate` varchar(128) DEFAULT NULL COMMENT '气瓶档案审核日期（格式：yyyy-mm-dd）',
  `delayIssuing` int(11) DEFAULT NULL COMMENT '是否延缓发证（0：不延缓；1、延缓）',
  `recordFlag` varchar(128) DEFAULT NULL COMMENT '气瓶档案状态标记（默认 0000000000）',
  `senderCode` varchar(128) DEFAULT NULL COMMENT '气瓶档案建档单位编码',
  `virtualUser` varchar(128) DEFAULT NULL COMMENT '气瓶档案建档虚拟用户代码',
  `backReason` varchar(128) DEFAULT NULL COMMENT '气瓶档案退回原因',
  `recordTime` varchar(32) DEFAULT NULL COMMENT '气瓶档案录入时间（格式：yyyy-mm-dd hh:nn:ss）',
  `lastUpdateTime` varchar(32) DEFAULT NULL COMMENT '气瓶档案修改时间（格式：yyyy-mm-dd hh:nn:ss）',
  `internalState` int(11) DEFAULT NULL COMMENT '记录状态（0：无效；1：有效）',
  `internalTime` timestamp(3) NULL DEFAULT NULL COMMENT '记录时间戳（格式：yyyy-mm-dd hh:nn:ss.zzz）',
  `did` int(11) NOT NULL COMMENT '单位id',
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `xk_cylinderrecbak_ibfk_1` FOREIGN KEY (`did`) REFERENCES `xk_dw` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_cylindertype` */

DROP TABLE IF EXISTS `xk_cylindertype`;

CREATE TABLE `xk_cylindertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) DEFAULT NULL COMMENT '气瓶类型编码',
  `name` varchar(128) DEFAULT NULL COMMENT '气瓶类型名称',
  `internalState` int(11) DEFAULT NULL COMMENT '记录状态（0：无效；1：有效）',
  `internalTime` timestamp(3) NULL DEFAULT NULL COMMENT '记录变动时间（格式：yyyy-mm-dd hh:nn:ss.zzz）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_log` */

DROP TABLE IF EXISTS `xk_log`;

CREATE TABLE `xk_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(128) DEFAULT NULL,
  `detail` varchar(128) DEFAULT NULL,
  `rq` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158762 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_manufacturer` */

DROP TABLE IF EXISTS `xk_manufacturer`;

CREATE TABLE `xk_manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) DEFAULT NULL COMMENT '制造厂商编码',
  `name` varchar(128) DEFAULT NULL COMMENT '制造厂商名称',
  `exName` varchar(128) DEFAULT NULL COMMENT '制造厂商曾用名称',
  `fastKey` varchar(128) DEFAULT NULL COMMENT '制造厂商名称缩写编码',
  `internalState` int(11) DEFAULT NULL COMMENT '记录状态（0：无效；1：有效）',
  `internalTime` timestamp(3) NULL DEFAULT NULL COMMENT '记录变动时间（格式：yyyy-mm-dd hh:nn:ss.zzz）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1423 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_medium` */

DROP TABLE IF EXISTS `xk_medium`;

CREATE TABLE `xk_medium` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) DEFAULT NULL COMMENT '充装介质编码',
  `name` varchar(128) DEFAULT NULL COMMENT '充装介质名称',
  `color` varchar(128) DEFAULT NULL COMMENT '充装介质颜色',
  `inspectPeriod` int(11) DEFAULT NULL COMMENT '充装介质-气瓶检验周期（年）',
  `scrappedPeriod` int(11) DEFAULT NULL COMMENT '充装介质-气瓶报废周期（年）',
  `internalState` int(11) DEFAULT NULL COMMENT '记录状态（0：无效；1：有效）',
  `internalTime` timestamp(3) NULL DEFAULT NULL COMMENT '记录变动时间（格式：yyyy-mm-dd hh:nn:ss.zzz）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=533 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_params` */

DROP TABLE IF EXISTS `xk_params`;

CREATE TABLE `xk_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `value` varchar(128) DEFAULT NULL,
  `bz` varchar(128) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `xk_stationmap` */

DROP TABLE IF EXISTS `xk_stationmap`;

CREATE TABLE `xk_stationmap` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `stationCodejbl` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '佳贝尔站点号',
  `did` int(11) NOT NULL COMMENT '单位id',
  `bz` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  CONSTRAINT `xk_stationmap_ibfk_1` FOREIGN KEY (`did`) REFERENCES `xk_dw` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `yseqtbldata_120101` */

DROP TABLE IF EXISTS `yseqtbldata_120101`;

CREATE TABLE `yseqtbldata_120101` (
  `id` int(32) NOT NULL DEFAULT '0',
  `recordtime` timestamp NOT NULL,
  `yseqid` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
