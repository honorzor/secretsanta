package ru.honorozor.secretsanta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailSenderConfiguration {

    private final MailConfiguration mailConfiguration;

    public JavaMailSenderConfiguration(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailConfiguration.getHost());
        javaMailSender.setPort(mailConfiguration.getPort());
        javaMailSender.setProtocol(mailConfiguration.getProtocol());
        javaMailSender.setUsername(mailConfiguration.getUsername());
        javaMailSender.setPassword(mailConfiguration.getPassword());
        javaMailSender.setJavaMailProperties(getProps());
        return javaMailSender;
    }

    private Properties getProps() {
        Properties mailProp = new Properties();
        mailProp.setProperty("mail.smtp.auth", mailConfiguration.getAuth());
        mailProp.setProperty("mail.smtp.starttls.enable", mailConfiguration.getStarttlsEnable());
        return mailProp;
    }
}
