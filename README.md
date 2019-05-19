# Spring-Boot-Testing



## Technology stack
 
 **Back-end stack :**
 - [JPAModelGen][] - meta-model generator for JPA2
 - [Liquibase][] - DB migration tool
 - [Spring boot][] - java web framework
 - [Embedded Tomcat][] - servlet container to run project
 - [Gradle][] - build system

## Build configuration
#### Terminal tasks

**BootRun** - build and run project via terminal

    bash ./gradlew bootRun

    gradlew.bat bootRun

**BootWar** - build project for production

    bash ./gradlew bootWar

    gradlew.bat bootWar

**Jar** Command to run binary

    java -jar build/libs/*.war

#### Intellij IDEA configuration

 - **Delegate to gradle** - 
 Press **Ctrl+Alt+S**, go to **Build, Execution, Deployment** -> **Build Tools** -> **Gradle** -> **Runner**
 and enable checkbox **Delegate IDE build/run actions to gradle**

[Spring boot]: https://spring.io/
[JPAModelGen]: https://docs.jboss.org/hibernate/jpamodelgen/1.0/reference/en-US/html_single
[Liquibase]: https://www.liquibase.org/
[Embedded Tomcat]: http://tomcat.apache.org/
[Gradle]: https://gradle.org/
