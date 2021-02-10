package ru.honorozor.secretsanta.converter;

import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserConverter {

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .wishes(userDTO.getWishes().toString())
                .name(userDTO.getName())
                .filter(userDTO.getFilter() == null ? null : userDTO.getFilter().toString())
                .emailToBuyGift(userDTO.getToBuyGift())
                .build();
    }

    public List<User> toEntities(List<UserDTO> users) {
        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
