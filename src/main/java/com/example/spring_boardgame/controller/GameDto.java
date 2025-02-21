package com.example.spring_boardgame.controller;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Map;

public record GameDto(String id, String name, int boardSize, int playersNumber, String status) {
}
