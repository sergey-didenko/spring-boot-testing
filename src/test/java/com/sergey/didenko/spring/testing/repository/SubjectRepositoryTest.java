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

import com.sergey.didenko.spring.testing.config.LiquibaseConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//TODO : Init Context Automatically
//TODO : Auto configuration is enabled, but only for Entities and Repositories
@DataJpaTest
@Import(LiquibaseConfiguration.class)
@TestPropertySource(properties = {
        "spring.liquibase.enabled=false"
})
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @org.junit.jupiter.api.Test
    public void subjectRepository_check01() {
        assertThat(subjectRepository).isNotNull();
    }
}
