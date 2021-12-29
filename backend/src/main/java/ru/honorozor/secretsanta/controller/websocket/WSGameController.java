package ru.honorozor.secretsanta.controller.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.dto.JoinRequestDTO;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.service.GameService;

@Controller
@RequiredArgsConstructor
public class WSGameController {
    private final GameService gameService;

    @MessageMapping("/create-game")
    @SendTo("/topic/game-info")
    public GameDTO createGame(final UserDTO creator) {
        return gameService.start(creator);
    }

    @MessageMapping("/join-game")
    @SendTo("/topic/game-info")
    public GameDTO joinGame(final JoinRequestDTO joinRequestDTO) {
        return gameService.joinGame(joinRequestDTO);
    }

    @MessageMapping("/start")
    @SendTo("/topic/game-info")
    public GameDTO start(final GameDTO gameDTO) {
        gameService.start(gameDTO);
        return GameDTO.builder().isCreated(true).build();
    }

}
