package ru.honorozor.secretsanta.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.model.Game;
import ru.honorozor.secretsanta.model.User;

import java.util.List;

@Service
public class MailService {

    private final JavaMailSenderImpl javaMailSender;

    public MailService(@Qualifier("getJavaMailSenderImpl") JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
        }

    public void send(Game game) {
        final List<User> users = game.getUsers();

        for (User user : users) {
            send(user.getEmail(), user.getEmailToBuyGift());
        }
    }

    public void send(String to, String text) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ioanntrapeznikov@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
