package ru.honorozor.secretsanta.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoinResponseDTO {
    private UUID uuid;
}
