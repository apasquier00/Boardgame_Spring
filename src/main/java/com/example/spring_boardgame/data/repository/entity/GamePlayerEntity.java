package com.example.spring_boardgame.data.repository.entity;

import fr.le_campus_numerique.square_games.engine.Game;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class GamePlayerEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    private String symbol;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
