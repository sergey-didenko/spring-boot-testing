--liquibase formatted sql

--changeset Svigelf:1
CREATE TABLE `test`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = MyISAM AUTO_INCREMENT = 1;
--rollback drop table test;
