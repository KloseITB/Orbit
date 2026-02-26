package it.unipv.posfw.orbit.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    public void testReviewInitialization() {
        // create the variables for the test
        int userId = 1;
        int gameId = 15;
        int rating = 4;
        String comment = "very fun game, highly recommended!";

        // act by creating a new review instance
        Review review = new Review(userId, gameId, rating, comment);

        // assert that the getters return the correct values
        assertEquals(1, review.getUserId());
        assertEquals(15, review.getGameId());
        assertEquals(4, review.getRating());
        assertEquals("very fun game, highly recommended!", review.getComment());
    }
}