package ru.honorozor.secretsanta.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "game", ignore = true)
    })
    User toEntity(UserDTO user);

    default List<User> toEntities(List<UserDTO> users) {
        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
