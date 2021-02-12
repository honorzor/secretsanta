package ru.honorozor.secretsanta.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "filter", target = "filter"),
            @Mapping(source = "toBuyGift", target = "emailToBuyGift")
    })
    User userDtoToEntity(UserDTO userDto);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<User> usersDtoToEntities(List<UserDTO> usersInGame);

    default String map(List<String> past) {
        if (past != null) {
            return String.join(",", past);
        }
        return "";
    }
}
