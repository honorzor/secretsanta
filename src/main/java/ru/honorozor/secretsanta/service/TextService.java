package ru.honorozor.secretsanta.service;

import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.model.User;

import java.util.List;

import static java.lang.String.*;

@Service
public class TextService {

    public String createText(User user, List<User> users) {
        final User toBuyUser = users.stream()
                .filter(us -> us.getEmail().equals(user.getEmailToBuyGift()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("cannot find user"));

        final StringBuilder text = new StringBuilder(
                format("Поздравляем , Вы дарите подарок: %s .", user.getEmailToBuyGift())
        );

        if (toBuyUser.getWishes() != null && !toBuyUser.getWishes().isBlank()){
            text.append(format("У человека, которому Вы дарите есть пожелания %s", toBuyUser.getWishes()));
        }

        return text.toString();
    }
}
