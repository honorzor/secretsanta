package ru.honorozor.secretsanta.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinDTO {
    private String uuid;
    private UserDTO userDTO;
}
