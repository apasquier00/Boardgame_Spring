package com.example.spring_boardgame.data.repository.dao;

import com.example.spring_boardgame.data.repository.entity.GameEntity;
import com.example.spring_boardgame.data.repository.entity.PlayerEntity;
import com.example.spring_boardgame.data.repository.entity.TokenEntity;
import com.example.spring_boardgame.data.repository.repository.GamePlayerRepository;
import com.example.spring_boardgame.data.repository.repository.GameRepository;
import com.example.spring_boardgame.data.repository.repository.PlayerRepository;
import com.example.spring_boardgame.data.repository.repository.TokenRepository;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class JpaGameDao implements GameDao {

    GameFactory TicTacToeGameFactory = new TicTacToeGameFactory();

    @Autowired
    GameRepository gameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    GamePlayerRepository gamePlayerRepository;

    public Game save(Game game) {
        game.save;
    }

    public Game getGameById(UUID id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);    }

    public Stream<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game updateGame(Game game) {
        return null;
    }


    public Game convertEntitytoGame(GameEntity game, List<TokenEntity> tokens, List<PlayerEntity> players) {

        return TicTacToeGameFactory.createGame(game.getId(), game.getBoardSize(), players, tokens );
    }

    public Collection<TokenPosition> getTokenPositionsByGameId(UUID gameId, boolean removed) {
        Collection<TokenPosition> tokens = List.of();
        if (removed) {
            tokens.add()
        }
        return tokens;
    }

    List<PlayerEntity> getPlayerByGameId(){

    }




}
