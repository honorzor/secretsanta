package ru.honorozor.secretsanta;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.honorozor.secretsanta.initializer.Mysql;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(
        initializers = Mysql.initializer.class
)
@Transactional
public abstract class TestBase {

    @BeforeAll
    static void init() {
        Mysql.conteiner.start();
    }
}
