-- liquibase formatted sql
-- changeset arpit:01_create_database.sql

set foreign_key_checks=0;

create database if not exists retail_store;
use retail_store;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `email_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_item_id_fk` (`item_id`),
  CONSTRAINT `order_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

set foreign_key_checks=1;
