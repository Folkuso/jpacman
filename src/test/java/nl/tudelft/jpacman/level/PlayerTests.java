package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.AnimatedSprite;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTests {
    private AnimatedSprite animationMock;
    private Player player;

    @BeforeEach
    public void setUp() {
        animationMock = Mockito.mock(AnimatedSprite.class);
        player = new Player(null, animationMock);
    }

    @Test
    public void testAddFivePointsAddsInGetScore() {
        player.addPoints(5);

        assertThat(player.getScore()).isEqualTo(5);
    }

    @Test
    public void testAddMultiplePointsAddsInGetScore() {
        player.addPoints(5);
        player.addPoints(10);
        player.addPoints(0);
        player.addPoints(5);

        assertThat(player.getScore()).isEqualTo(20);
    }

    @Test
    public void testIsAliveTrueWhenTwoDeaths() {
        player.setAlive(false);
        player.setAlive(false);

        assertThat(player.isAlive()).isTrue();
    }

    @Test
    public void testIsAliveFalseWhenThreeDeaths() {
        player.setAlive(false);
        player.setAlive(false);
        player.setAlive(false);

        assertThat(player.isAlive()).isFalse();
    }

    @Test
    public void testIsAliveTrueWhenIsAliveTrue() {
        player.setAlive(true);

        assertThat(player.getKiller()).isNull();
        verify(animationMock, times(2)).setAnimating(false);
    }

}
