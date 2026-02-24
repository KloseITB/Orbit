package it.unipv.posfw.orbit.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // create a new user instance for testing
        user = new User("testUser", "password123");
    }

    @Test
    public void testUserInitialization() {
        // verify the initial balance is zero
        assertEquals(0.0, user.getBalance(), 0.01);
        
        // verify the nickname is properly assigned
        assertEquals("testUser", user.getNickname());
        
        // verify the user is not banned by default
        assertFalse(user.isBanned());
    }

    @Test
    public void testSetBanned() {
        // ban the user locally
        user.setBanned(true);
        
        // verify the user status has changed to banned
        assertTrue(user.isBanned());
    }
    
    @Test
    public void testLibraryNotNull() {
        // check that the library object is created together with the user
        assertNotNull(user.getLibrary());
    }
}