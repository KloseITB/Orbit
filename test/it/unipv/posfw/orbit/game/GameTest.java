package it.unipv.posfw.orbit.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        // initialize a new game before each test
        game = new Game("test game", 60.0, "action", "/path/to/cover.png");
    }

    @Test
    public void testGameInitialization() {
        // check if the base price is correct
        assertEquals(60.0, game.getBasePrice(), 0.01);
        
        // check if the current price equals the base price at creation
        assertEquals(60.0, game.getCurrentPrice(), 0.01);
        
        // check if the title matches
        assertEquals("test game", game.getTitle());
    }

    @Test
    public void testAvgRatingWithNoReviews() {
        // calculate average rating without any reviews added
        double avg = game.avgRating();
        
        // check that the average is safely 0.0 and does not crash
        assertEquals(0.0, avg, 0.01);
    }
    
    @Test
    public void testApplyDiscount() {
        // apply a 50 percent discount to the game
        game.discount(50.0);
        
        // check that the current price is halved
        assertEquals(30.0, game.getCurrentPrice(), 0.01);
    }
}