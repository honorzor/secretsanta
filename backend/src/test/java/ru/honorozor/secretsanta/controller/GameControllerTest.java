package ru.honorozor.secretsanta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.honorozor.secretsanta.container.TestWithMysqlContainer;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.dto.UserDTO;

import java.net.URI;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
@ExtendWith(value = SpringExtension.class)
class GameControllerTest extends TestWithMysqlContainer {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateGame() throws Exception {
        final GameDTO gameDTO = GameDTO.builder()
                .players(Set.of(
                        UserDTO.builder()
                                .name("test")
                                .email("test@mail.ru")
                                .build(),
                        UserDTO.builder()
                                .name("test2")
                                .email("test2@mail.ru")
                                .build()
                ))
                .build();

        final String content = objectMapper.writeValueAsString(gameDTO);

        mockMvc.perform(post(URI.create("/game/create"))
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
