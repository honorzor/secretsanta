package ru.honorozor.secretsanta.strategy;

import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.enums.UserGiftType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GiftStrategySelector {
    private final Map<UserGiftType, UserGiftSelector> strategies;

    public GiftStrategySelector(List<UserGiftSelector> userGiftSelector) {
        this.strategies = userGiftSelector.stream().collect(Collectors.toMap(
                UserGiftSelector::getUserGiftType,
                selector -> selector
        ));
    }

    public UserGiftSelector getUserGiftSelector(boolean isFilter) {
        return isFilter ? strategies.get(UserGiftType.FILTER) : strategies.get(UserGiftType.SIMPLE);
    }
}
