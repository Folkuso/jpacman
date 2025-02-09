package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScorePanelTest {
    Player player;
    ScorePanel scorePanel;

    @BeforeEach
    void setUp() {
        player = mock(Player.class);
        var playerList = List.of(player);

        scorePanel = new ScorePanel(playerList);
    }

    @Test
    void testConstructorCreatesCorrectNumberOfLabels() {
        assertEquals(3, scorePanel.getComponentCount());
    }

    @Test
    void testRefreshUpdatesScores() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);

        scorePanel.refresh();

        JLabel scoreLabel = (JLabel) scorePanel.getComponent(1);
        assertEquals("Score:  10", scoreLabel.getText());
    }

    @Test
    void testRefreshUpdatesLives() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);
        when(player.getLives()).thenReturn(2);

        scorePanel.refresh();

        JLabel livesLabel = (JLabel) scorePanel.getComponent(2);
        assertEquals("Lives: 2", livesLabel.getText());
    }

    @Test
    void testRefreshDisplaysDeathMessage() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(false);

        scorePanel.refresh();

        JLabel scoreLabel = (JLabel) scorePanel.getComponent(2);
        assertEquals("You died.", scoreLabel.getText());
    }

    @Test
    void testSetScoreFormatterChangesScoreDisplay() {
        ScorePanel.ScoreFormatter customFormatter = player -> String.format("Custom score: %d", player.getScore());
        scorePanel.setScoreFormatter(customFormatter);

        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);

        scorePanel.refresh();

        JLabel scoreLabel = (JLabel) scorePanel.getComponent(1);
        assertEquals("Custom score: 10", scoreLabel.getText());
    }

    @Test
    void testDefaultScoreFormatter() {
        when(player.getScore()).thenReturn(10);
        when(player.isAlive()).thenReturn(true);

        ScorePanel.ScoreFormatter defaultFormatter = ScorePanel.DEFAULT_SCORE_FORMATTER;
        String formattedScore = defaultFormatter.format(player);

        assertEquals("Score:  10", formattedScore);
    }

    @Test
    void testNullPlayerListThrowsExceptionForSinglePlayer() {
        assertThrows(IllegalArgumentException.class, () -> new ScorePanel(null));
    }

    @Test
    void testSetScoreFormatterNullFormatterThrowsExceptionForSinglePlayer() {
        assertThrows(IllegalArgumentException.class, () -> scorePanel.setScoreFormatter(null));
    }
}
