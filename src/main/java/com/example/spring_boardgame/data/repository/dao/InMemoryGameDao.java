package com.example.spring_boardgame.data.repository.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Repository
public class InMemoryGameDao implements GameDao {
    Map<UUID, Game> games = new HashMap<>();

    public Game upsert(Game game) {
        games.put(game.getId(), game);
        return game;
    }

    public Game getGameById(UUID id) {
        return games.get(id);
    }

    public Stream<Game> getGames() {
        return games.values().stream();
    }

    public Game updateGame(Game game) {
        games.put(game.getId(), game);
        return game;
    }


}
