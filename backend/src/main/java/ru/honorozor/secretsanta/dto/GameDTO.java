package ru.honorozor.secretsanta.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameDTO {

    @NotEmpty
    @Size(min = 2, max = 10)
    @Valid
    private Set<UserDTO> players;

    private UUID uuid;
}
