package ru.honorozor.secretsanta.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.honorozor.secretsanta.model.MailTask;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.repository.MailRepository;

import java.util.List;

@Service
public class MailTaskService {

    private final MailRepository mailRepository;

    public MailTaskService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @Transactional
    public void create(List<User> users) {
        users.stream()
                .map(user -> MailTask.builder()
                        .to(user.getEmail())
                        .text(String.format("Поздравляем , Вы дарите подарок: %s", user.getEmailToBuyGift()))
                        .build())
                .forEach(this::create);
    }

    @Transactional
    public void markAsSend(MailTask task){
        task.setIsSend(true);
        save(task);
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

    public List<MailTask> findAllByIsSendFalse() {
        return mailRepository.findAllByIsSendFalse();
    }
}
