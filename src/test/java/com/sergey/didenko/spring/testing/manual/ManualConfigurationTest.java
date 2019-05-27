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

package com.sergey.didenko.spring.testing.manual;

import com.sergey.didenko.spring.testing.config.ManualContextConfig;
import com.sergey.didenko.spring.testing.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO : This annotation disabled AutoContext
@ContextConfiguration(classes = {
        ManualContextConfig.class
})
//TODO : Or use this, who will import in existing context, instead below (the same result)
//@Import(ManualContextConfig.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=true"
})
public class ManualConfigurationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ApplicationContext appContext;

    @Test
    public void manualConfigurationTest_check01() {
        assertThat(studentRepository).isNotNull();

        DriverManagerDataSource dataSource = (DriverManagerDataSource) appContext.getBean(DataSource.class);
        assertThat(dataSource).isNotNull();

        String actual = "jdbc:mariadb://localhost:3306/testing?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&createDatabaseIfNotExist=true";
        assertEquals(actual, dataSource.getUrl());
    }
}
