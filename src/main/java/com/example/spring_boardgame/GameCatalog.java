package com.example.spring_boardgame;

import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface GameCatalog {


    Collection<String> getGameIdentifiers();

}
