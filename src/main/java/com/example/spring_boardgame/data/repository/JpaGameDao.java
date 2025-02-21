package com.example.spring_boardgame.data.repository;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class JpaGameDao implements GameDao {

    @Autowired
    GameRepository gameRepository;

    public Game save(Game game) {
        return null;
    }

    public Game getGameById(UUID id) {
        return null;
    }

    public Stream<Game> getGames() {
        return Stream.empty();
    }

    public Game updateGame(Game game) {
        return null;
    }

//
//    public GameEntity convertGameToEntity(Game game) {
//        GameEntity gameEntity = new GameEntity();
//        gameEntity.setId(game.getId().toString());
//        gameEntity.setName(game.getFactoryId());
//        gameEntity.setBoardSize(game.getBoardSize());
//        gameEntity.setPlayerNumber(game.getPlayerIds().size());
//        return gameEntity;
//    }
}
