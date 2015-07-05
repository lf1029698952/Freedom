DROP DATABASE IF EXISTS weibo;
create database weibo;
use weibo;
DROP TABLE IF EXISTS `hd_user` ;
CREATE  TABLE IF NOT EXISTS `hd_user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `account` CHAR(20) NOT NULL DEFAULT '' COMMENT '账号' ,
  `password` CHAR(32) NOT NULL DEFAULT '' COMMENT '密码' ,
  `registime` INT(10) UNSIGNED NOT NULL COMMENT '注册时间' ,
  `lock` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否锁定（0：否，1：是）' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `account` (`account` ASC) )
DEFAULT CHARSET=utf8,
COMMENT = '用户表';


DROP TABLE IF EXISTS `hd_userinfo` ;

CREATE  TABLE IF NOT EXISTS `hd_userinfo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '用户昵称' ,
  `truename` VARCHAR(45) NULL COMMENT '真实名称' ,
  `sex` tinyint NOT NULL DEFAULT '1' COMMENT '性别' ,
  `location` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '所在地' ,
  `constellation` CHAR(10) NOT NULL DEFAULT '' COMMENT '星座' ,
  `intro` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '一句话介绍自己' ,
  `face50` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '50*50头像' ,
  `face80` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '80*80头像' ,
  `face180` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '180*180头像' ,
  `style` VARCHAR(45) NOT NULL DEFAULT 'default' COMMENT '个性模版' ,
  `follow` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '关注数' ,
  `fans` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '粉丝数' ,
  `weibo` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '微博数' ,
  `uid` INT NOT NULL COMMENT '所属用户ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `uid` (`uid` ASC) )
DEFAULT CHARSET=utf8,
COMMENT = '用户信息表';


DROP TABLE IF EXISTS `hd_group` ;

CREATE  TABLE IF NOT EXISTS `hd_group` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '分组名称' ,
  `uid` INT NOT NULL COMMENT '所属用户的ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `uid` (`uid` ASC) )
DEFAULT CHARSET=utf8,
COMMENT = '关注分组表';

DROP TABLE IF EXISTS `hd_follow` ;

CREATE  TABLE IF NOT EXISTS `hd_follow` (
  `follow` INT UNSIGNED NOT NULL COMMENT '关注用户的ID' ,
  `fans` INT UNSIGNED NOT NULL COMMENT '粉丝用户ID' ,
  `gid` INT NOT NULL COMMENT '所属关注分组ID' ,
  INDEX `follow` (`follow` ASC) ,
  INDEX `fans` (`fans` ASC) ,
  INDEX `gid` (`gid` ASC) )
DEFAULT CHARSET=utf8,
COMMENT = '关注与粉丝表';

DROP TABLE IF EXISTS `hd_letter` ;

CREATE  TABLE IF NOT EXISTS `hd_letter` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `from` INT NOT NULL COMMENT '发私用户ID' ,
  `content` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '私信内容' ,
  `time` INT(10) UNSIGNED NOT NULL COMMENT '私信发送时间' ,
  `uid` INT NOT NULL COMMENT '所属用户ID（收信人）' ,
  PRIMARY KEY (`id`) ,
  INDEX `uid` (`uid` ASC) )
DEFAULT CHARSET=utf8,
COMMENT = '私信表';


DROP TABLE IF EXISTS `hd_weibo` ;

CREATE  TABLE IF NOT EXISTS `hd_weibo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '微博内容' ,
  `isturn` INT NOT NULL DEFAULT 0 COMMENT '是否转发（0：原创， 如果是转发的则保存该转发微博的ID）' ,
  `time` INT(10) UNSIGNED NOT NULL COMMENT '发布时间' ,
  `turn` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '转发次数' ,
  `keep` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏次数' ,
  `comment` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏次数' ,
  `uid` INT NOT NULL COMMENT '所属用户的ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `uid` (`uid` ASC) )
DEFAULT CHARSET=utf8,
COMMENT = '微博表';

DROP TABLE IF EXISTS `hd_picture` ;

CREATE  TABLE IF NOT EXISTS `hd_picture` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `mini` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '小图' ,
  `medium` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '中图' ,
  `max` VARCHAR(60) NOT NULL DEFAULT '' COMMENT '大图' ,
  `wid` INT NOT NULL COMMENT '所属微博ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `wid` (`wid` ASC) )
  DEFAULT CHARSET=utf8,
COMMENT = '微博配图表';


DROP TABLE IF EXISTS `hd_comment` ;

CREATE  TABLE IF NOT EXISTS `hd_comment` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '评论内容' ,
  `time` INT(10) UNSIGNED NOT NULL COMMENT '评论时间' ,
  `uid` INT NOT NULL COMMENT '评论用户的ID' ,
  `wid` INT NOT NULL COMMENT '所属微博ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `uid` (`uid` ASC) ,
  INDEX `wid` (`wid` ASC) )
  DEFAULT CHARSET=utf8,
COMMENT = '微博评论表';


DROP TABLE IF EXISTS `hd_keep` ;

CREATE  TABLE IF NOT EXISTS `hd_keep` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `uid` INT NOT NULL COMMENT '收藏用户的ID' ,
  `time` INT(10) UNSIGNED NOT NULL COMMENT '收藏时间' ,
  `wid` INT NOT NULL COMMENT '收藏微博的ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `wid` (`wid` ASC) ,
  INDEX `uid` (`uid` ASC) )
  DEFAULT CHARSET=utf8,
COMMENT = '收藏表';

DROP TABLE IF EXISTS `hd_atme` ;

CREATE  TABLE IF NOT EXISTS `hd_atme` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `wid` INT NOT NULL COMMENT '提到我的微博ID' ,
  `uid` INT NOT NULL COMMENT '所属用户ID' ,
  PRIMARY KEY (`id`) ,
  INDEX `uid` (`uid` ASC) ,
  INDEX `wid` (`wid` ASC) )
  DEFAULT CHARSET=utf8,
COMMENT = '@提到我的微博';

DROP TABLE IF EXISTS `hd_admin` ;
create table hd_admin(
	id int unsigned not null primary key auto_increment,
	username char(20) not null default '',
	password char(32) not null default '',
	logintime int(10) unsigned not null default 0,
	loginip char(20) not null default '',
	`lock` tinyint(1) unsigned not null default 0,
	`admin` tinyint(1) unsigned not null default 1
)DEFAULT CHARSET=utf8;

insert into hd_admin set username='admin',password=md5('admin'),logintime=unix_timestamp(now()),loginip='127.0.0.1',admin=0;
