package ru.honorozor.secretsanta.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.honorozor.secretsanta.container.TestWithMysqlContainer;
import ru.honorozor.secretsanta.model.MailTask;
import ru.honorozor.secretsanta.model.User;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(value = SpringExtension.class)
class MailTaskServiceTest extends TestWithMysqlContainer {

    @Autowired
    private MailTaskService mailTaskService;

    @MockBean
    private JavaMailSenderImpl javaMailSender;

    @Test
    public void shouldSendTask() {
        final List<User> users = Arrays.asList(
                User.builder()
                        .email("test1@mail.ru")
                        .name("test1")
                        .emailToBuyGift("test2@mail.ru")
                        .build(),
                User.builder()
                        .email("test2@mail.ru")
                        .name("test2")
                        .emailToBuyGift("test3@mail.ru")
                        .build(),
                User.builder()
                        .email("test3@mail.ru")
                        .name("test3")
                        .emailToBuyGift("test1@mail.ru")
                        .build()
        );

        mailTaskService.create(users);

        final List<MailTask> task = mailTaskService.findAll();
        Assertions.assertTrue(task.get(0).getText().contains("test2@mail.ru"));
        Assertions.assertTrue(task.get(1).getText().contains("test3@mail.ru"));
        Assertions.assertTrue(task.get(2).getText().contains("test1@mail.ru"));

        Assertions.assertEquals(task.get(0).getTo(), "test1@mail.ru");
        Assertions.assertEquals(task.get(1).getTo(), "test2@mail.ru");
        Assertions.assertEquals(task.get(2).getTo(), "test3@mail.ru");
    }
}
