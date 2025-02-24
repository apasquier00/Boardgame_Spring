package com.example.spring_boardgame.data.repository.repository;

import com.example.spring_boardgame.data.repository.entity.GamePlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayerEntity, UUID> {
}
