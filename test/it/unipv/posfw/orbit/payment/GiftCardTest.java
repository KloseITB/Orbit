package it.unipv.posfw.orbit.payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GiftCardTest {

    @Test
    public void testGiftCardInitialization() {
        // create a new gift card with a code and a value
        GiftCard giftCard = new GiftCard("ORBIT-50-EU", 50.0);

        // check if the code is assigned correctly
        assertEquals("ORBIT-50-EU", giftCard.getCode());

        // check if the value is assigned correctly
        assertEquals(50.0, giftCard.getValue(), 0.01);
    }
}