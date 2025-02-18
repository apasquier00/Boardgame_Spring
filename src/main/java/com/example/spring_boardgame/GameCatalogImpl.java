package com.example.spring_boardgame;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogImpl implements GameCatalog {
TicTacToeGameFactory[] gameFactory = {new TicTacToeGameFactory()};

    public Collection<String> getGameIdentifiers() {
        Collection<String> gamesId = new ArrayList<String>();
        for (TicTacToeGameFactory factory : gameFactory) {
            gamesId.add(factory.getGameFactoryId());
        }
        return gamesId;
    }
}
