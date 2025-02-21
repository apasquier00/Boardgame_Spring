package com.example.spring_boardgame.data.repository;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
public class GameEntity {
    @Id
    private @NotNull String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    private int boardSize;
    private int playerNumber;

    public GameEntity() {
    }

}
