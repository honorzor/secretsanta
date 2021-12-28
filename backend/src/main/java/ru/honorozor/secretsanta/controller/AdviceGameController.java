package ru.honorozor.secretsanta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@ControllerAdvice
public class AdviceGameController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> htpMessageNotReadableException(HttpServletRequest req, HttpMessageNotReadableException ex) {
        return ResponseEntity.ok(Objects.requireNonNull(ex.getMessage()));
    }
}
