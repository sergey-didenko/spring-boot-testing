--liquibase formatted sql

--changeset Svigelf:5
--preconditions onFail:CONTINUE onError:CONTINUE
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM student
INSERT INTO `student` VALUES ('1', 'first', 'somename');
INSERT INTO `student` VALUES ('2', 'second', 'somename2');
INSERT INTO `student` VALUES ('3', 'third', 'somename3');
--rollback delete from student where id in (1, 2, 3);
