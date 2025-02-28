package com.example.spring_boardgame.controller;

import com.example.spring_boardgame.plugin.Connect4Plugin;
import com.example.spring_boardgame.plugin.GamePlugin;
import com.example.spring_boardgame.plugin.TaquinPlugin;
import com.example.spring_boardgame.plugin.TicTacToePlugin;
import com.example.spring_boardgame.service.catalog.GameCatalog;
import com.example.spring_boardgame.service.catalog.GameCatalogImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GameCatalogController {
    GameCatalogImpl gameCatalogimpl = new GameCatalogImpl();
    private GamePlugin gamePlugin;
    @Autowired
    MessageSource messageSource;
    @Autowired
    GameCatalog gameCatalog;

    @GetMapping("/catalog")
    public String getGameCatalog() {
        return gameCatalogimpl.getGameIdentifiers().toString();
    }

    @GetMapping("catalog/gameType/{gameType}")
    public String getGameName(@RequestHeader("Language") Locale language, @PathVariable String gameType) {

        switch (gameType){
            case "tictactoe":gamePlugin = new TicTacToePlugin(messageSource); break;
            case "connect4":gamePlugin = new Connect4Plugin(messageSource); break;
            case "taquin":gamePlugin = new TaquinPlugin(messageSource); break;
        }
        return  gamePlugin.getName(language);

    }
}
