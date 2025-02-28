package com.example.spring_boardgame.plugin;

import com.sun.source.util.Plugin;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class Connect4Plugin implements GamePlugin {

    private final MessageSource messageSource;
    @Value("${game.connect4.default-board-size}") // Valeur par défaut = 2
    private int defaultBoardSize;
    @Value("${game.connect4.default-player-count}") // Valeur par défaut = 2
    private int defaultPlayerCount;

    public Connect4Plugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getName(Locale language) {
        return messageSource.getMessage("connect4.name", null, "Nom introuvable", language);
    }

    @Override
    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount) {
        return null;
    }
}
