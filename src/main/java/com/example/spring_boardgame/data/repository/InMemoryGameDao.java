package com.example.spring_boardgame.data.repository;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class InMemoryGameDao implements GameDao {
    Map<UUID, Game> games = new HashMap<>();

    public Game save(Game game) {
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
