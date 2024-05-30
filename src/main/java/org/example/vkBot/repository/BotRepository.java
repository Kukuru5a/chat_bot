package org.example.vkBot.repository;

import org.example.vkBot.model.Bot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface BotRepository extends JpaRepository<Bot, Long> {
    Optional<Bot> findById(Long id);
}
