package ru.honorozor.secretsanta.service;

import org.springframework.stereotype.Service;
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

    public void create(List<User> users) {
        for (User user : users) {
            final MailTask mailTask = MailTask.builder()
                    .to(user.getEmail())
                    .text(String.format("Поздравляем , Dы дарите подарок: %s", user.getEmailToBuyGift()))
                    .build();
            create(mailTask);
        }
    }

    public void create(MailTask mailTask) {
        save(mailTask);
    }

    private void save(MailTask mailTask) {
        mailRepository.save(mailTask);
    }

    public List<MailTask> findAll(){
       return mailRepository.findAll();
    }
}
