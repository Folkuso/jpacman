package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScorePanelTest {
    Player player;
    ScorePanel scorePanel;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        var playerList = List.of(player);

        scorePanel = new ScorePanel(playerList);
    }

    @Test
    public void testConstructorCreatesCorrectNumberOfLabels() {
        assertEquals(2, scorePanel.getComponentCount());
    }

    @Test
    public void testRefreshUpdatesScores() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);

        scorePanel.refresh();

        JLabel scoreLabel = (JLabel) scorePanel.getComponent(1);
        assertEquals("Score:  10", scoreLabel.getText());
    }

    @Test
    public void testRefreshDisplaysDeathMessage() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(false);

        scorePanel.refresh();

        JLabel scoreLabel = (JLabel) scorePanel.getComponent(1);
        assertTrue(scoreLabel.getText().startsWith("You died."));
    }

    @Test
    public void testSetScoreFormatterChangesScoreDisplay() {
        ScorePanel.ScoreFormatter customFormatter = player -> String.format("Custom score: %d", player.getScore());
        scorePanel.setScoreFormatter(customFormatter);

        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);

        scorePanel.refresh();

        JLabel scoreLabel = (JLabel) scorePanel.getComponent(1);
        assertEquals("Custom score: 10", scoreLabel.getText());
    }

    @Test
    public void testDefaultScoreFormatter() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);

        ScorePanel.ScoreFormatter defaultFormatter = ScorePanel.DEFAULT_SCORE_FORMATTER;
        String formattedScore = defaultFormatter.format(player);

        assertEquals("Score:  10", formattedScore);
    }

    @Test
    public void testNullPlayerListThrowsExceptionForSinglePlayer() {
        assertThrows(AssertionError.class, () -> new ScorePanel(null));
    }

    @Test
    public void testSetScoreFormatterNullFormatterThrowsExceptionForSinglePlayer() {
        assertThrows(AssertionError.class, () -> scorePanel.setScoreFormatter(null));
    }
}
