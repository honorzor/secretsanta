package ru.honorozor.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.converter.UserConverter;
import ru.honorozor.secretsanta.dto.GameDTO;
import ru.honorozor.secretsanta.dto.UserDTO;
import ru.honorozor.secretsanta.model.Game;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.repository.GameRepository;
import ru.honorozor.secretsanta.strategy.GiftStrategySelector;
import ru.honorozor.secretsanta.strategy.UserGiftSelector;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GiftStrategySelector giftStrategySelector;
    private final MailTaskService mailTaskService;
    private final UserConverter userConverter;

    public void createGame(GameDTO gameDTO) {
        final List<UserDTO> players = gameDTO.getUsers();

        final List<User> users = userConverter.toEntities(players);

        final UserGiftSelector userGiftSelector = giftStrategySelector.getUserGiftSelector(gameDTO.isFilter());

        userGiftSelector.setGifts(users);

        final Game game = Game.builder()
                .users(users)
                .build();

        users.forEach(user -> user.setGame(game));
        gameRepository.save(game);
        mailTaskService.create(users);
    }
}
