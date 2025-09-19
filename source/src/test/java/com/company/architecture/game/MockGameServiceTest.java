package com.company.architecture.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

@SpringBootTest(classes = GameService.class)
class MockGameServiceTest {
    @Autowired
    GameService gameService;

    @MockitoBean
    @Autowired
    GameRepository gameRepository;

    @Test
    void shouldReturnAllGamesWhenListingWithAnyTitle() {
        final var games = List.of(new Game("Game X"), new Game("Game Y"), new Game("Game Z"));
        Mockito.when(gameRepository.list(ArgumentMatchers.any())).thenReturn(games);
        Assertions.assertEquals(games.size(), gameService.list(new Game("A")).size());
        Assertions.assertEquals(games.size(), gameService.list(new Game("B")).size());
        Assertions.assertEquals(games.size(), gameService.list(new Game("C")).size());
    }
}
