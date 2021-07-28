package ru.honorozor.secretsanta.utils;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class BindingResultsUtils {

    public static String convertErrorsToString(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " поле " + fieldError.getDefaultMessage() + ".\n")
                .collect(Collectors.joining());
    }
}
