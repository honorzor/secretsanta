package ru.honorozor.secretsanta.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private List<UserDTO> users;
    private boolean filter;
}
