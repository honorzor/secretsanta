package ru.honorozor.secretsanta.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "S_MAIL")
public class MailTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mail_id")
    private Long mailId;

    @Column(name = "send_to")
    private String to;

    @Column(name = "text_message")
    private String text;

    @Builder.Default
    @Column(name = "status_mail")
    private Boolean isSend = false;
}
