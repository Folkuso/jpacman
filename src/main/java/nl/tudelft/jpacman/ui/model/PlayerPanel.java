package nl.tudelft.jpacman.ui.model;

import javax.swing.*;

public class PlayerPanel {
    JLabel scoreLabel;
    JLabel livesLabel;

    public PlayerPanel(JLabel scoreLabel, JLabel livesLabel) {
        this.scoreLabel = scoreLabel;
        this.livesLabel = livesLabel;
    }

    public void setScoreText(String scoreText) {
        scoreLabel.setText(scoreText);
    }

    public void setLivesText(String livesText) {
        livesLabel.setText(livesText);
    }
}
