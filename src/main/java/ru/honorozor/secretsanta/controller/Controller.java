package ru.honorozor.secretsanta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.service.GameService;
import ru.honorozor.secretsanta.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class Controller {

    private final GameService gameService;
    private final UserService userService;


    @GetMapping(value = "/users/{id}")
    public List<User> users(@PathVariable("id") Long id) {
        return userService.getAllUsersByGameId(id);
    }

    @PostMapping(value = "/users/create")
    public ResponseEntity<String> start(@RequestBody GameDTO gameDTO) {
        gameService.createGame(gameDTO);
        return ResponseEntity.ok("OK");
    }
}
