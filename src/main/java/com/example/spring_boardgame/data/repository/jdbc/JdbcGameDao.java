package com.example.spring_boardgame.data.repository.jdbc;

import com.example.spring_boardgame.data.repository.dao.GameDao;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class JdbcGameDao implements GameDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Game save(Game game) {
        String saveGame = "INSERT INTO game (id, board_size, current_player_id, status, created_at) " +
                "VALUES (:id, :boardSize, :currentPlayerId, :status, :createdAt)";

        String savePlayer = "INSERT INTO player (id) VALUES (:id) ";

        String saveGamePlayer = "INSERT INTO game_player (game_id, player_id, symbol) " +
                "VALUES (:gameId, :playerId, :symbol)";

        Map<String, Object> paramsGame = new HashMap<>();
        paramsGame.put("id", game.getId());
        paramsGame.put("boardSize", game.getBoardSize());
        paramsGame.put("currentTurnPlayerId", game.getCurrentPlayerId());
        paramsGame.put("status", game.getStatus().toString());
        paramsGame.put("createdAt", new Date());
        paramsGame.put("updatedAt", new Date());

        jdbcTemplate.update(saveGame, paramsGame);

        // Sauvegarde des joueurs
        for (UUID playerId : game.getPlayerIds()) {
            Map<String, Object> paramsPlayer = new HashMap<>();
            paramsPlayer.put("id", playerId);
            paramsPlayer.put("name", "Player " + playerId); // Remplace par le vrai nom si disponible
            jdbcTemplate.update(savePlayer, paramsPlayer);
        }

        // Sauvegarde des relations game-player
        int index = 0;
        for (UUID playerId : game.getPlayerIds()) {
            Map<String, Object> paramsGamePlayer = new HashMap<>();
            paramsGamePlayer.put("gameId", game.getId());
            paramsGamePlayer.put("playerId", playerId);
            paramsGamePlayer.put("symbol", (index++ == 0) ? "X" : "O"); // Assigne un symbole
            jdbcTemplate.update(saveGamePlayer, paramsGamePlayer);
        }

        return game;
    }

    public Game getGameById(UUID id) {
        String SELECT_BY_ID = "SELECT * FROM game WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        RowMapper<Game> rowMapper = BeanPropertyRowMapper.newInstance(Game.class);
        return jdbcTemplate.queryForObject(SELECT_BY_ID, params, rowMapper);
    }

    public Stream<Game> getGames() {
        String SELECT_ALL = "SELECT * FROM game";
        Map<String, Object> params = new HashMap<>();
        RowMapper<Game> rowMapper = BeanPropertyRowMapper.newInstance(Game.class);
        return jdbcTemplate.queryForStream(SELECT_ALL, params, rowMapper);
    }

    public Game updateGame(Game game) {
        String updateGame = "UPDATE game SET current_player_id = :currentTurnPlayerId, " +
                "status = :status WHERE id = :id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", game.getId());
        params.put("currentTurnPlayerId", game.getCurrentPlayerId());
        params.put("status", game.getStatus().toString());
        jdbcTemplate.update(updateGame, params);
        return game;
    }
}
