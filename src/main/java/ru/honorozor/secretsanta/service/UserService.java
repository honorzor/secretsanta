package ru.honorozor.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.honorozor.secretsanta.model.User;
import ru.honorozor.secretsanta.repository.GameRepository;
import ru.honorozor.secretsanta.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public List<User> getAllUsersByGameId(Long id) {
        return userRepository.findAllUsersByGameId(id);

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
