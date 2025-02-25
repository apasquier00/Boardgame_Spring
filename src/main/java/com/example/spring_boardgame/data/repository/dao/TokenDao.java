package com.example.spring_boardgame.data.repository.dao;

import com.example.spring_boardgame.data.repository.repository.TokenRepository;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.UUID;
import java.util.stream.Stream;

public interface TokenDao {

    Stream<Token> saveTokens(Stream<Token> tokens, UUID gameId);
    Stream<Token> getTokens(UUID gameId);
}
