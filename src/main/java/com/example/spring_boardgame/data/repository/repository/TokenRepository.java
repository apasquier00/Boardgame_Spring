package com.example.spring_boardgame.data.repository.repository;

import com.example.spring_boardgame.data.repository.entity.GameTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<GameTokenEntity, Long> {
}
