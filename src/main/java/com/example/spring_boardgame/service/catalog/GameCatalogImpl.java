package com.example.spring_boardgame.service.catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class GameCatalogImpl implements GameCatalog {
    GameFactory[] gameFactory = {new TicTacToeGameFactory(), new TaquinGameFactory(), new ConnectFourGameFactory()};

    public Collection<String> getGameIdentifiers() {
        Collection<String> gamesId = new ArrayList<String>();
        for (GameFactory factory : gameFactory) {
            gamesId.add(factory.getGameFactoryId());
        }
        return gamesId;
    }

    public GameFactory getGameFactoryById(String gameFactoryId) {
        return Arrays.stream(gameFactory)
                .filter(gameFactory -> gameFactory.getGameFactoryId().equals(gameFactoryId))
                .findFirst()
                .orElse(null);
    }
}
