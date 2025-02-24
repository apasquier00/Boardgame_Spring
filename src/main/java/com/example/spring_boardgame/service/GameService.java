package com.example.spring_boardgame.service;


import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.UUID;
import java.util.stream.Stream;
import java.util.Set;

public interface GameService
{
    public CellPosition play(UUID playerId, int x, int y, UUID gameId) throws Exception;
    public Game getGameById(UUID gameId);
    public Stream<Game> getGamesByPlayerId(UUID playerId);
    public GameStatus getGameStatus(UUID gameId);
    public Set<CellPosition> getPossibleMoves(UUID gameId);
    public Stream<Game> getGames(String status, UUID playerId);

    }
