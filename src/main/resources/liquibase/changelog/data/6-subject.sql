--liquibase formatted sql

--changeset Svigelf:6
--preconditions onFail:CONTINUE onError:CONTINUE
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM subject
INSERT INTO `subject` VALUES ('1', 'subject', '1');
INSERT INTO `subject` VALUES ('2', 'subject2', '1');
INSERT INTO `subject` VALUES ('3', 'subject3', '2');
INSERT INTO `subject` VALUES ('4', 'subject4', '2');
INSERT INTO `subject` VALUES ('5', 'subject5', '3');
--rollback delete from subject where id in (1, 2, 3, 4, 5);
