package ru.honorozor.secretsanta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.service.GameService;

import javax.validation.Valid;

import static ru.honorozor.secretsanta.utils.BindingResultsUtils.convertErrorsToString;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> create(@Valid @RequestBody GameDTO gameDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final String convertErrorsToString = convertErrorsToString(bindingResult);
            return ResponseEntity.badRequest().body(convertErrorsToString);
        }
        gameService.createGame(gameDTO);
        return ResponseEntity.ok(HttpStatus.OK.getReasonPhrase());
    }

}
