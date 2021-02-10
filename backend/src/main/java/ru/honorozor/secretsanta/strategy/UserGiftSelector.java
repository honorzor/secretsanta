package ru.honorozor.secretsanta.strategy;


import ru.honorozor.secretsanta.enums.UserGiftType;
import ru.honorozor.secretsanta.model.User;

import java.util.List;

public interface UserGiftSelector {

    void setGifts(List<User> users);

    UserGiftType getUserGiftType();
}
