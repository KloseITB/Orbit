package it.unipv.posfw.orbit.game;

import org.junit.jupiter.api.Test;
import it.unipv.posfw.orbit.exception.AmountNotValidException;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountManagerTest {

    @Test
    public void testCalculateDiscountValid() {
        // create the variables for the test
        double currentPrice = 50.0;
        double percentage = 20.0;

        try {
            // calculate the new price
            double discountedPrice = DiscountManager.calculateDiscount(currentPrice, percentage);
            
            // check that the expected value is 40.0
            assertEquals(40.0, discountedPrice, 0.01);
        } catch (AmountNotValidException e) {
            // fail the test if the exception is thrown unexpectedly
            fail("exception should not be thrown for valid amounts");
        }
    }

    @Test
    public void testCalculateDiscountInvalidPrice() {
        // create an invalid price below the minimum value
        double invalidPrice = -10.0;
        double percentage = 20.0;

        // check that the correct exception is thrown
        assertThrows(AmountNotValidException.class, () -> {
            DiscountManager.calculateDiscount(invalidPrice, percentage);
        });
    }
}