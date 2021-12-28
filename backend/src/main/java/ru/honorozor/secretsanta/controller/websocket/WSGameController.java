package ru.honorozor.secretsanta.controller.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.honorozor.secretsanta.dto.JoinDTO;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.service.GameService;

@Controller
@RequiredArgsConstructor
public class WSGameController {
    private final GameService gameService;

    @MessageMapping("/create-game")
    @SendTo("/topic/game-info")
    public String createGame(final UserDTO creator) {
        gameService.createEmptyGame(creator);
        return "Игрок создал новую игру:" + gameService.createEmptyGame(creator);
    }

    @MessageMapping("/join-game")
    @SendTo("/topic/game-info")
    public String joinGame(final JoinDTO joinDTO) {
        return gameService.joinGame(joinDTO);
    }

}
