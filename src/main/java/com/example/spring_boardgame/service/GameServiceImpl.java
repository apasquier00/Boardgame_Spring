package com.example.spring_boardgame.service;

import com.example.spring_boardgame.data.repository.dao.GameDao;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {
    @Qualifier("inMemoryGameDao")
    @Autowired
    GameDao gameDao;


    public CellPosition play(UUID playerId, int x, int y, UUID gameId) throws Exception {
        if(!isCurrentPlayer(gameDao.getGameById(gameId), playerId)) {
            throw new Exception("Cet Utilisateur n'a pas les droits");
        }
        CellPosition cellPosition = new CellPosition(x, y);
        Game game = gameDao.getGameById(gameId);
        Token token = getCurrentPlayerToken(gameId);


        token.moveTo(cellPosition);
        gameDao.updateGame(game);
        return (cellPosition);
    }

    public Stream<Game> getGames(String status, UUID playerId) {
        Stream <Game> games = gameDao.getGames();
        games = filterByPlayerId(playerId, games);
        games = filterByStatus(status, games);
        return games;
    }

    public Game getGameById(UUID gameId) {
        return gameDao.getGameById(gameId);
    }

    public Stream<Game> getGamesByPlayerId(UUID playerId) {
        return gameDao.getGames();
    }

    public GameStatus getGameStatus(UUID gameId) {
        return gameDao.getGameById(gameId).getStatus();
    }

    public Set<CellPosition> getPossibleMoves(UUID gameId) {
        return  getCurrentPlayerToken(gameId).getAllowedMoves();
    }


    private Stream<Game> filterByPlayerId(UUID playerId, Stream<Game> games) {
        if (playerId == null) return games; // Aucun filtrage si playerId est null
        return games.filter(game -> hasPlayer(game, playerId));
    }

    private Stream<Game> filterByStatus(String status, Stream<Game> games) {
        if (status == null) return games;
        return games.filter(game -> status.equals(game.getStatus().name()));
    }


    boolean hasPlayer(Game game, UUID playerId) {
        Set<UUID> players = game.getPlayerIds();
        return players.contains(playerId);
    }

    boolean isCurrentPlayer(Game game, UUID playerId) {
        return game.getCurrentPlayerId().equals(playerId);
    }


    public Token getCurrentPlayerToken(UUID gameId){
        Game game =  gameDao.getGameById(gameId);
        UUID currentPlayerId = game.getCurrentPlayerId();

        // Extract and compare UUID
        // Get the first matching token
        // Return null if no match is found
        return game.getRemainingTokens().stream()
                .filter(token -> token.getOwnerId().isPresent() && token.getOwnerId().get().equals(currentPlayerId)) // Extract and compare UUID
                .findFirst() // Get the first matching token
                .orElse(null);
    }

}
