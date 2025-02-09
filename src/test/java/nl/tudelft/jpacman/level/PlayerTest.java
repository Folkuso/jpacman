package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.AnimatedSprite;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlayerTest {
    private AnimatedSprite animationMock;
    private Player player;

    @BeforeEach
    void setUp() {
        animationMock = Mockito.mock(AnimatedSprite.class);
        player = new Player(null, animationMock);
    }

    @Test
    void testAddFivePointsAddsInGetScore() {
        player.addPoints(5);

        assertThat(player.getScore()).isEqualTo(5);
    }

    @Test
    void testAddMultiplePointsAddsInGetScore() {
        player.addPoints(5);
        player.addPoints(10);
        player.addPoints(0);
        player.addPoints(5);

        assertThat(player.getScore()).isEqualTo(20);
    }

    @Test
    void testIsAliveTrueWhenTwoDeaths() {
        player.setAlive(false);
        player.setAlive(false);

        assertThat(player.isAlive()).isTrue();
    }

    @Test
    void testIsAliveFalseWhenThreeDeaths() {
        player.setAlive(false);
        player.setAlive(false);
        player.setAlive(false);

        assertThat(player.isAlive()).isFalse();
    }

    @Test
    void testIsAliveTrueWhenIsAliveTrue() {
        player.setAlive(true);

        assertThat(player.getKiller()).isNull();
        verify(animationMock, times(2)).setAnimating(false);
    }

}
