package com.example.spring_boardgame.service.catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

public interface GameCatalog {


    Collection<String> getGameIdentifiers();
    GameFactory getGameFactoryById(String gameFactoryId);

}
