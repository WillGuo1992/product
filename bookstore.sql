/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.0.27-community-nt : Database - bookstore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bookstore` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bookstore`;

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL default '',
  `product_id` varchar(100) NOT NULL default '',
  `buynum` int(11) default NULL,
  PRIMARY KEY  (`order_id`,`product_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

insert  into `orderitem`(`order_id`,`product_id`,`buynum`) values ('4fea61eb-381f-4cc8-8cf1-37a1b20d434b','111',1),('86f2c937-4b1c-4c98-b675-f6cb1ac9ed93','111',3),('86f2c937-4b1c-4c98-b675-f6cb1ac9ed93','2',1),('86f2c937-4b1c-4c98-b675-f6cb1ac9ed93','666',1),('ae772770-753d-41a1-a8eb-b104c4b5c195','111',1),('b44f7ca3-b906-4b04-825a-511a15bd38b4','111',3),('b44f7ca3-b906-4b04-825a-511a15bd38b4','2',1),('b44f7ca3-b906-4b04-825a-511a15bd38b4','666',1),('c0a2fb65-aa22-4bbf-80ae-16b9e37e4e48','222',1),('c0a2fb65-aa22-4bbf-80ae-16b9e37e4e48','333',1),('de8aa1ef-7f74-41f1-ae99-4602fa487153','111',3),('de8aa1ef-7f74-41f1-ae99-4602fa487153','2',1),('de8aa1ef-7f74-41f1-ae99-4602fa487153','666',1);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL default '',
  `money` double default NULL,
  `receiverAddress` varchar(255) default NULL,
  `receiverName` varchar(20) default NULL,
  `receiverPhone` varchar(20) default NULL,
  `paystate` int(11) default NULL,
  `ordertime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`money`,`receiverAddress`,`receiverName`,`receiverPhone`,`paystate`,`ordertime`,`user_id`) values ('4fea61eb-381f-4cc8-8cf1-37a1b20d434b',111,'222','333','444',0,'2017-08-03 17:12:24',1),('86f2c937-4b1c-4c98-b675-f6cb1ac9ed93',1001,'','','',0,'2017-08-03 14:56:21',1),('ae772770-753d-41a1-a8eb-b104c4b5c195',111,'111','22','333',0,'2017-08-03 17:09:58',1),('b44f7ca3-b906-4b04-825a-511a15bd38b4',1001,'','','',0,'2017-08-03 14:57:42',1),('c0a2fb65-aa22-4bbf-80ae-16b9e37e4e48',555,'1','2','3',0,'2017-08-03 15:20:40',1),('de8aa1ef-7f74-41f1-ae99-4602fa487153',1001,'','','',0,'2017-08-03 14:59:28',1);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` varchar(100) NOT NULL default '',
  `name` varchar(40) default NULL,
  `price` double default NULL,
  `category` varchar(40) default NULL,
  `pnum` int(11) default NULL,
  `imgurl` varchar(100) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `products` */

insert  into `products`(`id`,`name`,`price`,`category`,`pnum`,`imgurl`,`description`) values ('1','1',1,'文学',0,'1','1'),('1000','1000',1000,'生活',1000,'1000','1000'),('111','111',111,'文学',104,'111','111'),('2','2',2,'文学',0,'2','2'),('222','222',222,'文学',221,'222','222'),('3','3',3,'文学',3,'3','3'),('333','333',333,'文学',332,'333','333'),('444','444',444,'生活',444,'444','444'),('555','555',555,'文学',555,'555','555'),('666','666',666,'文学',664,'666','666'),('777','777',777,'文学',777,'777','777'),('888','888',888,'文学',888,'888','888'),('999','999',999,'生活',999,'999','999');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(20) default NULL,
  `PASSWORD` varchar(20) default NULL,
  `gender` varchar(10) default NULL,
  `email` varchar(50) default NULL,
  `telephone` varchar(20) default NULL,
  `introduce` varchar(100) default NULL,
  `activeCode` varchar(50) default NULL,
  `state` int(11) default '0',
  `role` varchar(10) default '普通用户',
  `registTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`PASSWORD`,`gender`,`email`,`telephone`,`introduce`,`activeCode`,`state`,`role`,`registTime`) values (1,'123','123','男','123@qq.com','123','123','1111111111111',1,'普通用户','2017-08-02 14:58:13'),(2,'admin','123','女','admin@qq.com','123','123','123333333',1,'admin','2017-08-02 14:58:48');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
