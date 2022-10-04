/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 8.0.18 : Database - vhr
*********************************************************************
*/
/* the name of database script is defined by：V<VERSION>__<NAME>.sql */

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

-- auto-generated definition

DROP TABLE IF EXISTS `database_type`;

create table database_type
(
    id          int           not null
        primary key,
    code        varchar(50)  not null,
    name        varchar(50)  not null,
    value       varchar(255)  not null,
    description varchar(500)  null,
    sort_order  int default 0 null comment '排序',
    type        varchar(100)  null comment '类型'
)
    comment 'basic data information';

