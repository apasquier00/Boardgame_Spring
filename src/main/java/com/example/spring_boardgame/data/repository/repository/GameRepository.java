package com.example.spring_boardgame.data.repository.repository;

import com.example.spring_boardgame.data.repository.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID>
{

    Object getGameEntitiesById(UUID id);
}
