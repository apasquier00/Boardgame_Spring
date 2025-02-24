package com.example.spring_boardgame.service;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.UUID;

public interface GameFactoryService {
    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount);

}
