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

package com.sergey.didenko.spring.testing;

import com.sergey.didenko.spring.testing.config.AutoContextConfig;
import com.sergey.didenko.spring.testing.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
//TODO : Init Context Automatically
//TODO : Auto configuration is enabled, but only for Entities and Repositories
@EnableAutoConfiguration
//TODO : Will import in existing context
@Import(AutoContextConfig.class)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=true"
})
public class AutoConfigurationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ApplicationContext appContext;

    @Test
    public void autoConfigurationTest_check01() {
        assertThat(studentRepository).isNotNull();

        DriverManagerDataSource dataSource = (DriverManagerDataSource) appContext.getBean(DataSource.class);
        assertThat(dataSource).isNotNull();

        String actual = "jdbc:mariadb://localhost:3306/testing?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&createDatabaseIfNotExist=true";
        assertEquals(actual, dataSource.getUrl());
    }
}