/*
Copyright 2019 Sergey Didenko <sergey.didenko.dev@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.sergey.didenko.spring.testing.repository;

import com.sergey.didenko.spring.testing.domain.Student;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//TODO : Enable Transactions, Rollback, Load DataSource, and JPA repositories (if @ContextConfiguration not specify)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @org.junit.jupiter.api.Test
    @Sql("classpath:liquibase/changelog/structure/3-student.sql")
    @Sql("classpath:sql/check01.sql")
    public void studentRepository_check01() {

        assertThat(studentRepository).isNotNull();

        Student expected = new Student();
        expected.setFirstName("one");
        expected.setLastName("one");
        expected.setSubjectList(new ArrayList<>());

        expected = studentRepository.save(expected);

        Optional<Student> studentOprional = studentRepository.findById(expected.getId());
        Student actual = studentOprional.orElse(null);

        assertThat(expected).isNotNull();
        assertThat(actual).isNotNull();

        System.out.println(expected.toString());
        System.out.println(actual.toString());

        Assert.assertEquals(expected, actual);
    }
}
