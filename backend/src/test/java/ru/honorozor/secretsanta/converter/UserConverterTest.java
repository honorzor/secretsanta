package ru.honorozor.secretsanta.converter;

import org.junit.jupiter.api.Test;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.mapper.UserMapper;
import ru.honorozor.secretsanta.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserConverterTest {
    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    public void shouldConvertToEntity() {
        final String email = "test@mail.ru";
        final String name = "test";

        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .name(name)
                .build();

        final User user = mapper.toEntity(userDTO);

        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
    }

}
