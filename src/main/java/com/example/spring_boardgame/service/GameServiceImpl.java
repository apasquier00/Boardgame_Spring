package com.example.spring_boardgame.service;

import com.example.spring_boardgame.data.repository.dao.Convertor;
import com.example.spring_boardgame.data.repository.dao.GameDao;
import com.example.spring_boardgame.data.repository.entity.GameEntity;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class GameServiceImpl implements GameService {

    @Qualifier("jpaGameDao")
    @Autowired
    GameDao gameDao;
    @Autowired
    private Convertor convertor;


    public CellPosition play(UUID playerId, int x, int y, UUID gameId) throws Exception {
        if(!isCurrentPlayer(getGameById(gameId), playerId)) {
            throw new Exception("Cet Utilisateur n'a pas les droits");
        }
        CellPosition cellPosition = new CellPosition(x, y);
        Game game = getGameById(gameId);
        if (game.getFactoryId().equals("15 puzzle")){
            Collection<Token> tokens = game.getBoard().values(); // Récupère tous les tokens
            int i = 0;

            for (Token token : tokens) {
                if (cellPosition.equals(token.getPosition())){
                    try {
                        CellPosition targetPosition = token.getAllowedMoves().stream().findFirst().orElse(null);
                        if (targetPosition != null) {
                            token.moveTo(targetPosition);
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }

        }else {
            // Déplacer le jeton
            getCurrentPlayerToken(game).moveTo(cellPosition);
            // Mettre à jour la BDD

        }
        gameDao.upsert(game);
        printGame(game);
        return (cellPosition);
    }

    public Stream<Game> getGames(String status, UUID playerId) {
        Stream <Game> games = gameDao.getGames();
        games = filterByPlayerId(playerId, games);
        games = filterByStatus(status, games);
        return games;
    }

    public Game getGameById(UUID gameId) {
        try{
            return gameDao.getGameById(gameId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Stream<Game> getGamesByPlayerId(UUID playerId) {
        return gameDao.getGames();
    }

    public GameStatus getGameStatus(UUID gameId) {
        return getGameById(gameId).getStatus();
    }

    public Set<CellPosition> getPossibleMoves(UUID gameId) {
        Game game = getGameById(gameId);
        if (game.getFactoryId().equals("15 puzzle")){
            Collection<Token> tokens = game.getBoard().values(); // Récupère tous les tokens
            System.out.println(tokens);

            Set<CellPosition> playableTokenPositions = new HashSet<>();

            for (Token token : tokens) {
                if (!token.getAllowedMoves().isEmpty()) { // Vérifie si le token peut jouer
                    playableTokenPositions.add(token.getPosition()); // Ajoute sa position
                }
            }

            return playableTokenPositions;



        }
        else return  getCurrentPlayerToken(getGameById(gameId)).getAllowedMoves();
    }


    private Stream<Game> filterByPlayerId(UUID playerId, Stream<Game> games) {
        if (playerId == null) return games; // Aucun filtrage si playerId est null
        return games.filter(game -> hasPlayer(game, playerId));
    }

    private Stream<Game> filterByStatus(String status, Stream<Game> games) {
        if (status == null) return games;
        return games.filter(game -> status.equalsIgnoreCase(game.getStatus().name()));
    }




    boolean hasPlayer(Game game, UUID playerId) {
        Set<UUID> players = game.getPlayerIds();
        return players.contains(playerId);
    }

    boolean isCurrentPlayer(Game game, UUID playerId) {
        return game.getCurrentPlayerId().equals(playerId);
    }



    public Token getCurrentPlayerToken(Game game){
        UUID currentPlayerId = game.getCurrentPlayerId();
        System.out.println(currentPlayerId);
        return game.getRemainingTokens().stream()
                .filter(token -> token.getOwnerId().isPresent() && token.getOwnerId().get().equals(currentPlayerId)) // Extract and compare UUID
                .findFirst() // Get the first matching token
                .orElse(null);
    }

    void printGame(Game game) {
        GameEntity gameEntity = convertor.GameToGameEntity(game);

        System.out.println(convertor.convertToBoardString(gameEntity));

    }

}
