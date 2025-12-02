package radioactivePursuit.interactives;
import org.junit.jupiter.api.Test;
import radioactivePursuit.creatures.Creature;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {
    @Test
    void CreatureTestInitialization(){
        Creature testCreature = new Creature();
        assertEquals(testCreature.getHealth(), 10.0);
    }
}
