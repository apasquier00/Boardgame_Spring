package com.example.spring_boardgame;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @PostMapping("/game/{id1}/{id2}")
    void createGame(@PathVariable int id1,@PathVariable int id2){

    }

    @GetMapping("/X-UserId")
    int getUserId(@RequestHeader int id) {
        return id;
    }


    int randomUserId() {
        return (int)(Math.random()*10);
    }
}
