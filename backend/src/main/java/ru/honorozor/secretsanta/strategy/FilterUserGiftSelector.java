package ru.honorozor.secretsanta.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.enums.UserGiftType;
import ru.honorozor.secretsanta.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterUserGiftSelector implements UserGiftSelector {

    @Override
    public void setGifts(List<User> users) {
        Collections.shuffle(users);
        final List<User> withPresents = new ArrayList<>();
        for (final User user : users) {
            final String filterString = user.getFilter();

            final List<String> userHasPresent = users.stream()
                    .map(User::getEmailToBuyGift)
                    .collect(Collectors.toList());

            if (filterString != null) {
                final List<String> filter = Arrays.stream(filterString
                        .trim()
                        .replace("[", "")
                        .replace("]", "")
                        .split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
                filter.forEach(System.out::println);

                final Optional<User> to = users.stream()
                        .filter(us -> !filter.contains(us.getEmail()))
                        .filter(us -> !userHasPresent.contains(us.getEmail()))
                        .filter(us -> !us.getName().equals(user.getName()))
                        .findAny();
                System.out.println(to);

                to.ifPresent(value -> {
                    user.setEmailToBuyGift(value.getEmail());
                    withPresents.add(users.stream().filter(us -> us.getEmail().equals(value.getEmail()))
                            .findFirst()
                            .orElseThrow());
                });
            }
        }

        final List<User> mustToPresentWithFilter = users.stream()
                .filter(us -> us.getFilter() != null)
                .collect(Collectors.toList());

        for (User user : mustToPresentWithFilter) {
            final User from = users.stream()
                    .filter(us -> !us.getName().equals(user.getName()))
                    .findFirst()
                    .orElseThrow();
            from.setEmailToBuyGift(user.getEmail());
        }

        final List<User> usersWithEmailGift = users.stream()
                .map(User::getEmailToBuyGift)
                .filter(Objects::nonNull)
                .map(email -> users.stream()
                        .filter(us -> us.getEmail().equals(email))
                        .findFirst()
                        .orElseThrow())
                .collect(Collectors.toList());

        final List<User> whomCanPresent = users.stream()
                .filter(us -> !usersWithEmailGift.contains(us))
                .collect(Collectors.toList());

        final List<User> whoCanPresent = users.stream()
                .filter(us -> us.getEmailToBuyGift() == null)
                .collect(Collectors.toList());

        for (User who : whoCanPresent) {
            final List<User> duplicate = whomCanPresent.stream()
                    .filter(us -> !us.equals(who))
                    .filter(whoCanPresent::contains)
                    .distinct()
                    .collect(Collectors.toList());

            if (!duplicate.isEmpty()) {
                final User whom = duplicate.stream()
                        .findAny()
                        .orElseThrow();
                who.setEmailToBuyGift(whom.getEmail());
                continue;
            }

            final User whom = whomCanPresent.stream()
                    .filter(us -> !us.equals(who))
                    .findFirst()
                    .orElseThrow();

            who.setEmailToBuyGift(whom.getEmail());
            whomCanPresent.remove(whom);
        }
    }

    @Override
    public UserGiftType getUserGiftType() {
        return UserGiftType.FILTER;
    }
}
