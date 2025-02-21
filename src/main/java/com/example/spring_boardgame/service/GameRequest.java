package com.example.spring_boardgame.service;

import java.util.UUID;
import java.util.stream.Stream;

public class GameRequest {


    UUID id;
    int boardSize;
    int playerCount;
    Stream<UUID> playersId;


    public GameRequest(UUID id, int boardSize, Stream<UUID> playersId) {
        this.id = id;
        this.boardSize = boardSize;
        this.playersId = playersId;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getPlayerCount() {
        return (int) playersId.count();
    }



}
