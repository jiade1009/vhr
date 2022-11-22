# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.6.5-MariaDB-1:10.6.5+maria~focal)
# Database: vhr
# Generation Time: 2022-11-11 14:45:54 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table adjustsalary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `adjustsalary`;

CREATE TABLE `adjustsalary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL,
  `asDate` date DEFAULT NULL COMMENT '调薪日期',
  `beforeSalary` int(11) DEFAULT NULL COMMENT '调前薪资',
  `afterSalary` int(11) DEFAULT NULL COMMENT '调后薪资',
  `reason` varchar(255) DEFAULT NULL COMMENT '调薪原因',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `pid` (`eid`),
  CONSTRAINT `adjustsalary_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table appraise
# ------------------------------------------------------------

DROP TABLE IF EXISTS `appraise`;

CREATE TABLE `appraise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL,
  `appDate` date DEFAULT NULL COMMENT '考评日期',
  `appResult` varchar(32) DEFAULT NULL COMMENT '考评结果',
  `appContent` varchar(255) DEFAULT NULL COMMENT '考评内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `pid` (`eid`),
  CONSTRAINT `appraise_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table database_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `database_type`;

CREATE TABLE `database_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `sort_order` int(11) DEFAULT 0 COMMENT '排序',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `sys` int(11) DEFAULT NULL COMMENT '是否是系统参数，0不是，1是，系统参数不允许删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='basic data information';

LOCK TABLES `database_type` WRITE;
/*!40000 ALTER TABLE `database_type` DISABLE KEYS */;

INSERT INTO `database_type` (`id`, `code`, `name`, `value`, `description`, `sort_order`, `type`, `sys`)
VALUES
	(1,'stk_weekly_start_date','周线读取起始日期','20200101','周线读取起始日期，日期格式：yyyyMMdd',0,NULL,1),
	(2,'switch_controller','条件开关','true','选股控制开关',0,'controller',0),
	(3,'stk_auto_order','自动下单开关','0','自动下单开关，0关闭，1开启',0,'auto',1),
	(4,'stk_ema_18','周线18ema','18','',0,'stk_ema',1),
	(5,'stk_ema_25','周线25ema','25','',1,'stk_ema',1),
	(6,'stk_ema_75','周线75ema','75','',2,'stk_ema',1);

/*!40000 ALTER TABLE `database_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '部门名称',
  `parentId` int(11) DEFAULT NULL,
  `depPath` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT 1,
  `isParent` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;

INSERT INTO `department` (`id`, `name`, `parentId`, `depPath`, `enabled`, `isParent`)
VALUES
	(1,'股东会',-1,'.1',1,1),
	(4,'董事会',1,'.1.4',1,1),
	(5,'总办',4,'.1.4.5',1,1),
	(8,'财务部',5,'.1.4.5.8',1,0),
	(78,'市场部',5,'.1.4.5.78',1,0),
	(91,'技术部',5,'.1.4.5.91',1,0),
	(92,'运维部',5,'.1.4.5.92',1,0);

