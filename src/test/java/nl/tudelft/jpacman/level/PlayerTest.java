package nl.tudelft.jpacman.level;

import static org.assertj.core.api.Assertions.assertThat;

import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        var pacmanSpite = new PacManSprites();
        var playerFactory = new PlayerFactory(pacmanSpite);
        player = playerFactory.createPacMan();
    }
    @Test
    void testIsAliveTrueAtFirst() {
        assertThat(player.isAlive()).isTrue();
    }

    @Test
    void testIsAliveFalseWhenOneDeath() {
        player.setAlive(false);

        assertThat(player.isAlive()).isFalse();
    }

}
