package ru.honorozor.secretsanta.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

}