/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table employee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `name` varchar(10) DEFAULT NULL COMMENT '员工姓名',
  `gender` char(4) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `idCard` char(18) DEFAULT NULL COMMENT '身份证号',
  `wedlock` enum('已婚','未婚','离异') DEFAULT NULL COMMENT '婚姻状况',
  `nationId` int(8) DEFAULT NULL COMMENT '民族',
  `nativePlace` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `politicId` int(8) DEFAULT NULL COMMENT '政治面貌',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `departmentId` int(11) DEFAULT NULL COMMENT '所属部门',
  `jobLevelId` int(11) DEFAULT NULL COMMENT '职称ID',
  `posId` int(11) DEFAULT NULL COMMENT '职位ID',
  `engageForm` varchar(8) DEFAULT NULL COMMENT '聘用形式',
  `tiptopDegree` enum('博士','硕士','本科','大专','高中','初中','小学','其他') DEFAULT NULL COMMENT '最高学历',
  `specialty` varchar(32) DEFAULT NULL COMMENT '所属专业',
  `school` varchar(32) DEFAULT NULL COMMENT '毕业院校',
  `beginDate` date DEFAULT NULL COMMENT '入职日期',
  `workState` enum('在职','离职') DEFAULT '在职' COMMENT '在职状态',
  `workID` char(8) DEFAULT NULL COMMENT '工号',
  `contractTerm` double DEFAULT NULL COMMENT '合同期限',
  `conversionTime` date DEFAULT NULL COMMENT '转正日期',
  `notWorkDate` date DEFAULT NULL COMMENT '离职日期',
  `beginContract` date DEFAULT NULL COMMENT '合同起始日期',
  `endContract` date DEFAULT NULL COMMENT '合同终止日期',
  `workAge` int(11) DEFAULT NULL COMMENT '工龄',
  PRIMARY KEY (`id`),
  KEY `departmentId` (`departmentId`),
  KEY `jobId` (`jobLevelId`),
  KEY `dutyId` (`posId`),
  KEY `nationId` (`nationId`),
  KEY `politicId` (`politicId`),
  KEY `workID_key` (`workID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`jobLevelId`) REFERENCES `joblevel` (`id`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`posId`) REFERENCES `position` (`id`),
  CONSTRAINT `employee_ibfk_4` FOREIGN KEY (`nationId`) REFERENCES `nation` (`id`),
  CONSTRAINT `employee_ibfk_5` FOREIGN KEY (`politicId`) REFERENCES `politicsstatus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;

INSERT INTO `employee` (`id`, `name`, `gender`, `birthday`, `idCard`, `wedlock`, `nationId`, `nativePlace`, `politicId`, `email`, `phone`, `address`, `departmentId`, `jobLevelId`, `posId`, `engageForm`, `tiptopDegree`, `specialty`, `school`, `beginDate`, `workState`, `workID`, `contractTerm`, `conversionTime`, `notWorkDate`, `beginContract`, `endContract`, `workAge`)
VALUES
	(1,'江','男','1990-01-01','610122199001011256','已婚',1,'陕西',13,'laowang@qq.com','18565558897','深圳市南山区',5,9,29,'劳务合同','本科','信息管理与信息系统','深圳大学','2018-01-01','在职','00000001',2,'2018-04-01',NULL,'2018-01-01','2020-01-01',NULL),
	(1942,'黄','男','2022-10-05','350581191101011416','已婚',1,'上海',13,'17979563@qq.com','13651642400','shanghai ',8,9,29,'劳动合同','硕士','software','daxue','2022-10-26','在职','00000066',0,'2022-10-26',NULL,'2022-10-25','2022-10-31',NULL);

/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table employeeec
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employeeec`;

CREATE TABLE `employeeec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL COMMENT '员工编号',
  `ecDate` date DEFAULT NULL COMMENT '奖罚日期',
  `ecReason` varchar(255) DEFAULT NULL COMMENT '奖罚原因',
  `ecPoint` int(11) DEFAULT NULL COMMENT '奖罚分',
  `ecType` int(11) DEFAULT NULL COMMENT '奖罚类别，0：奖，1：罚',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `pid` (`eid`),
  CONSTRAINT `employeeec_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table employeeremove
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employeeremove`;

CREATE TABLE `employeeremove` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL,
  `afterDepId` int(11) DEFAULT NULL COMMENT '调动后部门',
  `afterJobId` int(11) DEFAULT NULL COMMENT '调动后职位',
  `removeDate` date DEFAULT NULL COMMENT '调动日期',
  `reason` varchar(255) DEFAULT NULL COMMENT '调动原因',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`eid`),
  CONSTRAINT `employeeremove_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table employeetrain
# ------------------------------------------------------------

DROP TABLE IF EXISTS `employeetrain`;

CREATE TABLE `employeetrain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL COMMENT '员工编号',
  `trainDate` date DEFAULT NULL COMMENT '培训日期',
  `trainContent` varchar(255) DEFAULT NULL COMMENT '培训内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `pid` (`eid`),
  CONSTRAINT `employeetrain_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table empsalary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `empsalary`;

CREATE TABLE `empsalary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `eid` (`eid`),
  KEY `empsalary_ibfk_2` (`sid`),
  CONSTRAINT `empsalary_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employee` (`id`),
  CONSTRAINT `empsalary_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `salary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;





