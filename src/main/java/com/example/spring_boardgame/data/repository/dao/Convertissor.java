package com.example.spring_boardgame.data.repository.dao;

import com.example.spring_boardgame.data.repository.entity.GameEntity;
import com.example.spring_boardgame.data.repository.entity.GameTokenEntity;
import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Convertissor {
    GameFactory gameFactory;


    public GameEntity GameToGameEntity(Game game) {
        GameEntity gameEntity = new GameEntity();

        // Conversion de l'ID du jeu en String
        gameEntity.setId(game.getId());

        // Stockage du factoryId
        gameEntity.setFactoryId(game.getFactoryId());

        // Stockage de la taille du plateau
        gameEntity.setBoardSize(game.getBoardSize());

        // Conversion de la liste de joueurs UUID en List<String>
        List<String> playerIds = game.getPlayerIds().stream()
                .map(UUID::toString)
                .toList();
        gameEntity.setPlayerIds(playerIds.toString());

        // Conversion des tokens en TokenEntity



        List<GameTokenEntity> boardTokens = game.getBoard().values().stream()
                .map(this::TokentoTokenEntity)
                .toList();
        gameEntity.setTokens(boardTokens);

        return gameEntity;
    }


    GameTokenEntity TokentoTokenEntity(Token token){
        CellPosition position = token.getPosition();
        boolean removed;
        GameTokenEntity tokenEntity = new GameTokenEntity(
                UUID.randomUUID(),
                token.getOwnerId().get(),
                token.getName()
                );
        removed = false;
        if (position != null) {
            tokenEntity.setX(token.getPosition().x());
            tokenEntity.setY(token.getPosition().y());

        }
        tokenEntity.setRemoved(removed);

        return tokenEntity;
    }


    Game GameEntityToGame(GameEntity gameEntity) {
        switch (gameEntity.getFactoryId()) {
            case"tictactoe": gameFactory = new TicTacToeGameFactory(); break;
            case"15 puzzles":gameFactory = new TaquinGameFactory(); break;
            case"connect4":gameFactory = new ConnectFourGameFactory(); break;
        }
        Collection<TokenPosition<UUID>> removedTokens = new ArrayList<>();
        Collection<TokenPosition<UUID>> placedTokens = new ArrayList<>();
        List<GameTokenEntity> tokens = gameEntity.getTokens();
        for (GameTokenEntity token : tokens) {
            if (token.isRemoved()){
                removedTokens.add(convertTokenEntityToPosition(token));
            }
            else {
                placedTokens.add(convertTokenEntityToPosition(token));
            }
        }
        try{
        return gameFactory.createGameWithIds(
                gameEntity.getId(),
                gameEntity.getBoardSize(),
                convertStringToIds(gameEntity.getPlayerIds()),
                placedTokens,
                removedTokens

        );} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    TokenPosition<UUID> convertTokenEntityToPosition(GameTokenEntity tokenEntity){
        Optional<Integer> x = Optional.ofNullable(tokenEntity.getX());
        Optional<Integer> y = Optional.ofNullable(tokenEntity.getY());
        return new TokenPosition(
                tokenEntity.getOwnerId(),
                tokenEntity.getName(),
                x.orElse(-1),
                y.orElse(-1)
        );
    }


    List<UUID>  convertStringToIds(String string){
        return Arrays.stream(string.replace("[", "").replace("]", "").split(", "))
                .map(UUID::fromString) // Convertir chaque élément en UUID
                .collect(Collectors.toList()); // Collecter en une liste
    }

}
