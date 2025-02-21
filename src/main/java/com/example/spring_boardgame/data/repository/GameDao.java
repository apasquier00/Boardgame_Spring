package com.example.spring_boardgame.data.repository;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

public interface GameDao {
    Game save(Game game);

    Game getGameById(UUID id);
    Stream<Game> getGames();
    Game updateGame(Game game);

}