# Dump of table hr
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hr`;

CREATE TABLE `hr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'hrID',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` char(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) DEFAULT NULL COMMENT '联系地址',
  `enabled` tinyint(1) DEFAULT 1,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `userface` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `hr` WRITE;
/*!40000 ALTER TABLE `hr` DISABLE KEYS */;

INSERT INTO `hr` (`id`, `name`, `phone`, `telephone`, `address`, `enabled`, `username`, `password`, `userface`, `remark`)
VALUES
	(3,'系统管理员','18568887789','029-82881234','深圳南山',1,'admin','$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm','http://127.0.0.1:8889/group1/M00/00/00/rBIAA2M6_1-AM14YAABHZ6iigFs160.jpg',NULL),
	(5,'李白','18568123489','029-82123434','海口美兰',1,'libai','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','','备注信息'),
	(10,'韩愈','18568123666','029-82111555','广州番禺',0,'hanyu','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','',NULL),
	(11,'柳宗元','18568123377','029-82111333','广州天河',1,'liuzongyuan','$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.','',NULL);

/*!40000 ALTER TABLE `hr` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hr_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hr_role`;

CREATE TABLE `hr_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hrid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`rid`),
  KEY `hr_role_ibfk_1` (`hrid`),
  CONSTRAINT `hr_role_ibfk_1` FOREIGN KEY (`hrid`) REFERENCES `hr` (`id`) ON DELETE CASCADE,
  CONSTRAINT `hr_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `hr_role` WRITE;
/*!40000 ALTER TABLE `hr_role` DISABLE KEYS */;

INSERT INTO `hr_role` (`id`, `hrid`, `rid`)
VALUES
	(1,3,6),
	(84,11,3),
	(85,11,2),
	(86,11,4),
	(87,10,3),
	(88,5,1),
	(89,5,2);

/*!40000 ALTER TABLE `hr_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table joblevel
# ------------------------------------------------------------

DROP TABLE IF EXISTS `joblevel`;

CREATE TABLE `joblevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '职称名称',
  `titleLevel` enum('正高级','副高级','中级','初级','员级') DEFAULT NULL,
  `createDate` timestamp NULL DEFAULT current_timestamp(),
  `enabled` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `joblevel` WRITE;
/*!40000 ALTER TABLE `joblevel` DISABLE KEYS */;

INSERT INTO `joblevel` (`id`, `name`, `titleLevel`, `createDate`, `enabled`)
VALUES
	(9,'教授','正高级','2018-01-11 08:00:00',1),
	(12,'助教','初级','2018-01-12 05:35:39',1),
	(13,'讲师','中级','2018-01-11 08:00:00',0),
	(14,'初级工程师','初级','2018-01-14 08:00:00',1),
	(16,'高级工程师','副高级','2018-01-15 00:19:14',1),
	(17,'骨灰级工程师','正高级','2018-01-15 00:19:24',1);

