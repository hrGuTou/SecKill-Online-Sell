drop database SecKillProject;
create database SecKillProject;
use secKillProject;
drop table if exists merchandise;
CREATE TABLE merchandise (
  merchandise_id bigint(20) NOT NULL AUTO_INCREMENT ,
  merchandise_name varchar(64) DEFAULT NULL ,
  merchandise_img varchar(64) DEFAULT NULL ,
  merchandise_detail longtext ,
  merchandise_price decimal(10,2) DEFAULT NULL,
  merchandise_stock int(11) DEFAULT '0' COMMENT '-1: no limit',
	PRIMARY KEY (`merchandise_id`)
) ENGINE=InnoDB default CHARSET = utf8;

drop table if exists orderInfo;
CREATE TABLE orderInfo (
	order_id bigint(20) NOT NULL AUTO_INCREMENT,
    user_id bigint(20) default null,
    merchandise_id bigint(20) default null,
    order_status tinyint(4) DEFAULT NULL COMMENT '0: new created, 1 paid',
    create_date datetime default null,
    pay_date datetime default null,
	  primary key(`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- only one order per user
DROP TABLE IF EXISTS `sk_order`;
CREATE TABLE `sk_order` (
  `skorder_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `merchandise_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`skorder_id`),
  UNIQUE KEY `u_uid_gid` (`user_id`,`merchandise_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




drop table if exists `user`;
CREATE TABLE `user` (
  user_id bigint(20) unsigned NOT NULL,
  nickname varchar(255) NOT NULL ,
  password varchar(255) NOT NULL ,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

