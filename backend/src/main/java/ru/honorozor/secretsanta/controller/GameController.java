package ru.honorozor.secretsanta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.dto.Response;
import ru.honorozor.secretsanta.service.GameService;

import javax.validation.Valid;

import static ru.honorozor.secretsanta.utils.BindingResultsUtils.convertErrorsToString;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = {"http://localhost:8080", "http://127.0.0.1"})
    public Response create(@Valid @RequestBody GameDTO gameDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final String convertErrorsToString = convertErrorsToString(bindingResult);
            return Response.fail(convertErrorsToString);
        }
        gameService.createGame(gameDTO);
        return Response.ok("OK");
    }

}
