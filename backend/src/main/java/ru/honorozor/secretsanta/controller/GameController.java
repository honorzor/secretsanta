package ru.honorozor.secretsanta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.service.GameService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController extends AdviceGameController {

    private final GameService gameService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1"})
    public ResponseEntity<String> create(@Valid @RequestBody GameDTO gameDTO) {
        gameService.createGame(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
