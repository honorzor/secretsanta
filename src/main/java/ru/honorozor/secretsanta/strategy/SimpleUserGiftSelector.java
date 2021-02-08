package ru.honorozor.secretsanta.strategy;


import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.enums.UserGiftType;
import ru.honorozor.secretsanta.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class SimpleUserGiftSelector implements UserGiftSelector {

    @Override
    public void setGifts(List<User> users) {
        Collections.shuffle(users);
        for (int i = 0; i < users.size(); i++) {
            if (i == users.size() - 1){
                users.get(users.size() - 1).setEmailToBuyGift(users.get(0).getEmail());
                break;
            }
            User user = users.get(i);
            user.setEmailToBuyGift(users.get(i + 1).getEmail());
        }
    }

    @Override
    public UserGiftType getUserGiftType() {
        return UserGiftType.SIMPLE;
    }
}
