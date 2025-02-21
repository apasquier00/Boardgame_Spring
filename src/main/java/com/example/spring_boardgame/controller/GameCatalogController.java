package com.example.spring_boardgame.controller;

import com.example.spring_boardgame.service.catalog.GameCatalog;
import com.example.spring_boardgame.service.catalog.GameCatalogImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameCatalogController {
    GameCatalogImpl gameCatalogimpl = new GameCatalogImpl();

    @Autowired
    GameCatalog gameCatalog;

    @GetMapping("/catalog")
    public String getGameCatalog() {
        return gameCatalogimpl.getGameIdentifiers().toString();
    }
}
