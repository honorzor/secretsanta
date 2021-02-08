package ru.honorozor.secretsanta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl (){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setProtocol("smtp");
        javaMailSender.setUsername("mailbox");
        javaMailSender.setPassword("password");

        Properties javaMailProperties = javaMailSender.getJavaMailProperties();
        javaMailProperties.setProperty("mail.smtp.auth","true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable","true");
        return javaMailSender;
    }
}
