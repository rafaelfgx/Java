package com.company.architecture;

import com.company.architecture.shared.configurations.ExceptionConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration(proxyBeanMethods = false)
@Import({
    ExceptionConfiguration.class,
    KafkaTestConfiguration.class,
    LocalStackTestConfiguration.class,
    MongoTestConfiguration.class,
    PostgreTestConfiguration.class
})
public class SpringBootTestConfiguration {
}
