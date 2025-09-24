package com.company.architecture;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class PostgreTestConfiguration {
    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreContainer() {
        return new PostgreSQLContainer<>("postgres");
    }
}
