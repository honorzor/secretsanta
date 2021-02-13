package ru.honorozor.secretsanta.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.honorozor.secretsanta.config.MailConfiguration;
import ru.honorozor.secretsanta.service.MailTaskService;

import static org.springframework.data.util.Pair.of;

@Component
@RequiredArgsConstructor
public class MailWorker {

    private final JavaMailSenderImpl javaMailSender;
    private final MailTaskService mailTaskService;
    private final MailConfiguration mailConfiguration;

    @Scheduled(fixedDelay = 5000)
    public void sendMail() {
        mailTaskService.findAllByIsSendFalse()
                .stream()
                .map(task -> {
                    final SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setTo(task.getTo());
                    mailMessage.setFrom(mailConfiguration.getDefaultSender());
                    mailMessage.setText(task.getText());
                    return of(task, mailMessage);
                })
                .forEach(pair -> {
                    javaMailSender.send(pair.getSecond());
                    mailTaskService.markAsSend(pair.getFirst());
                });
    }
}
