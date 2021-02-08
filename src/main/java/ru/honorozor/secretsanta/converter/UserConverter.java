package ru.honorozor.secretsanta.converter;

import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toEntity(UserDTO userDTO){
        return User.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .filter(userDTO.getFilter() == null ? null : userDTO.getFilter().toString())
                .build();
    }

    public static List<User> toEntities(List<UserDTO> users){
        return users.stream()
                .map(UserConverter::toEntity)
                .collect(Collectors.toList());
    }
}
