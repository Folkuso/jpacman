package nl.tudelft.jpacman.level;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapParserTest {
    private MapParser mapParser;
    private LevelFactory levelFactory;
    private BoardFactory boardFactory;

    @BeforeEach
    public void setUp() {
        levelFactory = mock(LevelFactory.class);
        boardFactory = mock(BoardFactory.class);
        mapParser = new MapParser(levelFactory, boardFactory);
    }

    @Test
    public void testParseMapFromInputStream() throws IOException {
        String mapData = "###\n#P#\n#G#\n";
        InputStream inputStream = new java.io.ByteArrayInputStream(mapData.getBytes());

        when(boardFactory.createBoard(any(Square[][].class))).thenReturn(mock(Board.class));
        when(boardFactory.createGround()).thenReturn(mock(Square.class));
        when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
        when(levelFactory.createLevel(any(Board.class), anyList(), anyList())).thenReturn(mock(Level.class));

        Level level = mapParser.parseMap(inputStream);

        var player = level.getBoard().squareAt(1, 1);
        var ghost = level.getBoard().squareAt(2, 1);

        assertThat(player.getOccupants().get(0).getClass()).isEqualTo(Player.class);
        assertThat(ghost.getOccupants().get(0).getClass()).isEqualTo(Ghost.class);
        assertNotNull(level);
    }
}
