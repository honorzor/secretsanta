package ru.honorozor.secretsanta.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NonNull
    private String name;

    @NonNull
    private String email;

}
