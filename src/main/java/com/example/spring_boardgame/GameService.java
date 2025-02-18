package com.example.spring_boardgame;

public interface GameService {

    void createGame(int i, int j);
    String getGameState();
    void startGame();
    void endGame();
}
