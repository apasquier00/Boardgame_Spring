package com.example.spring_boardgame.service;

import com.example.spring_boardgame.data.repository.GameEntity;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public interface GameFactoryService {
    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount);

}
