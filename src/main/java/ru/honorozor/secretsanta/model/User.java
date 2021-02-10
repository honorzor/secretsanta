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
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "emailToBuyGift")
    private String emailToBuyGift;

    @Column(name = "filter")
    private String filter;

    @Column(name = "wishes")
    private String wishes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

}
