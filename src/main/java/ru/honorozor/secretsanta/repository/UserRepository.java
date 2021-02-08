package ru.honorozor.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.honorozor.secretsanta.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

    @Query(nativeQuery = true ,value = "select * from s_user where game_id = :gameId")
    List<User> findAllUsersByGameId(@Param("gameId") Long gameId);
}
