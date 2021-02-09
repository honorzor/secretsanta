package ru.honorozor.secretsanta.strategy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.honorozor.secretsanta.model.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SimpleUserGiftSelectorTest {

    private final UserGiftSelector filterUserGiftSelector = new SimpleUserGiftSelector();

    @Test
    public void shouldSendPrizesToAll() {
        final List<User> users = IntStream.range(0, new Random().nextInt(1_000_000))
                .mapToObj(i -> User.builder()
                        .name(String.valueOf(i))
                        .email(i + "@test.ru")
                        .build())
                .collect(Collectors.toList());

        filterUserGiftSelector.setGifts(users);

        allUsersHaveRecipients(users);
        recipientsNotRepeat(users);

    }

    private void allUsersHaveRecipients(List<User> users) {
        users.forEach(
                user -> Assertions.assertNotNull(user.getEmailToBuyGift())
        );
    }

    private void recipientsNotRepeat(List<User> users) {
        final List<String> allGifts = users.stream()
                .map(User::getEmailToBuyGift)
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertEquals(users.size(), allGifts.size());
    }

}
