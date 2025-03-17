package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.board.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class LevelTest {
    private Level level;
    private Board board;
    private CollisionMap collisionMap;
    private Square startSquare;
    private Square destinationSquare;
    private Unit unit;
    private Direction direction;

    @BeforeEach
    void setUp() {
        // Mock dependencies
        board = mock(Board.class);
        collisionMap = mock(CollisionMap.class);
        startSquare = mock(Square.class);
        destinationSquare = mock(Square.class);
        unit = mock(Unit.class);
        direction = Direction.EAST;

        // Configure board squares
        when(startSquare.getSquareAt(direction)).thenReturn(destinationSquare);
        when(destinationSquare.isAccessibleTo(unit)).thenReturn(true);
        when(destinationSquare.getOccupants()).thenReturn(Collections.emptyList());

        // Configure unit
        when(unit.getSquare()).thenReturn(startSquare);
        when(unit.hasSquare()).thenReturn(true);

        // Initialize Level
        level = new Level(board, Collections.emptyList(), Collections.singletonList(startSquare), collisionMap);
    }

    @Test
    void testMoveSuccessful() {
        level.start();
        level.move(unit, direction);

        verify(unit).setDirection(direction);
        verify(unit).occupy(destinationSquare);
    }
}
