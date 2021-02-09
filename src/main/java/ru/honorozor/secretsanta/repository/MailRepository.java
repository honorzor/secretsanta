package ru.honorozor.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.honorozor.secretsanta.model.MailTask;

import java.util.List;

public interface MailRepository extends JpaRepository<MailTask, Long> {

    List<MailTask> findAllByIsSendFalse();
}
