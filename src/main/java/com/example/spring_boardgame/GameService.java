package com.example.spring_boardgame;

public interface GameService
{
    void createGame(int i, int j, String UserId, String opponentId);
    void startGame(GameEntity gameEntity);
    void endGame(GameEntity gameEntity);
}
