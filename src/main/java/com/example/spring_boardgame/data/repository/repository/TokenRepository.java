package com.example.spring_boardgame.data.repository.repository;

import com.example.spring_boardgame.data.repository.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
}