/*!40000 ALTER TABLE `joblevel` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mail_send_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mail_send_log`;

CREATE TABLE `mail_send_log` (
  `msgId` varchar(255) DEFAULT NULL,
  `empId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT 0 COMMENT '0发送中，1发送成功，2发送失败',
  `routeKey` varchar(255) DEFAULT NULL COMMENT '路由键',
  `exchange` varchar(255) DEFAULT NULL COMMENT '交换机',
  `count` int(11) DEFAULT 0 COMMENT '重试次数',
  `tryTime` datetime DEFAULT NULL COMMENT '第一次重试时间',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


# Dump of table menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL COMMENT '后端API请求的路径格式',
  `path` varchar(64) DEFAULT NULL COMMENT '前端页面的请求路径',
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT 1,
  `sortOrder` int(11) DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;

INSERT INTO `menu` (`id`, `url`, `path`, `component`, `name`, `iconCls`, `keepAlive`, `requireAuth`, `parentId`, `enabled`, `sortOrder`)
VALUES
	(1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1,0),
	(2,'/','/home','Home','员工资料','fa fa-user-circle-o',NULL,1,1,1,0),
	(6,'/','/home','Home','系统管理','fa fa-windows',NULL,1,1,1,2),
	(7,'/','/home','Home','股票管理','fa fa-windows',NULL,1,1,1,1),
	(107,'/employee/basic/**','/emp/basic','EmpBasic','基本资料',NULL,NULL,1,2,1,1),
	(123,'/system/basic/**','/sys/basic','SysBasic','基础信息设置',NULL,NULL,1,6,1,1),
	(126,'/system/hr/**','/sys/hr','SysHr','操作员管理',NULL,NULL,1,6,1,4),
	(129,'/system/databasetype/**','/sys/databasetype','SysDatabaseType','基础数据管理',NULL,NULL,1,6,1,7),
	(130,'/stock/basicinfo/**','/stock/basicinfo','StockBasicinfo','A股代码管理',NULL,NULL,1,7,1,1),
	(131,'/stock/weeklylineresult/**','/stock/weeklylineresult','StockWeeklylineResult','周线数据管理',NULL,NULL,1,7,1,2),
	(132,'/stock/weeklylineemaresult/**','/stock/weeklylineemaresult','StockWeeklylineEmaResult','周线ema数据管理',NULL,NULL,1,7,1,3),
	(133,'/stock/buyrule/**','/stock/buyrule','StockBuyRule','买入策略管理',NULL,NULL,1,7,1,4),
	(134,'/stock/hold/**','/stock/hold','StockHold','股票池管理',NULL,NULL,1,7,1,5),
	(135,'/stock/messageconf/**','/stock/messageconf','StockMessageConf','股票消息配置',NULL,NULL,1,7,1,6);

/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table menu_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu_role`;

CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mid` (`mid`),
  KEY `rid` (`rid`),
  CONSTRAINT `menu_role_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_role_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `menu_role` WRITE;
/*!40000 ALTER TABLE `menu_role` DISABLE KEYS */;

INSERT INTO `menu_role` (`id`, `mid`, `rid`)
VALUES
	(274,107,6),
	(275,123,6),
	(276,126,6),
	(277,129,6),
	(278,130,6),
	(279,131,6),
	(280,132,6),
	(281,133,6),
	(282,134,6),
	(283,135,6),
	(284,107,1);

/*!40000 ALTER TABLE `menu_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table msgcontent
# ------------------------------------------------------------

DROP TABLE IF EXISTS `msgcontent`;

CREATE TABLE `msgcontent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `createDate` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table nation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `nation`;

CREATE TABLE `nation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `nation` WRITE;
/*!40000 ALTER TABLE `nation` DISABLE KEYS */;

