package architecture.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { GameService.class, GameRepository.class })
class GameServiceTest {
    @Autowired
    GameService gameService;

    @Test
    void testList() {
        final var title = "Game A";
        final var games = gameService.list(new Game(title));
        Assertions.assertEquals(title, games.get(0).getTitle());
    }
}
