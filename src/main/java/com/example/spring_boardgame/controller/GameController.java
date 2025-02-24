package com.example.spring_boardgame.controller;

import com.example.spring_boardgame.service.GameFactoryServiceImpl;
import com.example.spring_boardgame.service.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.core.ParameterizedTypeReference;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.List;


@RestController
public class GameController {

    RestClient restClient = RestClient.builder().build();

    @Autowired
    private GameFactoryServiceImpl gameFactoryService;
    @Autowired
    private GameServiceImpl gameService;


    @PostMapping("/games")
    public GameDto createGame(
                       @RequestHeader("X-UserId") UUID playerId, @RequestBody GameCreationParams params){
        if (!isUserInDatabase(playerId)){
            System.out.println("ACCES REFUSE");
            return null;
        }
        Game game = gameFactoryService.createGame(params.boardSize(), playerId, params.gameType(), params.playerCount());
        return convertToDto(game);
    }


    @GetMapping("/games")
    public Stream<GameDto> getAllGames ( @RequestHeader(value = "X-userId", required = false) UUID userId,
                                         @RequestParam(required = false) String status){

        return gameService.getGames(status, userId).map(this::convertToDto);
    }

    @GetMapping("/games/{gameId}")
    public GameDto getGame (@PathVariable UUID gameId){
        return convertToDto(gameService.getGameById(gameId));
    }

    @PutMapping("/games/{gameId}/moves")
    public CellPosition playGame(
            @RequestHeader("X-UserId") UUID userId, @PathVariable UUID gameId, @RequestBody GamePlayParams playParams) {
        try{
            CellPosition move = gameService.play(userId,playParams.x() ,playParams.y(), gameId);
            return move;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/games/{gameId}/moves")
    public Set<CellPosition> playGame(
            @PathVariable UUID gameId) {
        return gameService.getPossibleMoves(gameId);
    }






    private GameDto convertToDto(Game game){
        return new GameDto(
                game.getId().toString(),
                game.getFactoryId(),
                game.getBoardSize(),
                game.getPlayerIds().size(),
                game.getStatus().name(),
                game.getCurrentPlayerId()
        );

    }

    private boolean isUserInDatabase(UUID userId){
        try{
            List<UserParams> usersParams = restClient.get()
                    .uri("http://localhost:8080/users")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            assert usersParams != null;
            List<String> ids = usersParams.stream().map(UserParams::id).toList();

            return ids.contains(userId.toString());
        }catch (Exception e){
            System.err.println(e.getMessage() );
            return true;
        }
    }

}
