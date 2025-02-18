package com.example.spring_boardgame;

public interface PawnService {
    void createPawn();
    String getPawnPossibleMoves(String id);
    int[] movePawn(int row, int col);
    void removePawn(String id);

}
