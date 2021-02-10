package ru.honorozor.secretsanta.initializer;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

@UtilityClass
public class Mysql {

    public static final MySQLContainer<?> conteiner = new MySQLContainer<>("mysql:5.7");

    public static class initializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + conteiner.getJdbcUrl(),
                    "spring.datasource.username=" + conteiner.getUsername(),
                    "spring.datasource.password=" + conteiner.getPassword()
            ).applyTo(applicationContext);
        }
    }

}
