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

import com.sergey.didenko.spring.testing.config.LiquibaseConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//TODO : Run with Liquibase, but also need to add Context, like below
@ContextConfiguration(classes = {LiquibaseConfiguration.class})
//TODO : And specify, what will be load (JPA Repositories, Entity, etc)
//@EntityScan(basePackageClasses = {
//        Subject.class//TODO : just for example
//})
//@EnableJpaRepositories(basePackageClasses = {
//        SubjectRepository.class//TODO : just for example
//})
//TODO : Or use AutoConfiguration
@EnableAutoConfiguration
//TODO : Run with Liquibase
@TestPropertySource(properties = {
        "spring.liquibase.enabled=true"
})
@ExtendWith(SpringExtension.class)
public class LiquibaseTest {

    @org.junit.jupiter.api.Test
    public void liquibaseTest_check01() {
    }

}
