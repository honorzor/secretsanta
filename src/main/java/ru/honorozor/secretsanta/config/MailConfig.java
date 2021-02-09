package ru.honorozor.secretsanta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ru.honorozor.secretsanta.settings.EmailSetting;

import java.util.Properties;

@Configuration
public class MailConfig {

    private final EmailSetting emailSetting;

    public MailConfig(EmailSetting emailSetting) {
        this.emailSetting = emailSetting;
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailSetting.getHost());
        javaMailSender.setPort(emailSetting.getPort());
        javaMailSender.setProtocol(emailSetting.getProtocol());
        javaMailSender.setUsername(emailSetting.getUsername());
        javaMailSender.setPassword(emailSetting.getPassword());
        javaMailSender.setJavaMailProperties(getProps());
        return javaMailSender;
    }

    private Properties getProps() {
        Properties mailProp = new Properties();
        mailProp.setProperty("mail.smtp.auth", emailSetting.getAuth());
        mailProp.setProperty("mail.smtp.starttls.enable", emailSetting.getStarttlsEnable());
        return mailProp;
    }
}
