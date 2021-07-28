package ru.honorozor.secretsanta.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class GameDTO {

    @NotEmpty
    @Size(min = 2, max = 10)
    private Set<UserDTO> players;
}
