package com.example.spring_boardgame.plugin;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;
import java.util.UUID;

public interface GamePlugin {

    String getName(Locale language);
    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount);
}
