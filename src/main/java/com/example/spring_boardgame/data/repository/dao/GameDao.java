package com.example.spring_boardgame.data.repository.dao;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.UUID;
import java.util.stream.Stream;

public interface GameDao {
    Game upsert(Game game);

    Game getGameById(UUID id) throws Exception;


    Stream<Game> getGames();
    Game updateGame(Game game);

}
