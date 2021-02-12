package ru.honorozor.secretsanta.converter;

import org.junit.jupiter.api.Test;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.mapper.UserMapper;
import ru.honorozor.secretsanta.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserConverterTest {
    private final UserMapper mapper = UserMapper.INSTANCE;

    @Test
    public void shouldConvertToEntity() {
        final String email = "test@mail.ru";
        final String filter1 = "filter@mai.ru";
        final String filter2 = "filter2@mail.ru";
        final String name = "test";
        final String toBuyGift = "test2@mail.ru";
        final long id = 1L;

        final UserDTO userDTO = UserDTO.builder()
                .email(email)
                .id(id)
                .filter(List.of(filter1, filter2))
                .name(name)
                .toBuyGift(toBuyGift)
                .build();

        final User user = mapper.toEntity(userDTO);

        assertEquals(email, user.getEmail());
        assertEquals(filter1 + "," + filter2, user.getFilter());
        assertEquals(name, user.getName());
        assertEquals(toBuyGift, user.getEmailToBuyGift());
        assertEquals(id, user.getId());
    }

}
