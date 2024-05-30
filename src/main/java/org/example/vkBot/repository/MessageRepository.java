package org.example.vkBot.repository;

import org.example.vkBot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MessageRepository extends JpaRepository<Message, String> {
    Optional<Message> findByContent(String content);
}