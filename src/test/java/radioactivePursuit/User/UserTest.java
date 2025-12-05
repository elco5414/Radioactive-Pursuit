package radioactivePursuit.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName(null);
        user.setScore(0);
    }

    @Test
    void testSetName() {
        user.setName("Alice");
        assertEquals("Alice", user.getName());
    }

    @Test
    void testSetScore() {
        user.setScore(42);
        assertEquals(42, user.getScore());
    }

    @Test
    void testMultipleUpdates() {
        user.setName("First");
        user.setScore(10);

        user.setName("Second");
        user.setScore(20);

        assertEquals("Second", user.getName());
        assertEquals(20, user.getScore());
    }
}

