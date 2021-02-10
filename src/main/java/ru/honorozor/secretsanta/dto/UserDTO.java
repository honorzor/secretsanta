package ru.honorozor.secretsanta.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String toBuyGift;

    private List<String> filter;

    private List<String> wishes;
}
