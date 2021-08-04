package ru.honorozor.secretsanta.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Response {
    private boolean isFail = false;
    private String message;

    public static Response fail(String message) {
        return Response.builder()
                .isFail(true)
                .message(message)
                .build();
    }

    public static Response ok(String message) {
        return Response.builder()
                .message(message)
                .build();
    }
}
