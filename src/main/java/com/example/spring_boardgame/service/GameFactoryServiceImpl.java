package com.example.spring_boardgame.service;

import com.example.spring_boardgame.data.repository.dao.GameDao;
import com.example.spring_boardgame.data.repository.dao.JpaGameDao;
import com.example.spring_boardgame.service.catalog.GameCatalog;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameFactoryServiceImpl implements GameFactoryService {
    @Qualifier("inMemoryGameDao")
    @Autowired
    GameDao gameDao;
    @Autowired
    GameCatalog catalog;
    public GameFactoryServiceImpl(JpaGameDao jpaGameDao) {
        this.gameDao = jpaGameDao;
    }

    public Game createGame(int boardSize, UUID playerId, String gameName, int playerCount) {
        Set<UUID> users = fillRandomUsers(playerCount, playerId).collect(Collectors.toSet());
        GameFactory factory = catalog.getGameFactoryById(gameName);
        if (factory == null) {
            return null;
        }

        return gameDao.save(factory.createGame(boardSize, users));
    }



    private Stream<UUID> fillRandomUsers(int playerNumber, UUID id){
        Stream<UUID> users = Stream.generate(() -> UUID.randomUUID()).limit(playerNumber - 1);
        return Stream.concat(Stream.of(id), users);
    }
}

