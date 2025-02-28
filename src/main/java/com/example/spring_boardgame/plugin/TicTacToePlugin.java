package com.example.spring_boardgame.plugin;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class TicTacToePlugin implements GamePlugin {
    private final MessageSource messageSource;

    @Value("${game.tictactoe.default-board-size}") // Valeur par défaut = 2
    private int defaultBoardSize;
    @Value("${game.tictactoe.default-player-count}") // Valeur par défaut = 2
    private int defaultPlayerCount;

    private TicTacToeGameFactory gameFactory;
    @Autowired
    public TicTacToePlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getName(Locale language) {
        return messageSource.getMessage("tictactoe.name", null, "Nom introuvable", language);
    }

    @Override
    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount) {

        return gameFactory.createGame(defaultPlayerCount, defaultBoardSize)
        ;
    }
}
