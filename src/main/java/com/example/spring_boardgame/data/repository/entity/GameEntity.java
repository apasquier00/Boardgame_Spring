package com.example.spring_boardgame.data.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.*;
@Entity
public class GameEntity {
    @Id
    private UUID id;
    private @NotNull String factoryId;
    private @Positive int boardSize;
    private  String playerIds;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameTokenEntity> tokens;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public String getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(String playerIds) {
        this.playerIds = playerIds;
    }

    public List<GameTokenEntity> getTokens() {
        return tokens;
    }

    public void setTokens(List<GameTokenEntity> tokens) {
        this.tokens = tokens;
    }



}