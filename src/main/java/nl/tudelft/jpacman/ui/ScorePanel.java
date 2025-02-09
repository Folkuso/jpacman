package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.ui.model.PlayerPanel;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class ScorePanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final transient Map<Player, PlayerPanel> labels;

    /**
     * The default way in which the score is shown.
     */
    public static final ScoreFormatter DEFAULT_SCORE_FORMATTER =
        (Player player) -> String.format("Score: %3d", player.getScore());

    /**
     * The way to format the score information.
     */
    private transient ScoreFormatter scoreFormatter = DEFAULT_SCORE_FORMATTER;

    /**
     * Creates a new score panel with a column for each player.
     *
     * @param players
     *            The players to display the scores of.
     */
    public ScorePanel(List<Player> players) {
        super();
        if(players == null || players.isEmpty()) {
            throw new IllegalArgumentException("players cannot be null or empty");
        }

        setLayout(new GridLayout(3, players.size()));

        labels = new LinkedHashMap<>();
        for (int i = 0; i < players.size(); i++) {
            var playerNumber = String.valueOf(i + 1);
            JLabel playerNumberLabel = new JLabel("Player " + playerNumber, javax.swing.SwingConstants.CENTER);
            JLabel scoreLabel = new JLabel("0", javax.swing.SwingConstants.CENTER);
            JLabel livesLabel = new JLabel("0", javax.swing.SwingConstants.CENTER);

            labels.put(players.get(i), new PlayerPanel(scoreLabel, livesLabel));

            add(playerNumberLabel);
            add(scoreLabel);
            add(livesLabel);
        }
    }

    /**
     * Refreshes the scores of the players.
     */
    protected void refresh() {
        for (var entry : labels.entrySet()) {
            Player player = entry.getKey();

            var score = scoreFormatter.format(player);
            var lives = getLives(player);

            var labelEntries = entry.getValue();
            labelEntries.setScoreText(score);
            labelEntries.setLivesText(lives);
        }
    }

    private static String getLives(Player player) {
        var lives = "Lives: " + player.getLives();
        if(!player.isAlive()) {
            lives = "You died.";
        }
        return lives;
    }

    /**
     * Provide means to format the score for a given player.
     */
    public interface ScoreFormatter {

        /**
         * Format the score of a given player.
         * @param player The player and its score
         * @return Formatted score.
         */
        String format(Player player);
    }

    /**
     * Let the score panel use a dedicated score formatter.
     * @param scoreFormatter Score formatter to be used.
     */
    public void setScoreFormatter(ScoreFormatter scoreFormatter) {
        if(scoreFormatter == null) {
            throw new IllegalArgumentException("scoreFormatter should not be null");
        }
        this.scoreFormatter = scoreFormatter;
    }
}
