package ru.honorozor.secretsanta.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties("mail.smtp")
@Setter
@Getter
@NoArgsConstructor
public class EmailSetting {
    private String auth;
    private String starttlsEnable;
    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
}