INSERT INTO `nation` (`id`, `name`)
VALUES
	(1,'汉族'),
	(2,'蒙古族'),
	(3,'回族'),
	(4,'藏族'),
	(5,'维吾尔族'),
	(6,'苗族'),
	(7,'彝族'),
	(8,'壮族'),
	(9,'布依族'),
	(10,'朝鲜族'),
	(11,'满族'),
	(12,'侗族'),
	(13,'瑶族'),
	(14,'白族'),
	(15,'土家族'),
	(16,'哈尼族'),
	(17,'哈萨克族'),
	(18,'傣族'),
	(19,'黎族'),
	(20,'傈僳族'),
	(21,'佤族'),
	(22,'畲族'),
	(23,'高山族'),
	(24,'拉祜族'),
	(25,'水族'),
	(26,'东乡族'),
	(27,'纳西族'),
	(28,'景颇族'),
	(29,'柯尔克孜族'),
	(30,'土族'),
	(31,'达斡尔族'),
	(32,'仫佬族'),
	(33,'羌族'),
	(34,'布朗族'),
	(35,'撒拉族'),
	(36,'毛难族'),
	(37,'仡佬族'),
	(38,'锡伯族'),
	(39,'阿昌族'),
	(40,'普米族'),
	(41,'塔吉克族'),
	(42,'怒族'),
	(43,'乌孜别克族'),
	(44,'俄罗斯族'),
	(45,'鄂温克族'),
	(46,'崩龙族'),
	(47,'保安族'),
	(48,'裕固族'),
	(49,'京族'),
	(50,'塔塔尔族'),
	(51,'独龙族'),
	(52,'鄂伦春族'),
	(53,'赫哲族'),
	(54,'门巴族'),
	(55,'珞巴族'),
	(56,'基诺族');

/*!40000 ALTER TABLE `nation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table oplog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oplog`;

CREATE TABLE `oplog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addDate` date DEFAULT NULL COMMENT '添加日期',
  `operate` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `hrid` int(11) DEFAULT NULL COMMENT '操作员ID',
  PRIMARY KEY (`id`),
  KEY `hrid` (`hrid`),
  CONSTRAINT `oplog_ibfk_1` FOREIGN KEY (`hrid`) REFERENCES `hr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table politicsstatus
# ------------------------------------------------------------

DROP TABLE IF EXISTS `politicsstatus`;

CREATE TABLE `politicsstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `politicsstatus` WRITE;
/*!40000 ALTER TABLE `politicsstatus` DISABLE KEYS */;

INSERT INTO `politicsstatus` (`id`, `name`)
VALUES
	(1,'中共党员'),
	(2,'中共预备党员'),
	(3,'共青团员'),
	(4,'民革党员'),
	(5,'民盟盟员'),
	(6,'民建会员'),
	(7,'民进会员'),
	(8,'农工党党员'),
	(9,'致公党党员'),
	(10,'九三学社社员'),
	(11,'台盟盟员'),
	(12,'无党派民主人士'),
	(13,'普通公民');

