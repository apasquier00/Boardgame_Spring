package com.example.spring_boardgame;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    void createGame(){

    }

    @GetMapping("/X-UserId")
    int getUserId(@RequestHeader int id) {
        return id;
    }
}
