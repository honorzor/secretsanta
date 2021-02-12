package ru.honorozor.secretsanta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.service.GameService;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping(value = "/create")
    @CrossOrigin
    public ResponseEntity<String> create(@RequestBody GameDTO gameDTO) {
        gameService.createGame(gameDTO);
        return ResponseEntity.ok("OK");
    }
}
