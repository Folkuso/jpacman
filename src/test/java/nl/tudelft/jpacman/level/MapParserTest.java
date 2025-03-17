package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class MapParserTest {
    private MapParser parser;

    @BeforeEach
    void setUp() {
        PacManSprites sprites = new PacManSprites();
        LevelFactory levelFactory = new LevelFactory(
            sprites,
            new GhostFactory(sprites),
            mock(PointCalculator.class));
        parser = new MapParser(levelFactory, new BoardFactory(sprites));
    }

    @Test
    public void testParseMapFromInputStream() throws IOException {
        String mapData = "#####\n#.PG#\n#####\n";
        InputStream inputStream = new java.io.ByteArrayInputStream(mapData.getBytes());

        Level level = parser.parseMap(inputStream);

        var player = level.getBoard().squareAt(1, 1);
        var ghost = level.getBoard().squareAt(3, 1);

        assertTrue(player.getOccupants().get(0) instanceof Pellet);
        assertTrue(ghost.getOccupants().get(0) instanceof Ghost);
        assertNotNull(level);
    }
}
