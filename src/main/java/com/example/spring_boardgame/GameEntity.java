package com.example.spring_boardgame;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.*;

public class GameEntity implements Game {
    @Override
    public @NotNull UUID getId() {
        return null;
    }

    @Override
    public @NotBlank String getFactoryId() {
        return "";
    }

    @Override
    public @NotEmpty Set<UUID> getPlayerIds() {
        return Set.of();
    }

    @Override
    public @NotNull GameStatus getStatus() {
        return null;
    }

    @Override
    public UUID getCurrentPlayerId() {
        return null;
    }

    @Override
    public @Min(2L) int getBoardSize() {
        return 0;
    }

    @Override
    public @NotNull Map<CellPosition, Token> getBoard() {
        return Map.of();
    }

    @Override
    public @NotNull Collection<Token> getRemainingTokens() {
        return List.of();
    }

    @Override
    public @NotNull Collection<Token> getRemovedTokens() {
        return List.of();
    }
}