/*!40000 ALTER TABLE `politicsstatus` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table position
# ------------------------------------------------------------

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '职位',
  `createDate` timestamp NULL DEFAULT current_timestamp(),
  `enabled` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;

INSERT INTO `position` (`id`, `name`, `createDate`, `enabled`)
VALUES
	(29,'技术总监','2018-01-12 05:13:42',1),
	(30,'运营总监','2018-01-12 05:13:48',1),
	(31,'市场总监','2018-01-11 08:00:00',1),
	(33,'研发工程师','2018-01-14 08:00:00',0),
	(34,'运维工程师','2018-01-15 00:11:41',1);

/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `name`, `nameZh`)
VALUES
	(1,'ROLE_manager','部门经理'),
	(2,'ROLE_personnel','人事专员'),
	(3,'ROLE_recruiter','招聘主管'),
	(4,'ROLE_train','培训主管'),
	(6,'ROLE_admin','系统管理员');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table salary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `salary`;

CREATE TABLE `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `basicSalary` int(11) DEFAULT NULL COMMENT '基本工资',
  `bonus` int(11) DEFAULT NULL COMMENT '奖金',
  `lunchSalary` int(11) DEFAULT NULL COMMENT '午餐补助',
  `trafficSalary` int(11) DEFAULT NULL COMMENT '交通补助',
  `allSalary` int(11) DEFAULT NULL COMMENT '应发工资',
  `pensionBase` int(11) DEFAULT NULL COMMENT '养老金基数',
  `pensionPer` float DEFAULT NULL COMMENT '养老金比率',
  `createDate` timestamp NULL DEFAULT NULL COMMENT '启用时间',
  `medicalBase` int(11) DEFAULT NULL COMMENT '医疗基数',
  `medicalPer` float DEFAULT NULL COMMENT '医疗保险比率',
  `accumulationFundBase` int(11) DEFAULT NULL COMMENT '公积金基数',
  `accumulationFundPer` float DEFAULT NULL COMMENT '公积金比率',
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;



# Dump of table stock_a_trade_date
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_a_trade_date`;

CREATE TABLE `stock_a_trade_date` (
  `trade_date` varchar(255) CHARACTER SET utf8mb3 NOT NULL,
  PRIMARY KEY (`trade_date`),
  KEY `ix_vhr_stock_a_trade_date_trade_date` (`trade_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




# Dump of table stock_basic_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_basic_info`;

CREATE TABLE `stock_basic_info` (
  `code` varchar(255) CHARACTER SET utf8mb3 NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
  `date_publish` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `ix_vhr_stock_basic_info_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




# Dump of table stock_buy_rule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_buy_rule`;

CREATE TABLE `stock_buy_rule` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_market` int(11) NOT NULL DEFAULT 3 COMMENT '上市时长要求',
  `time_market_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '上市时长是否必要，0否，1是',
  `rule_period` int(11) NOT NULL DEFAULT 52 COMMENT '策略周期，单位为周',
  `turnover_limit` int(11) NOT NULL DEFAULT 0 COMMENT '成交量额度',
  `turnover_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '成交量额度是否必要，0否，1是',
  `conver_limit` float NOT NULL DEFAULT 1.1 COMMENT '收敛度，最大EMA75/当前ema18<conver_limit ',
  `conver_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '收敛度是否必要，0否，1是',
  `shock_limit` float NOT NULL DEFAULT 0.45 COMMENT '下跌幅度,最低价/最高价<=shock_limit',
  `shock_limit_option` tinyint(1) NOT NULL DEFAULT 1 COMMENT '下跌幅度是否必要，0否，1是',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '2结束，1运行，0草稿',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `stock_buy_rule` WRITE;
/*!40000 ALTER TABLE `stock_buy_rule` DISABLE KEYS */;

INSERT INTO `stock_buy_rule` (`id`, `time_market`, `time_market_option`, `rule_period`, `turnover_limit`, `turnover_limit_option`, `conver_limit`, `conver_limit_option`, `shock_limit`, `shock_limit_option`, `time_create`, `time_update`, `status`)
VALUES
	(7,3,1,52,0,1,1.1,1,0.45,1,NULL,NULL,2),
	(8,3,1,52,0,1,1.1,1,0.45,0,NULL,'2022-11-01 18:47:39',2),
	(9,3,1,52,0,0,1.1,1,0.45,1,'2022-10-20 12:02:45','2022-11-01 18:47:39',1);

