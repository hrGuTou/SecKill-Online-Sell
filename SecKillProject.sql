create database SecKillProject;
use secKillProject;
drop table if exists sk_goods;
CREATE TABLE sk_goods (
  id bigint(20) NOT NULL AUTO_INCREMENT ,
  goods_name varchar(30) DEFAULT NULL ,
  goods_title varchar(64) DEFAULT NULL ,
  goods_img varchar(64) DEFAULT NULL ,
  goods_detail longtext ,
  goods_price decimal(10,2) DEFAULT NULL,
  goods_stock int(11) DEFAULT '0' COMMENT '-1: no limit',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB default CHARSET = utf8;

drop table if exists sk_goods_seckill; 
CREATE TABLE sk_goods_seckill (
  id bigint(20) NOT NULL AUTO_INCREMENT ,
  goods_id bigint(20) DEFAULT NULL ,
  seckill_price decimal(10,2) DEFAULT '0.00',
  stock_count int(11) DEFAULT NULL,
  start_date datetime DEFAULT NULL,
  end_date datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (goods_id) REFERENCES sk_goods(id)
	ON DELETE cascade 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists sk_user;
CREATE TABLE sk_user (
  userId bigint(20) unsigned NOT NULL,
  nickname varchar(255) NOT NULL ,
  password varchar(255) NOT NULL ,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

