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
@Table(name = "S_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email" , length = 50)
    private String email;

    @Column(name = "isCreator")
    private Boolean isCreator = false;

    @Column(name = "emailToBuyGift")
    private String emailToBuyGift;

    @Column(name = "filter")
    private String filter;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

}
