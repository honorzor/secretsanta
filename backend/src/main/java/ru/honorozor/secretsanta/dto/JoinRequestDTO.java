package ru.honorozor.secretsanta.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinRequestDTO {
    private String uuid;
    private UserDTO user;
}
