package ru.honorozor.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.dto.JoinRequestDTO;
import ru.honorozor.secretsanta.dto.JoinResponseDTO;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.enums.UserGiftType;
import ru.honorozor.secretsanta.mapper.UserMapper;
import ru.honorozor.secretsanta.model.Game;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.repository.GameRepository;
import ru.honorozor.secretsanta.strategy.GiftStrategySelector;
import ru.honorozor.secretsanta.strategy.UserGiftSelector;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final GiftStrategySelector giftStrategySelector;
    private final MailTaskService mailTaskService;
    private final Map<UUID, List<UserDTO>> games = new HashMap<>();

    public GameDTO joinGame(final JoinRequestDTO joinRequestDTO) {
        final UUID uuid = UUID.fromString(joinRequestDTO.getUuid());
        final List<UserDTO> usersInGame = games.get(uuid);
        usersInGame.add(joinRequestDTO.getUser());

        return GameDTO.builder()
                .uuid(uuid)
                .players(new HashSet<>(usersInGame))
                .build();
    }

    public GameDTO start(final UserDTO creator) {
        creator.setCreator(true);
        final UUID uuid = getRandomLink();
        games.put(uuid, new ArrayList<>() {{
            add(creator);
        }});

        return GameDTO.builder()
                .uuid(uuid)
                .players(new HashSet<>() {{
                    add(creator);
                }})
                .build();
    }

    private UUID getRandomLink() {
        return UUID.randomUUID();
    }


    public void start(GameDTO gameDTO) {
        final List<User> users = UserMapper.INSTANCE.toEntities(gameDTO.getPlayers());
        final UserGiftSelector userGiftSelector = giftStrategySelector.getUserGiftSelector(UserGiftType.SIMPLE);
        userGiftSelector.setGifts(users);
        final Game game = Game.builder()
                .users(users)
                .name(UUID.randomUUID().toString())
                .build();
        users.forEach(user -> user.setGame(game));
        gameRepository.save(game);
        mailTaskService.create(users);
    }
}
