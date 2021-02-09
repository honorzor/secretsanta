package ru.honorozor.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.honorozor.secretsanta.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> { }
