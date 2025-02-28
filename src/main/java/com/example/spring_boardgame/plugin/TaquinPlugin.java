package com.example.spring_boardgame.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

@Component
public class TaquinPlugin implements GamePlugin {
    private final MessageSource messageSource;
    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;
    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;


    public TaquinPlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getName(Locale language) {
        return messageSource.getMessage("taquin.name", null, "Nom introuvable", language);
    }

    @Override
    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount) {
        return null;
    }
}
