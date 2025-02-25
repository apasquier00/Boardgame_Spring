package com.example.spring_boardgame.data.repository.dao;

import com.example.spring_boardgame.data.repository.entity.GameEntity;
import com.example.spring_boardgame.data.repository.repository.GameRepository;
import com.example.spring_boardgame.data.repository.repository.TokenRepository;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@Repository
public class JpaGameDao implements GameDao {
    Convertissor convertissor = new Convertissor();

    @Autowired
    GameRepository gameRepository;
    @Autowired
    TokenRepository tokenRepository;

    public Game upsert(Game game) {
        gameRepository.save(convertissor.GameToGameEntity(game));
        return game;
    }

    public Game getGameById(UUID id) throws Exception {
        Optional<GameEntity> game = gameRepository.findById(id);
        if (game.isPresent()) {
                return convertissor.GameEntityToGame(game.get());
        }
        else {throw new Exception("Game not found");}

    }



    public Stream<Game> getGames() {

        return gameRepository.findAll().stream()
                .map(convertissor::GameEntityToGame);    }

    public Game updateGame(Game game) {
        return null;
    }




}
