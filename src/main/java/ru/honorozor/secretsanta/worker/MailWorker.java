package ru.honorozor.secretsanta.worker;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.honorozor.secretsanta.model.MailTask;
import ru.honorozor.secretsanta.repository.MailRepository;

import java.util.List;

@Component
public class MailWorker {

    private final MailRepository mailRepository;
    private final JavaMailSenderImpl javaMailSender;

    public MailWorker(MailRepository mailRepository, JavaMailSenderImpl javaMailSender) {
        this.mailRepository = mailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMail() {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        final List<MailTask> presents = mailRepository.findAllByIsSendFalse();

        for (MailTask mailTask : presents) {
            simpleMailMessage.setFrom("ioanntrapeznikov@gmail.com");
            simpleMailMessage.setTo(mailTask.getTo());
            simpleMailMessage.setText(mailTask.getText());
            mailTask.setIsSend(true);
            mailRepository.save(mailTask);
            javaMailSender.send(simpleMailMessage);
        }
    }
}
