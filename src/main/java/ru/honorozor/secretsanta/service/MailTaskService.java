package ru.honorozor.secretsanta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.honorozor.secretsanta.model.MailTask;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.repository.MailRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MailTaskService {

    private final MailRepository mailRepository;
    private final TextService textService;

    @Transactional
    public void create(List<User> users) {
        users.stream()
                .map(user -> MailTask.builder()
                        .to(user.getEmail())
                        .text(textService.createText(user, users))
                        .build())
                .forEach(this::create);
    }

    @Transactional
    public void create(MailTask mailTask) {
        save(mailTask);
    }

    private void save(MailTask mailTask) {
        mailRepository.save(mailTask);
    }

    public List<MailTask> findAll() {
        return mailRepository.findAll();
    }
}
