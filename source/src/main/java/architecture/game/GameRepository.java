package architecture.game;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository {
    private static final List<Game> games = List.of(new Game("Game A"), new Game("Game B"));

    public List<Game> list(final Game game) {
        return games.stream().filter(item -> item.getTitle().contains(game.getTitle())).toList();
    }
}
