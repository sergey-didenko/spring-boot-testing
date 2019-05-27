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

package com.sergey.didenko.spring.testing.config;

import com.sergey.didenko.spring.testing.domain.Student;
import com.sergey.didenko.spring.testing.repository.StudentRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@Import(LiquibaseConfiguration.class)
//TODO : Init Context, we can specify classes
@EntityScan(basePackageClasses = {
        Student.class
})
//TODO : Or packages
//@EntityScan(value = "com.sergey.didenko.spring.testing.domain")
@EnableJpaRepositories(basePackageClasses = {
        StudentRepository.class
})
@ComponentScan(basePackages = "com.sergey.didenko.spring.testing.service")
public class ManualContextConfig {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://localhost:3306/testing?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&createDatabaseIfNotExist=true");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);

        //TODO :
        factory.setPackagesToScan(getEntityPackageList());

        factory.setDataSource(dataSource());

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

        factory.setJpaPropertyMap(properties);
        return factory;
    }

    private String[] getEntityPackageList() {
        List<String> entityPackageList = new ArrayList<>();

        if (ManualContextConfig.class.isAnnotationPresent(EntityScan.class)) {
            EntityScan entityScan = ManualContextConfig.class.getAnnotation(EntityScan.class);

            processPackages(entityPackageList, entityScan.value());
            processPackages(entityPackageList, entityScan.basePackages());

            processPackageClasses(entityPackageList, entityScan.basePackageClasses());
        }

        String[] entityPackages = new String[entityPackageList.size()];
        entityPackageList.toArray(entityPackages);

        return entityPackages;
    }

    private void processPackages(List<String> entityPackageList, String[] packages) {
        for (String packageName : packages) {
            if (!entityPackageList.contains(packageName)) {
                entityPackageList.add(packageName);
            }
        }
    }

    private void processPackageClasses(List<String> entityPackageList, Class<?> [] classes) {
        for (Class<?> entityClass : classes) {
            String packageName = entityClass.getPackage().getName();
            if (!entityPackageList.contains(packageName)) {
                entityPackageList.add(packageName);
            }
        }
    }

}
