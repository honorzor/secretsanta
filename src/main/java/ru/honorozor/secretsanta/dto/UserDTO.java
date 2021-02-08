package ru.honorozor.secretsanta.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;

    private String email;

    private Boolean isCreator = false;

    private String toBuyGift;

    private List<String> filter;

}
