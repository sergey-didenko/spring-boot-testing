--liquibase formatted sql

--changeset Svigelf:4
CREATE TABLE `subject`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `s_si` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = MyISAM AUTO_INCREMENT = 1;
--rollback drop table subject;