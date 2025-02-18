package com.example.spring_boardgame;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameCatalogController {
    GameCatalogImpl gameCatalogimpl = new GameCatalogImpl();

    @Autowired
    GameCatalog gameCatalog;

    @GetMapping("/users")
    public String getGameCatalog() {
        return gameCatalogimpl.getGameIdentifiers().toString();
    }
}
