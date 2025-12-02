package radioactivePursuit.creatures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureTest {
    CreatureFactory creatureFactory = new CreatureFactory();

    @Test
    void PikachuCreationTest(){
        Creature pikachu = creatureFactory.createPikachu();
        assertEquals(pikachu.getHealth(), 5.0);
        assertEquals(pikachu.getName(), "Pikachu");
        assertTrue(pikachu.getRadioActiveLevel() <= 3);
        assertTrue(pikachu.getRadioActiveLevel() > 0);
    }

    @Test
    void ClefairyCreationTest(){
        Creature clefairy = creatureFactory.createClefairy();
        assertEquals(clefairy.getHealth(), 4.3);
        assertEquals(clefairy.getName(), "Clefairy");
        assertTrue(clefairy.getRadioActiveLevel() <= 3);
        assertTrue(clefairy.getRadioActiveLevel() > 0);
    }
    @Test
    void SquirtleCreationTest(){
        Creature squirtle = creatureFactory.createSquirtle();
        assertEquals(squirtle.getHealth(), 7.0);
        assertEquals(squirtle.getName(), "Squirtle");
        assertTrue(squirtle.getRadioActiveLevel() <= 3);
        assertTrue(squirtle.getRadioActiveLevel() > 0);
    }
    @Test
    void ButterfreeCreationTest(){
        Creature butterfree = creatureFactory.createButterfree();
        assertEquals(butterfree.getHealth(), 3.0);
        assertEquals(butterfree.getName(), "Butterfree");
        assertTrue(butterfree.getRadioActiveLevel() <= 3);
        assertTrue(butterfree.getRadioActiveLevel() > 0);
    }
}
