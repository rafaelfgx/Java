package com.company.architecture.game;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {
    private static final List<Game> games = List.of(new Game("Game A"), new Game("Game B"));

    public List<Game> list(final Game game) {
        return games.stream().filter(item -> item.title().contains(game.title())).toList();
    }
}
