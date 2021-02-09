package ru.honorozor.secretsanta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SecretsantaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecretsantaApplication.class, args);
    }

}