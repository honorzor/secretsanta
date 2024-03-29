package ru.honorozor.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.enums.UserGiftType;
import ru.honorozor.secretsanta.mapper.UserMapper;
import ru.honorozor.secretsanta.model.Game;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.repository.GameRepository;
import ru.honorozor.secretsanta.strategy.GiftStrategySelector;
import ru.honorozor.secretsanta.strategy.UserGiftSelector;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GiftStrategySelector giftStrategySelector;
    private final MailTaskService mailTaskService;

    public void createGame(GameDTO gameDTO) {
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