/*!40000 ALTER TABLE `stock_buy_rule` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table stock_hold
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_hold`;

CREATE TABLE `stock_hold` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '股票代码',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `status` int(11) DEFAULT 0 COMMENT '状态，0未购买、1购买中、2暂停购买、3已购买、4卖出中、5暂定卖出、6交易结束',
  `buy_amount` int(11) DEFAULT 0 COMMENT '买入股票数',
  `buy_price` float DEFAULT 0 COMMENT '买入价格',
  `hold_amount` int(11) DEFAULT 0 COMMENT '持有股票数',
  `sell_stage` int(11) DEFAULT 0 COMMENT '卖出阶段',
  `weekly_ema_result_id` int(11) DEFAULT NULL COMMENT '关联的ema_result_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table stock_hold_trade
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_hold_trade`;

CREATE TABLE `stock_hold_trade` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `hold_id` int(11) unsigned NOT NULL,
  `code` varchar(255) NOT NULL DEFAULT '',
  `amount` int(11) DEFAULT 100 COMMENT '委托数量',
  `trade_type` int(11) DEFAULT 0 COMMENT '交易类型：0买入，1卖出，2买入撤销，3卖出撤销',
  `trade_note` varchar(500) DEFAULT NULL COMMENT '交易备注',
  `orderid` varchar(50) DEFAULT NULL COMMENT '委托合同编号',
  `message` varchar(1000) DEFAULT NULL COMMENT '委托/撤单状态信息',
  `status` int(11) DEFAULT NULL COMMENT '委托/撤单状态，1结束，0执行中，-1失败',
  `note` varchar(1000) DEFAULT NULL COMMENT '投资备注',
  `taskid` varchar(50) DEFAULT NULL COMMENT '任务/指令编号',
  `taskstatus` int(11) DEFAULT NULL COMMENT '任务/指令状态，0 未知,1 等待,2 提交中,3 执行中,4 暂停,5 撤销中,6 异常撤销中,7 完成,8 已撤,9 打回,10  异常终止,11  放弃，目前用于组合交易中，放弃补单,12  强制终止',
  `taskmsg` varchar(1000) DEFAULT NULL COMMENT '任务/指令状态信息',
  `taskpro` varchar(500) DEFAULT NULL COMMENT '任务进度',
  `ordernum` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `time_update` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `price` float DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `hold_id_fk` (`hold_id`),
  CONSTRAINT `hold_id_fk` FOREIGN KEY (`hold_id`) REFERENCES `stock_hold` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table stock_message_conf
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_message_conf`;

CREATE TABLE `stock_message_conf` (
  `empid` int(11) NOT NULL COMMENT '员工id',
  `message_type` varchar(10) DEFAULT NULL COMMENT '消息分类（信号发现、买入、卖出），采用二进制机制保存，例如100，表示接收信号发现消息',
  `send_type` varchar(5) DEFAULT NULL COMMENT '发送形式（0短信、1邮件、2微信服务通知），采用二进制机制表示',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否生效',
  UNIQUE KEY `empid_unique` (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table stock_message_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_message_log`;

CREATE TABLE `stock_message_log` (
  `msgId` varchar(255) DEFAULT NULL,
  `empId` int(11) DEFAULT NULL COMMENT '员工id',
  `status` int(11) DEFAULT 0 COMMENT '0发送中，1发送成功，2发送失败',
  `routeKey` varchar(255) DEFAULT NULL COMMENT '路由键',
  `exchange` varchar(255) DEFAULT NULL COMMENT '交换机',
  `count` int(11) DEFAULT 0 COMMENT '重试次数',
  `time_try` timestamp NULL DEFAULT NULL COMMENT '第一次重试时间',
  `time_create` timestamp NULL DEFAULT NULL,
  `time_update` timestamp NULL DEFAULT NULL,
  `message_type` int(11) DEFAULT NULL COMMENT '消息分类（信号发现、买入、卖出）',
  `send_type` int(11) DEFAULT NULL COMMENT '发送形式（0短信、1邮件、2微信服务通知）',
  `content` longtext DEFAULT NULL COMMENT '消息内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table stock_weekly_line_ema_result
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_weekly_line_ema_result`;

CREATE TABLE `stock_weekly_line_ema_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `time_create` timestamp NULL DEFAULT NULL COMMENT 'ema_result创建时间',
  `time_end` timestamp NULL DEFAULT NULL COMMENT 'ema_result创建成功时间',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT 'ema生成状态，0成功，-1失败',
  `status_desc` longtext DEFAULT NULL COMMENT 'ema生成结果备注信息',
  `weekly_line_id` int(11) unsigned DEFAULT NULL,
  `ema_index` varchar(50) DEFAULT NULL COMMENT 'ema数据线，例如18_25_75',
  `time_run_begin` timestamp NULL DEFAULT NULL COMMENT 'ema数据运行买入规则开始时间',
  `time_run_end` timestamp NULL DEFAULT NULL COMMENT 'ema数据运行买入规则结束时间',
  `run_status` int(11) DEFAULT 0 COMMENT 'ema运行买入规则状态，0未运行，1运行成功，-1运行失败',
  `run_status_desc` longtext DEFAULT NULL COMMENT 'ema运行买入规则结果备注',
  PRIMARY KEY (`id`),
  KEY `weekly_line_result_id_fk` (`weekly_line_id`),
  CONSTRAINT `weekly_line_result_id_fk` FOREIGN KEY (`weekly_line_id`) REFERENCES `stock_weekly_line_result` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




# Dump of table stock_weekly_line_result
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stock_weekly_line_result`;

CREATE TABLE `stock_weekly_line_result` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rehabilitation_way` varchar(10) DEFAULT NULL COMMENT '复权方式',
  `date_weekly_research` varchar(8) NOT NULL DEFAULT '' COMMENT '周线调研日期',
  `date_weekly_start` varchar(8) NOT NULL DEFAULT '' COMMENT '周线调研起始日期',
  `result` int(11) NOT NULL DEFAULT 0 COMMENT '周线导入结果，0成功，1失败',
  `ema_result` int(11) NOT NULL DEFAULT 0 COMMENT 'expma生成状态（0未生成、1已生成、2生成失败）',
  `time_create` timestamp NULL DEFAULT NULL COMMENT '运行开始时间',
  `time_end` timestamp NULL DEFAULT NULL COMMENT '运行结束时间',
  `generate_type` int(11) DEFAULT 0 COMMENT '生成方式，0手动，1自动',
  `result_desc` longtext DEFAULT NULL COMMENT '生成结果备注',
  `ema_result_desc` longtext DEFAULT NULL COMMENT 'ema生成结果备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table sysmsg
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sysmsg`;

CREATE TABLE `sysmsg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL COMMENT '消息id',
  `type` int(11) DEFAULT 0 COMMENT '0表示群发消息',
  `hrid` int(11) DEFAULT NULL COMMENT '这条消息是给谁的',
  `state` int(11) DEFAULT 0 COMMENT '0 未读 1 已读',
  PRIMARY KEY (`id`),
  KEY `hrid` (`hrid`),
  KEY `sysmsg_ibfk_1` (`mid`),
  CONSTRAINT `sysmsg_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `msgcontent` (`id`),
  CONSTRAINT `sysmsg_ibfk_2` FOREIGN KEY (`hrid`) REFERENCES `hr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;




--
-- Dumping routines (PROCEDURE) for database 'vhr'
--
DELIMITER ;;

# Dump of PROCEDURE addDep
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `addDep` */;;
/*!50003 SET SESSION SQL_MODE="NO_AUTO_VALUE_ON_ZERO"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `addDep`(in depName varchar(32),in parentId int,in enabled boolean,out result int,out result2 int)
begin
  declare did int;
  declare pDepPath varchar(64);
  insert into department set name=depName,parentId=parentId,enabled=enabled;
  select row_count() into result;
  select last_insert_id() into did;
  set result2=did;
  select depPath into pDepPath from department where id=parentId;
  update department set depPath=concat(pDepPath,'.',did) where id=did;
  update department set isParent=true where id=parentId;
end */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
# Dump of PROCEDURE deleteDep
# ------------------------------------------------------------

/*!50003 DROP PROCEDURE IF EXISTS `deleteDep` */;;
/*!50003 SET SESSION SQL_MODE="NO_AUTO_VALUE_ON_ZERO"*/;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `deleteDep`(in did int,out result int)
begin
  declare ecount int;
  declare pid int;
  declare pcount int;
  declare a int;
  select count(*) into a from department where id=did and isParent=false;
  if a=0 then set result=-2;
  else
  select count(*) into ecount from employee where departmentId=did;
  if ecount>0 then set result=-1;
  else
  select parentId into pid from department where id=did;
  delete from department where id=did and isParent=false;
  select row_count() into result;
  select count(*) into pcount from department where parentId=pid;
  if pcount=0 then update department set isParent=false where id=pid;
  end if;
  end if;
  end if;
end */;;

/*!50003 SET SESSION SQL_MODE=@OLD_SQL_MODE */;;
DELIMITER ;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
